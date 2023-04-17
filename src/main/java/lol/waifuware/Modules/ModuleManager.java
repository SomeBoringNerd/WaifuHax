package lol.waifuware.Modules;

import lol.waifuware.Modules.CHAT.Highlight;
import lol.waifuware.Modules.CHAT.Suffix;
import lol.waifuware.Modules.COMBAT.AutoTotem;
import lol.waifuware.Modules.EXPLOITS.AntiHunger;
import lol.waifuware.Modules.AUTOMATION.AutoFrameDupe;
import lol.waifuware.Modules.EXPLOITS.ChestOpenExploit;
import lol.waifuware.Modules.EXPLOITS.GameModeDetector;
import lol.waifuware.Modules.GUI.ArrayList;
import lol.waifuware.Modules.GUI.ClickGUI;
import lol.waifuware.Modules.GUI.Coordinates;
import lol.waifuware.Modules.MISC.PronounDB;
import lol.waifuware.Modules.MOVEMENT.Strafe;
import lol.waifuware.Modules.RENDER.FullBright;
import lol.waifuware.Modules.GUI.Watermark;
import lol.waifuware.Modules.MOVEMENT.BoatFly;
import lol.waifuware.Modules.MOVEMENT.Sprint;
import lol.waifuware.Modules.MOVEMENT.VanillaFly;
import lol.waifuware.Modules.RENDER.Xray;
import lol.waifuware.Waifuhax;
import lol.waifuware.Util.ChatUtil;
import org.lwjgl.glfw.GLFW;

import java.io.File;
import java.util.*;

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
        //@todo : auto-sort this shit

        modules.add(new AntiHunger());
        modules.add(new ArrayList());
        modules.add(new AutoTotem());
        modules.add(new AutoFrameDupe());
        modules.add(new BoatFly());
        modules.add(new ChestOpenExploit());
        modules.add(new ClickGUI());
        modules.add(new Coordinates());
        modules.add(new FullBright());
        modules.add(new GameModeDetector());
        modules.add(new Highlight());
        modules.add(new PronounDB());
        modules.add(new Sprint());
        modules.add(new Strafe());
        modules.add(new Suffix());
        modules.add(new Xray());
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
                Waifuhax.Log("Creating folder " + cat.name);
                file.mkdir();
            }
        }
    }

    public static void onKeyPressed(int keycode)
    {
        if(!bind) {
            for (AbstractModule mod : modules)
            {
                if (mod.key == keycode)
                {
                    mod.Toggle();
                }
            }
        }else{
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
