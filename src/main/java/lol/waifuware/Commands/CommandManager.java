package lol.waifuware.Commands;

import lol.waifuware.Commands.EXPLOITS.Crash;
import lol.waifuware.Commands.Interfaces.AbstractCommand;
import lol.waifuware.Commands.MISC.Pronoun;
import lol.waifuware.Commands.MISC.SettingCommand;
import lol.waifuware.Commands.MODULES.Set;
import lol.waifuware.Commands.MODULES.Toggle;

import java.util.concurrent.CopyOnWriteArrayList;

public class CommandManager
{
    public static CopyOnWriteArrayList<AbstractCommand> Commands = new CopyOnWriteArrayList<AbstractCommand>();

    public CommandManager()
    {
        Commands.add(new Crash());
        Commands.add(new Toggle());
        Commands.add(new Set());
        Commands.add(new Pronoun());

        new SettingCommand();
    }
}
