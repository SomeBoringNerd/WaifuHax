package lol.waifuware.waifuhax.Modules;

import lol.waifuware.waifuhax.Modules.CHAT.Highlight;
import lol.waifuware.waifuhax.Modules.CHAT.Suffix;
import lol.waifuware.waifuhax.Modules.EXPLOITS.AntiHunger;
import lol.waifuware.waifuhax.Modules.EXPLOITS.ChestOpenExploit;
import lol.waifuware.waifuhax.Modules.GUI.ArrayList;
import lol.waifuware.waifuhax.Modules.GUI.Watermark;
import lol.waifuware.waifuhax.Modules.MOVEMENT.BoatFly;
import lol.waifuware.waifuhax.Modules.MOVEMENT.Sprint;
import lol.waifuware.waifuhax.Modules.MOVEMENT.VanillaFly;
import lol.waifuware.waifuhax.Waifuhax;
import lol.waifuware.waifuhax.clickgui.ClickGUI;
import lol.waifuware.waifuhax.util.ChatUtil;
import org.lwjgl.glfw.GLFW;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class ModuleManager
{

    public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>();

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
        Waifuhax.Log("Registering GUI modules");

        modules.add(new ArrayList("ArrayList", 0));
        modules.add(new Watermark("Watermark", 0));

        Waifuhax.Log("Registering chat modules");
        modules.add(new Highlight("Highlight", 0));
        modules.add(new Suffix("Suffix", 0));

        Waifuhax.Log("Registering exploits");
        modules.add(new AntiHunger("Anti Hunger", 0, CATEGORY.EXPLOIT));
        modules.add(new ChestOpenExploit("Chest Exploit", 0));

        Waifuhax.Log("Registering movement modules");
        modules.add(new BoatFly("BoatFly", 0, CATEGORY.MOVEMENT));
        modules.add(new VanillaFly("VanillaFly", 0, CATEGORY.MOVEMENT));
        modules.add(new Sprint("Sprint", 0, CATEGORY.MOVEMENT));
    }

    public List<Module> getModsInCat(CATEGORY cat)
    {
        List<Module> mods = new java.util.ArrayList<>();

        for(Module mod : modules){
            if(mod.cat == cat){
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
    }

    public static void onKeyPressed(int keycode)
    {
        if(!bind) {
            for (Module mod : modules)
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
                toBind.save();

            }
        }
    }
    private static boolean bind;
    private static Module toBind;
    public static void bindModule(Module mod)
    {
        ChatUtil.SendMessage("bind mode enabled");
        toBind = mod;
        bind = true;
    }
}
