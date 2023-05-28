package lol.waifuware.Commands.MODULES;

import lol.waifuware.Commands.CommandManager;
import lol.waifuware.Commands.Interfaces.AbstractCommand;
import lol.waifuware.Commands.Interfaces.BadCommandException;
import lol.waifuware.Commands.Interfaces.Command;
import lol.waifuware.Util.ChatUtil;

@Command(name = "help", usage = "-help", description = "provide a list of commands")
public class Help extends AbstractCommand
{
    @Override
    public void Execute(String[] command) throws BadCommandException
    {
        ChatUtil.SendMessage("§lList of commands in waifuware");
        for(AbstractCommand cmd : CommandManager.Commands)
        {
            ChatUtil.SendMessage(cmd.getUsage() + " : " + cmd.getDesc());
        }
        ChatUtil.SendMessage("(messages that start with - wont be sent)");
    }
}
