package lol.waifuware.waifuhax.Modules;

import lol.waifuware.waifuhax.Modules.CHAT.Highlight;
import lol.waifuware.waifuhax.Modules.CHAT.Suffix;
import lol.waifuware.waifuhax.Modules.COMBAT.AutoTotem;
import lol.waifuware.waifuhax.Modules.EXPLOITS.AntiHunger;
import lol.waifuware.waifuhax.Modules.EXPLOITS.AutoFrameDupe;
import lol.waifuware.waifuhax.Modules.EXPLOITS.ChestOpenExploit;
import lol.waifuware.waifuhax.Modules.GUI.ArrayList;
import lol.waifuware.waifuhax.Modules.GUI.ClickGUI;
import lol.waifuware.waifuhax.Modules.RENDER.FullBright;
import lol.waifuware.waifuhax.Modules.GUI.Watermark;
import lol.waifuware.waifuhax.Modules.MOVEMENT.BoatFly;
import lol.waifuware.waifuhax.Modules.MOVEMENT.Sprint;
import lol.waifuware.waifuhax.Modules.MOVEMENT.VanillaFly;
import lol.waifuware.waifuhax.Modules.RENDER.Xray;
import lol.waifuware.waifuhax.Waifuhax;
import lol.waifuware.waifuhax.util.ChatUtil;
import org.lwjgl.glfw.GLFW;

import java.io.File;
import java.util.*;

public class ModuleManager
{

    public static HashMap<String, Module> modules = new HashMap<>();

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
        modules.put("ArrayList", new ArrayList("ArrayList", 0));
        modules.put("Watermark", new Watermark("Watermark", 0));
        modules.put("ClickGUI", new ClickGUI("ClickGUI", GLFW.GLFW_KEY_RIGHT_SHIFT, CATEGORY.GUI));

        Waifuhax.Log("Registering render modules");
        modules.put("FullBright", new FullBright("FullBright", 0, CATEGORY.RENDER));
        modules.put("Xray", new Xray("Xray", 0, CATEGORY.RENDER));

        Waifuhax.Log("Registering automation modules");
        modules.put("AutoFrameDupe", new AutoFrameDupe("Auto Frame Dupe", 0, CATEGORY.BOT));

        Waifuhax.Log("Registering chat modules");
        modules.put("Highlight", new Highlight("Highlight", 0));
        modules.put("Suffix", new Suffix("Suffix", 0));

        Waifuhax.Log("Registering combat modules");
        modules.put("AutoTotem", new AutoTotem("AutoTotem", 0, CATEGORY.COMBAT));

        Waifuhax.Log("Registering exploits");
        modules.put("AntiHuger", new AntiHunger("Anti Hunger", 0, CATEGORY.EXPLOIT));
        modules.put("ChestOpenExploit", new ChestOpenExploit("Chest Exploit", 0));

        Waifuhax.Log("Registering movement modules");
        modules.put("BoatFly", new BoatFly("BoatFly", 0, CATEGORY.MOVEMENT));
        modules.put("VanillaFly", new VanillaFly("VanillaFly", 0, CATEGORY.MOVEMENT));
        modules.put("Sprint", new Sprint("Sprint", 0, CATEGORY.MOVEMENT));

        List<Module> moduleToSort = new java.util.ArrayList<>(modules.values());

        Collections.sort(moduleToSort, Comparator.comparing(Module::getName));

        modules.clear();

        for (Module mod: moduleToSort)
        {
            modules.put(mod.getName(), mod);
        }
    }

    public List<Module> getModsInCat(CATEGORY cat)
    {
        List<Module> mods = new java.util.ArrayList<>();

        for(Map.Entry<String, Module> mod : modules.entrySet()){
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
            for (Map.Entry<String, Module> mod : modules.entrySet())
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
