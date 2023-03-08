package lol.waifuware.Modules;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lol.waifuware.Modules.Interfaces.IModule;
import lol.waifuware.Modules.Interfaces.Module;
import lol.waifuware.Util.ChatUtil;
import lol.waifuware.Waifuhax;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public abstract class AbstractModule implements IModule
{
    public String getName()
    {
        return name;
    }

    public HashMap<String, Object> settings = new HashMap();
    public String[] desc = new String[5];

    public String name;
    public int key;
    public CATEGORY cat;

    public AbstractModule()
    {
        Module module = this.getClass().getAnnotation(Module.class);

        this.name = module.name();
        this.cat = module.cat();
        this.key = module.key();

        desc[0] = "[NO DESCRIPTION PROVIDED]";
        Waifuhax.LOGGER.info("module " + name + " was loaded");
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

        for(Map.Entry entry : settings.entrySet())
        {
            object.append((String) entry.getKey(), entry.getValue());
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

                for(Map.Entry entry : settings.entrySet())
                {
                    object.append((String) entry.getKey(), entry.getValue());
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

            settings.clear();

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
                    settings.put((String) entry.getKey(), entry.getValue().toString().replace("[", "").replace("]", "").replace("\"", ""));
                }
            }
        }catch (IOException e){
            Waifuhax.Log("A module couldn't be loaded : " + e.toString());
        }
    }
}
