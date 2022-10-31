package lol.waifuware.waifuhax.Modules;

import lol.waifuware.waifuhax.Modules.GUI.Watermark;
import lol.waifuware.waifuhax.Waifuhax;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ModuleManager
{

    public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>();

    // register modules here
    public ModuleManager()
    {
        Waifuhax.LOGGER.info("Registering GUI modules");

        modules.add(new Watermark("Watermark", 0));
    }
}
