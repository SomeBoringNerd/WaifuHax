package lol.waifuware.Commands;

import lol.waifuware.Commands.EXPLOITS.Crash;
import lol.waifuware.Commands.Interfaces.AbstractCommand;
import lol.waifuware.Commands.Interfaces.BadCommandException;
import lol.waifuware.Commands.MISC.Commands;
import lol.waifuware.Commands.MISC.Player;
import lol.waifuware.Commands.MISC.Pronoun;
import lol.waifuware.Commands.MISC.SettingCommand;
import lol.waifuware.Commands.MODULES.Help;
import lol.waifuware.Commands.MODULES.Set;
import lol.waifuware.Commands.MODULES.Toggle;
import lol.waifuware.Events.OnMessageSend;
import lol.waifuware.Util.ChatUtil;
import lol.waifuware.Waifuhax;
import meteordevelopment.orbit.EventHandler;

import java.util.concurrent.CopyOnWriteArrayList;

public class CommandManager
{
    public static CopyOnWriteArrayList<AbstractCommand> Commands = new CopyOnWriteArrayList<AbstractCommand>();

    public CommandManager()
    {
        Commands.add(new Crash());
        Commands.add(new Help());
        Commands.add(new Player());
        Commands.add(new Pronoun());
        Commands.add(new Set());
        Commands.add(new Toggle());

        Waifuhax.EVENT_BUS.subscribe(this);

        new SettingCommand();
    }

    @EventHandler
    public void MessageEvent(OnMessageSend event)
    {
        if(event.getMessage().startsWith("-"))
        {
            boolean found = false;
            for (AbstractCommand cmd : Commands)
            {
                if(event.getMessage().startsWith("-" + cmd.getName()))
                {
                    found = true;
                }
            }

            if(!found){
                ChatUtil.SendMessage("This command does not exist. do -help for a list of commands");
            }
        }
    }
}
