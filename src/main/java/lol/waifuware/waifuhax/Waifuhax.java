package lol.waifuware.waifuhax;

import lol.waifuware.waifuhax.Modules.ModuleManager;
import lol.waifuware.waifuhax.clickgui.ClickGUI;
import lol.waifuware.waifuhax.commands.CommandManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.api.ModInitializer;

public class Waifuhax implements ModInitializer
{
    public static final Logger LOGGER = LoggerFactory.getLogger("WaifuHax");

    @Override
    public void onInitialize()
    {
        Log("Hello World !");
        new ModuleManager();
        new CommandManager();
        new ClickGUI();
    }

    public static void Log(String message)
    {
        LOGGER.info("[WAIFUWARE INC] : " + message);
    }
}
