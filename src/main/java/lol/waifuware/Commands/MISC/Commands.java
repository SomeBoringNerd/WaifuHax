package lol.waifuware.Commands.MISC;

import lol.waifuware.Commands.CommandManager;
import lol.waifuware.Commands.Interfaces.AbstractCommand;
import lol.waifuware.Commands.Interfaces.Command;
import lol.waifuware.Util.ChatUtil;

@Command(name = "command", usage = "-command", description = "Show commands and their usages")
public class Commands extends AbstractCommand
{
    @Override
    public void Execute(String[] command)
    {
        for(AbstractCommand cmd : CommandManager.Commands)
        {
            ChatUtil.SendMessage(cmd.getName() + " >> " + cmd.getUsage() + " >> " + cmd.getDesc());
            ChatUtil.ClearLine();
        }
    }
}
