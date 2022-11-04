package lol.waifuware.waifuhax.Modules;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lol.waifuware.waifuhax.Waifuhax;
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

    public HashMap<String, Object> settings = new HashMap();

    public Module(String name, int Key)
    {
        Waifuhax.LOGGER.info("module " + name + " was loaded");

        this.name = name;
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
    }
    public abstract void Update();

    public abstract void Render(MatrixStack matrice);
    public abstract void onActivate();
    public abstract void onDisable();

    public void onChat(String message){};

    public void save()
    {
        JSONObject object = new JSONObject();

        object.append("active", isEnabled);

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

    public void Create()
    {
        File file = new File("WaifuHax/modules/" + name + ".WaifuConfig");
        if(!file.exists()){
            try
            {
                file.createNewFile();

                JSONObject object = new JSONObject();

                object.append("active", false);

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
                /*if(entry.getKey().toString().toLowerCase(Locale.ROOT).equals("key")){
                    System.out.println(Integer.parseInt(entry.getValue().toString().replace("[", "").replace("]", "").replace("\"", "")));
                    key = Integer.parseInt(entry.getValue().toString().replace("[", "").replace("]", "").replace("\"", ""));
                }else*/ if(entry.getKey().toString().toLowerCase(Locale.ROOT).equals("active")){
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
