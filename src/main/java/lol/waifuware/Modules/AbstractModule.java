package lol.waifuware.Modules;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lol.waifuware.Events.OnMessageReceive;
import lol.waifuware.Modules.Interfaces.IModule;
import lol.waifuware.Modules.Interfaces.Module;
import lol.waifuware.Settings.BooleanSetting;
import lol.waifuware.Settings.IntSetting;
import lol.waifuware.Settings.ModeSetting;
import lol.waifuware.Settings.Setting;
import lol.waifuware.Util.ChatUtil;
import lol.waifuware.Waifuhax;
import meteordevelopment.orbit.EventHandler;
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

    public AbstractModule()
    {
        Module module = this.getClass().getAnnotation(Module.class);

        name = module.name();
        cat = module.cat();
        key = module.key();

        desc[0] = "[NO DESCRIPTION PROVIDED]";
        Waifuhax.LOGGER.info("module " + name + " was loaded");
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
        Waifuhax.Log("Module " + name + " is loading " + settings.length + " settings");
        for(Setting set : settings)
        {
            Waifuhax.Log("Adding setting " + set.name);
            addSetting(set);
        }
    }

    public boolean isEnabled = false;

    public void Toggle()
    {
        Toggle(!isEnabled);
    }

    public void Toggle(boolean Forced)
    {
        isEnabled = Forced;

        Waifuhax.Log("Module " + name + " got toggled");
        ChatUtil.SendMessage("Module " + name + " was toggled " + (isEnabled ? "§aON§r" : "§4OFF§r"));

        if(isEnabled)
        {
            onEnable();
            Waifuhax.EVENT_BUS.subscribe(this);
        }
        else
        {
            onDisable();
            Waifuhax.EVENT_BUS.unsubscribe(this);
        }

        Save();
    }

    public void onEnable(){};

    public void onDisable(){};

    public void Save()
    {
        JSONObject object = new JSONObject();

        object.append("active", isEnabled);
        object.append("key", key);
        Waifuhax.Log("Setting list for " + name + " is " + getSettings().size());
        for(Setting setting : getSettings())
        {
            if(setting instanceof IntSetting e)
            {
                Waifuhax.Log("Added " + name + " to save file with " + e.getValue());
                object.append((String) setting.name, e.getValue());
            }
            else if (setting instanceof BooleanSetting e)
            {
                Waifuhax.Log("Added " + name + " to save file with " + e.getEnabled());
                object.append((String) setting.name, e.getEnabled());
            }
            else if (setting instanceof ModeSetting e)
            {
                Waifuhax.Log("Added " + name + " to save file with " + e.getIndex());
                object.append((String) setting.name, e.getIndex());
            }
        }

        System.out.println(object);

        try
        {
            FileWriter writer = new FileWriter("WaifuHax/modules/" + name + ".WaifuConfig");

            writer.write(object.toString());

            writer.close();
        }catch (IOException e){
            Waifuhax.Log("A module couldn't be saved : " + e.toString());
        }
    }
    public void Create()
    {
        File file = new File("WaifuHax/modules/" + name + ".WaifuConfig");
        if(!file.exists())
        {
            try
            {
                file.createNewFile();

                JSONObject object = new JSONObject();

                object.append("active", false);
                object.append("key", key);

                Waifuhax.Log("Setting list for " + name + " is " + getSettings().size());

                for(Setting setting : settings)
                {
                    if(setting instanceof IntSetting e)
                    {
                        Waifuhax.Log("Loaded " + name + " to save file with " + e.getValue());
                        object.append((String) setting.name, e.getValue());
                    }
                    else if (setting instanceof BooleanSetting e)
                    {
                        Waifuhax.Log("Loaded " + name + " to save file with " + e.getEnabled());
                        object.append((String) setting.name, e.getEnabled());
                    }
                    else if (setting instanceof ModeSetting e)
                    {
                        Waifuhax.Log("Loaded " + name + " to save file with " + e.getIndex());
                        object.append((String) setting.name, e.getIndex());
                    }
                }

                try
                {
                    FileWriter writer = new FileWriter("WaifuHax/modules/" + name + ".WaifuConfig");

                    writer.write(object.toString());

                    writer.close();
                }catch (IOException ignored){}
            }
            catch (IOException e){
                Waifuhax.Log("A module couldn't be saved : " + e.toString());
            }
        }else{
            Load();
        }
    }

    public void Load()
    {
        try {
            JsonParser jsonP = new JsonParser();

            JsonObject json = (JsonObject)jsonP.parse(new FileReader("WaifuHax/modules/" + name + ".WaifuConfig"));

            //settings.clear();

            for(Map.Entry entry : json.entrySet())
            {
                if(entry.getKey().toString().toLowerCase(Locale.ROOT).equals("key"))
                {
                    key = Integer.parseInt(entry.getValue().toString().replace("[", "").replace("]", "").replace("\"", ""));
                }
                else if(entry.getKey().toString().toLowerCase(Locale.ROOT).equals("active"))
                {
                    boolean tmp = entry.getValue().toString().replace("[", "").replace("]", "").replace("\"", "").equals("true");
                    if(tmp) {
                        Toggle(tmp);
                    }
                }
                else
                {
                    for(Setting setting : settings)
                    {
                        if(setting.name == entry.getKey())
                        {
                            IntSetting e = (IntSetting)setting;
                            e.setValue(Double.parseDouble(entry.getValue().toString().replace("[", "").replace("]", "").replace("\"", "")));
                        }
                        else if (setting instanceof BooleanSetting)
                        {
                            BooleanSetting e = (BooleanSetting)setting;
                            e.setEnabled((entry.getValue().toString().replace("[", "").replace("]", "").replace("\"", "")) == "true");
                        }
                        else if (setting instanceof ModeSetting)
                        {
                            ModeSetting e = (ModeSetting)setting;
                            e.setIndex(Integer.parseInt(entry.getValue().toString().replace("[", "").replace("]", "").replace("\"", "")));
                        }
                    }
                }
            }
        }catch (IOException e){
            Waifuhax.Log("A module couldn't be loaded : " + e.toString());
        }
    }
}
