package lol.waifuware.waifuhax.commands;

import lol.waifuware.waifuhax.commands.modules.set;
import lol.waifuware.waifuhax.commands.modules.toggle;

import java.util.concurrent.CopyOnWriteArrayList;

public class CommandManager
{
    public static CopyOnWriteArrayList<Command> Commands = new CopyOnWriteArrayList<Command>();

    public CommandManager()
    {
        Commands.add(new toggle("toggle"));
        Commands.add(new set("set"));
    }
}
