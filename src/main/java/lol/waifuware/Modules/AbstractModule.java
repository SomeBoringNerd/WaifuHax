package lol.waifuware.Modules;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lol.waifuware.Modules.Interfaces.IModule;
import lol.waifuware.Modules.Interfaces.Module;
import lol.waifuware.Modules.MISC.GlobalSettings;
import lol.waifuware.Settings.BooleanSetting;
import lol.waifuware.Settings.IntSetting;
import lol.waifuware.Settings.ModeSetting;
import lol.waifuware.Settings.Setting;
import lol.waifuware.Util.ChatUtil;
import lol.waifuware.Waifuhax;
import net.minecraft.client.MinecraftClient;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public abstract class AbstractModule implements IModule
{
    public String getName()
    {
        return name;
    }

    public List<Setting> settings = new ArrayList<>();
    public String[] desc = new String[5];

    public String name;
    public int key;
    public CATEGORY cat;
    
    private String path;

    public boolean isWorkInProgress;

    public AbstractModule()
    {
        Module module = this.getClass().getAnnotation(Module.class);

        name = module.name();
        cat = module.cat();
        key = module.key();

        desc[0] = "[NO DESCRIPTION PROVIDED]";
        Waifuhax.LOGGER.info("module " + name + " was loaded");
        
        path = "WaifuHax/modules/" + cat.name + "/" + name + ".WaifuConfig";
    }

    public AbstractModule(boolean fakeModule)
    {
        Module module = this.getClass().getAnnotation(Module.class);

        isFake = fakeModule;

        name = module.name();
        cat = module.cat();
        key = module.key();

        desc[0] = "[NO DESCRIPTION PROVIDED]";
        Waifuhax.LOGGER.info("module " + name + " was loaded");

        path = "WaifuHax/modules/" + cat.name + "/" + name + ".WaifuConfig";
    }

    public List<Setting> getSettings(){
        return settings;
    }

    public void addSetting(Setting setting)
    {
        this.settings.add(setting);
    }

    public void addSettings(Setting... settings)
    {
        for(Setting set : settings)
        {
            addSetting(set);
        }
    }

    public boolean isEnabled = false;

    private static boolean amIEnabled;

    public static boolean isEnabled()
    {
        return amIEnabled;
    }

    public void Toggle()
    {
        Toggle(!isEnabled);
    }

    public void Toggle(boolean Forced)
    {
        isEnabled = Forced;

        if(GlobalSettings.ToggleMessage.getEnabled()) {
            ChatUtil.SendMessage("Module " + name + " was toggled " + (isEnabled ? "§aON§r" : "§4OFF§r"));
        }
        if(isEnabled)
        {
            onEnable();
            amIEnabled = true;
            Waifuhax.EVENT_BUS.subscribe(this);
        }
        else
        {
            onDisable();
            amIEnabled = false;
            Waifuhax.EVENT_BUS.unsubscribe(this);
        }

        Save();
    }

    public String getDisplayName(){
        return name;
    }

    public void onEnable(){};

    public void onDisable(){};

    private boolean isFake = false;

    /**
     *  Some modules need this to not be drawn in arraylist but can in clickgui (like DescriptionHUD, or GlobalSettings)
      * @return isFake
     */
    public boolean fakeModule()
    {
        return isFake;
    }

    public void Save()
    {
        // dont save modules if the game is shutting down, as it fuck up some saved settings
        if(!MinecraftClient.getInstance().isRunning()) return;

        File file = new File(path);
        if(file.exists())
        {
            JSONObject object = new JSONObject();

            object.append("active", isEnabled);
            object.append("key", key);

            for(Setting setting : getSettings())
            {
                if(setting instanceof IntSetting e)
                {
                    object.append((String) setting.name, e.getValue());
                }
                else if (setting instanceof BooleanSetting e)
                {
                    object.append((String) setting.name, e.getEnabled());
                }
                else if (setting instanceof ModeSetting e)
                {
                    object.append((String) setting.name, e.getIndex());
                }
            }

            try
            {
                FileWriter writer = new FileWriter(path);

                writer.write(object.toString());

                writer.close();
            }
            catch (IOException e)
            {
                Waifuhax.Log("A module couldn't be loaded : " + e.toString());
            }
        }
        else
        {
            Create();
        }
    }
    public void Create()
    {
        File file = new File(path);
        if(!file.exists())
        {
            try
            {
                file.createNewFile();

                JSONObject object = new JSONObject();

                object.append("active", false);
                object.append("key", key);

                for(Setting setting : settings)
                {
                    if(setting instanceof IntSetting e)
                    {
                        object.append((String) setting.name, e.getValue());
                    }
                    else if (setting instanceof BooleanSetting e)
                    {
                        object.append((String) setting.name, e.getEnabled());
                    }
                    else if (setting instanceof ModeSetting e)
                    {
                        object.append((String) setting.name, e.getIndex());
                    }
                }

                try
                {
                    FileWriter writer = new FileWriter(path);

                    writer.write(object.toString());

                    writer.close();
                }
                catch (IOException ignored){}
            }
            catch (IOException e)
            {
                Waifuhax.Log("A module couldn't be loaded : " + e.toString());
            }
        }else{
            Load();
        }
    }

    public void Load()
    {
        try {
            JsonParser jsonP = new JsonParser();

            JsonObject json = (JsonObject)jsonP.parse(new FileReader(path));

            for(Map.Entry entry : json.entrySet())
            {
                if(entry.getKey().toString().toLowerCase(Locale.ROOT).equals("key"))
                {
                    key = Integer.parseInt(entry.getValue().toString().replace("[", "").replace("]", "").replace("\"", ""));
                }
                else if(entry.getKey().toString().toLowerCase(Locale.ROOT).equals("active"))
                {
                    boolean tmp = entry.getValue().toString().replace("[", "").replace("]", "").replace("\"", "").equals("true");
                    if(tmp)
                    {
                        Toggle(true);
                    }
                }
                else
                {
                    for(Setting setting : settings)
                    {
                        if(setting.name.trim().equalsIgnoreCase(entry.getKey().toString().trim()))
                        {
                            if(setting instanceof IntSetting)
                            {
                                IntSetting e = (IntSetting)setting;
                                e.setValue(Double.parseDouble(entry.getValue().toString().replace("[", "").replace("]", "").replace("\"", "")));
                            }
                            else if (setting instanceof BooleanSetting)
                            {
                                BooleanSetting e = (BooleanSetting)setting;
                                e.setEnabled(entry.getValue().toString().replace("[", "").replace("]", "").replace("\"", "").equals("true"));
                            }
                            else if (setting instanceof ModeSetting)
                            {
                                ModeSetting e = (ModeSetting)setting;
                                e.setIndex(Integer.parseInt(entry.getValue().toString().replace("[", "").replace("]", "").replace("\"", "")));
                            }
                        }
                    }
                }
            }
        }catch (IOException e)
        {
            Waifuhax.Log("A module couldn't be loaded : " + e.toString());
        }
    }
}
