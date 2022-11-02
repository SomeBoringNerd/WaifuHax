package lol.waifuware.waifuhax.Modules;

import lol.waifuware.waifuhax.Modules.CHAT.Highlight;
import lol.waifuware.waifuhax.Modules.CHAT.Suffix;
import lol.waifuware.waifuhax.Modules.GUI.ArrayList;
import lol.waifuware.waifuhax.Modules.GUI.Watermark;
import lol.waifuware.waifuhax.Waifuhax;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ModuleManager
{

    public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>();

    // register modules here
    public ModuleManager()
    {
        CheckForFolder();

        Waifuhax.LOGGER.info("Registering GUI modules");

        modules.add(new ArrayList("ArrayList", 0));
        modules.add(new Highlight("HighLight", 0));
        modules.add(new Suffix("Suffix", 0));
        modules.add(new Watermark("Watermark", 0));





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
}
