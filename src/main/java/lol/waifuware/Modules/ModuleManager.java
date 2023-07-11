package lol.waifuware.Modules;

import lol.waifuware.Modules.CHAT.Suffix;
import lol.waifuware.Modules.EXPLOITS.GameModeDetector;
import lol.waifuware.Modules.GUI.*;
import lol.waifuware.Modules.MISC.*;
import lol.waifuware.Modules.MOVEMENT.Sprint;
import lol.waifuware.Modules.MOVEMENT.VanillaFly;
import lol.waifuware.Modules.RENDER.FullBright;
import lol.waifuware.Util.ChatUtil;
import org.lwjgl.glfw.GLFW;

import java.io.File;
import java.util.List;

public class ModuleManager
{

    public static java.util.ArrayList<AbstractModule> modules = new java.util.ArrayList<>();

    private static ModuleManager instance;

    public static ModuleManager getInstance()
    {
        return instance;
    }

    // register modules here
    public ModuleManager()
    {
        CheckForFolder();
        instance = this;

        modules.add(new AFK());
        modules.add(new Announcer());
        modules.add(new ModuleList());
        modules.add(new ClickGUI());
        modules.add(new Coordinates());
        modules.add(new FullBright());
        modules.add(new GameModeDetector());
        modules.add(new GlobalSettings());
        modules.add(new HUDEditor());
        modules.add(new Pride());
        modules.add(new Sprint());
        modules.add(new Suffix());
        modules.add(new VanillaFly());
        modules.add(new Watermark());
    }

    public List<AbstractModule> getModsInCat(CATEGORY cat)
    {
        List<AbstractModule> mods = new java.util.ArrayList<>();

        for(AbstractModule mod : modules){
            if(mod.cat == cat)
            {
                mods.add(mod);
            }
        }

        return mods;
    }

    void CheckForFolder(){
        File file = new File("WaifuHax");

        if(!file.exists()){
            file.mkdir();
        }

        file = new File("WaifuHax/modules");

        if(!file.exists()){
            file.mkdir();
        }

        file = new File("WaifuHax/cat");

        if(!file.exists())
        {
            file.mkdir();
        }

        for(CATEGORY cat : CATEGORY.values())
        {
            file = new File("WaifuHax/modules/" + cat.name);

            if(!file.exists())
            {
                ChatUtil.Log("Creating folder " + cat.name);
                file.mkdir();
            }
        }
    }

    public static void onKeyPressed(int keycode)
    {
        if(!bind)
        {
            for (AbstractModule mod : modules)
            {
                if (mod.key == keycode)
                {
                    mod.Toggle();
                }
            }
        }
        else
        {
            if(keycode != 335 && keycode != 257)
            {
                toBind.key = keycode;
                ChatUtil.SendMessage(toBind.name + " was bound to §4" + GLFW.glfwGetKeyName(keycode, GLFW.glfwGetKeyScancode(keycode)));
                bind = false;
                toBind.Save();
            }
        }
    }
    private static boolean bind;
    private static AbstractModule toBind;
    public static void bindModule(AbstractModule mod)
    {
        ChatUtil.SendMessage("bind mode enabled");
        toBind = mod;
        bind = true;
    }
}
