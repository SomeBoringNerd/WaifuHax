package lol.waifuware.Modules;

import lol.waifuware.Modules.CHAT.Highlight;
import lol.waifuware.Modules.CHAT.Suffix;
import lol.waifuware.Modules.COMBAT.AutoTotem;
import lol.waifuware.Modules.EXPLOITS.AntiHunger;
import lol.waifuware.Modules.EXPLOITS.AutoFrameDupe;
import lol.waifuware.Modules.EXPLOITS.ChestOpenExploit;
import lol.waifuware.Modules.GUI.ArrayList;
import lol.waifuware.Modules.GUI.ClickGUI;
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

    public static HashMap<String, AbstractModule> modules = new HashMap<>();

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
        modules.put("ArrayList", new ArrayList());
        modules.put("Watermark", new Watermark());
        modules.put("ClickGUI", new ClickGUI());

        Waifuhax.Log("Registering render modules");
        modules.put("FullBright", new FullBright());
        modules.put("Xray", new Xray());

        Waifuhax.Log("Registering automation modules");
        modules.put("AutoFrameDupe", new AutoFrameDupe());

        Waifuhax.Log("Registering chat modules");
        modules.put("Highlight", new Highlight());
        modules.put("Suffix", new Suffix());

        Waifuhax.Log("Registering combat modules");
        modules.put("AutoTotem", new AutoTotem());

        Waifuhax.Log("Registering exploits");
        modules.put("AntiHuger", new AntiHunger());
        modules.put("ChestOpenExploit", new ChestOpenExploit());

        Waifuhax.Log("Registering movement modules");
        modules.put("BoatFly", new BoatFly());
        modules.put("VanillaFly", new VanillaFly());
        modules.put("Sprint", new Sprint());

        List<AbstractModule> moduleToSort = new java.util.ArrayList<>(modules.values());

        Collections.sort(moduleToSort, Comparator.comparing(AbstractModule::getName));

        modules.clear();

        for (AbstractModule mod: moduleToSort)
        {
            modules.put(mod.getName(), mod);
        }
    }

    public List<AbstractModule> getModsInCat(CATEGORY cat)
    {
        List<AbstractModule> mods = new java.util.ArrayList<>();

        for(Map.Entry<String, AbstractModule> mod : modules.entrySet()){
            if(mod.getValue().cat == cat)
            {
                mods.add(mod.getValue());
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
            for (Map.Entry<String, AbstractModule> mod : modules.entrySet())
            {
                if (mod.getValue().key == keycode)
                {
                    mod.getValue().Toggle();
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
