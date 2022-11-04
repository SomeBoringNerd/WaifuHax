package lol.waifuware.waifuhax.commands;

import lol.waifuware.waifuhax.commands.chatbot.whitelistadd;
import lol.waifuware.waifuhax.commands.chatbot.whitelistremove;
import lol.waifuware.waifuhax.commands.modules.botmode;
import lol.waifuware.waifuhax.commands.modules.toggle;

import java.util.concurrent.CopyOnWriteArrayList;

public class CommandManager
{
    public static CopyOnWriteArrayList<Command> Commands = new CopyOnWriteArrayList<Command>();

    public CommandManager()
    {
        Commands.add(new toggle("toggle"));
        Commands.add(new botmode("botmode"));
        Commands.add(new whitelistadd("whitelistadd"));
        Commands.add(new whitelistremove("whitelistremove"));
    }
}
