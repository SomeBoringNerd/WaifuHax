package lol.waifuware.Commands.MISC;

import lol.waifuware.Events.OnMessageSend;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.ModuleManager;
import lol.waifuware.Settings.Setting;
import lol.waifuware.Util.ChatUtil;
import lol.waifuware.Waifuhax;
import meteordevelopment.orbit.EventHandler;

public class SettingCommand
{
    public SettingCommand()
    {
        Waifuhax.EVENT_BUS.subscribe(this);
    }

    @EventHandler
    public void onCommand(OnMessageSend event)
    {
        String[] args = event.getMessage().split(" ");
        args[0] = args[0].replace("-", "");

        for(AbstractModule mod : ModuleManager.modules)
        {
            if (event.getMessage().toLowerCase().startsWith("-" + mod.name.toLowerCase())) {
                if (!mod.settings.isEmpty()) {
                    ChatUtil.SendMessage("§6--------------------------------------");
                    ChatUtil.SendMessage("§7Settings for the module §4§l" + mod.name);
                    ChatUtil.SendMessage("§6--------------------------------------");
                    for (Setting set : mod.settings) {
                        ChatUtil.SendMessage("§8>§7>§r" + set.name + " : " + set.Description);
			            ChatUtil.ClearLine();
                    }
                } else {
                    ChatUtil.SendMessage("This module do not contain settings");
                }
            }
        }
    }
}
