package lol.waifuware.waifuhax.Modules;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lol.waifuware.waifuhax.Waifuhax;
import lol.waifuware.waifuhax.util.ChatUtil;
import net.minecraft.client.gui.screen.Overlay;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public abstract class Module
{
    // note : make all modules extended from this one

    public String name;
    public String getName(){
        return name;
    }
    public HashMap<String, Object> settings = new HashMap();
    public String[] desc = new String[5];
    public int key;
    public CATEGORY cat;

    public Module(String name, int Key, CATEGORY cat)
    {
        Waifuhax.LOGGER.info("module " + name + " was loaded");

        this.name = name;
        this.cat = cat;
        this.key = key;

        desc[0] = "[NO DESCRIPTION PROVIDED]";
    }

    public boolean isEnabled = false;

    public void Toggle()
    {
        isEnabled = !isEnabled;

        Waifuhax.Log("Module " + name + " got toggled");

        if(isEnabled){
            onActivate();
        }else{
            onDisable();
        }

        save();
    }

    int tick = 0;

    public void Update()
    {
        if(tick >= 10)
        {
            shouldToggle = true;
            tick = 0;
        }
        tick++;
        _Update();
    }

    public void _Update(){

    }

    public void Render(MatrixStack matrice){};
    public void onActivate(){};
    public void onDisable(){};

    public void onChat(String message){};

    public void save()
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
        }catch (IOException ignored){}
    }

    public boolean shouldToggle = true;


    public void Create()
    {
        File file = new File("WaifuHax/modules/" + name + ".WaifuConfig");
        if(!file.exists()){
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

                System.out.println(object);

                try
                {
                    FileWriter writer = new FileWriter("WaifuHax/modules/" + name + ".WaifuConfig");

                    writer.write(object.toString());

                    writer.close();
                }catch (IOException ignored){}
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }else{
            load();
        }
    }
    public void load()
    {
        try {
            JsonParser jsonP = new JsonParser();

            JsonObject json = (JsonObject)jsonP.parse(new FileReader("WaifuHax/modules/" + name + ".WaifuConfig"));

            settings.clear();

            System.out.println(json);

            for(Map.Entry entry : json.entrySet())
            {
                if(entry.getKey().toString().toLowerCase(Locale.ROOT).equals("key")){
                    System.out.println(Integer.parseInt(entry.getValue().toString().replace("[", "").replace("]", "").replace("\"", "")));
                    key = Integer.parseInt(entry.getValue().toString().replace("[", "").replace("]", "").replace("\"", ""));
                }else if(entry.getKey().toString().toLowerCase(Locale.ROOT).equals("active")){
                    System.out.println(entry.getValue().toString().replace("[", "").replace("]", "").replace("\"", ""));
                    isEnabled = entry.getValue().toString().replace("[", "").replace("]", "").replace("\"", "").equals("true");
                }else {
                    System.out.println(entry.getKey() + " | " + entry.getValue().toString().replace("[", "").replace("]", "").replace("\"", ""));
                    settings.put((String) entry.getKey(), entry.getValue().toString().replace("[", "").replace("]", "").replace("\"", ""));
                }
            }
        }catch (IOException ignored){}
    }
}
