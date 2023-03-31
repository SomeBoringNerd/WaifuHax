package lol.waifuware.Commands.MISC;

import lol.waifuware.Events.OnMessageReceive;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.ModuleManager;
import lol.waifuware.Settings.Setting;
import lol.waifuware.Util.ChatUtil;
import lol.waifuware.Waifuhax;
import meteordevelopment.orbit.EventHandler;

import java.util.Map;

public class SettingCommand
{
    public SettingCommand()
    {
        Waifuhax.EVENT_BUS.subscribe(this);
    }

    @EventHandler
    public void onCommand(OnMessageReceive event)
    {
        String[] args = event.getMessage().split(" ");
        args[0] = args[0].replace("-", "");

        for(Map.Entry<String, AbstractModule> mods : ModuleManager.modules.entrySet())
        {
            if (event.getMessage().toLowerCase().startsWith("-" + mods.getValue().name.toLowerCase())) {
                if (!mods.getValue().settings.isEmpty()) {
                    ChatUtil.SendMessage("§6--------------------------------------");
                    ChatUtil.SendMessage("§7Settings for the module §4§l" + mods.getValue().name);
                    ChatUtil.SendMessage("§6--------------------------------------");
                    for (Setting set : mods.getValue().settings) {
                        ChatUtil.SendMessage("§8>§7>§r" + set.name + " : " + set.Description);
			ChatUtil.SendMessage("");
                    }
                } else {
                    ChatUtil.SendMessage("This module do not contain settings");
                }
            }
        }
    }
}
