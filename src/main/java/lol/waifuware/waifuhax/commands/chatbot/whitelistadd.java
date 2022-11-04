package lol.waifuware.waifuhax.commands.chatbot;

import lol.waifuware.waifuhax.Modules.BOT.AutoTpa;
import lol.waifuware.waifuhax.commands.Command;

public class whitelistadd extends Command {

    public whitelistadd(String name) {
        super(name);
    }

    @Override
    public void Execute(String command)
    {
        String[] array = command.split(" ");

        if(array.length >= 2){
            AutoTpa.whitelist.add("player §4" + array[1] + "§r has been added to the whitelist");
        }
    }
}
