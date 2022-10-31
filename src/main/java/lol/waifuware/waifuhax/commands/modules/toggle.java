package lol.waifuware.waifuhax.commands.modules;

import lol.waifuware.waifuhax.Modules.Module;
import lol.waifuware.waifuhax.Modules.ModuleManager;
import lol.waifuware.waifuhax.Waifuhax;
import lol.waifuware.waifuhax.commands.Command;

import java.util.Locale;

public class toggle extends Command
{

    public toggle(String name)
    {
        super("toggle");
    }

    @Override
    public void Execute(String command)
    {
        String[] commandArgs = command.split(" ");

        int i = 0;
        for (String str : commandArgs)
        {
            Waifuhax.Log("commandArgs[" + i + "] = " + str);
            i++;
        }

        for (Module mod: ModuleManager.modules)
        {
            Waifuhax.Log(mod.name.toLowerCase() + "|" + commandArgs[1] + "");
            Waifuhax.Log(String.valueOf(mod.name.toLowerCase().equals(commandArgs[1].toLowerCase())));
            if(mod.name.toLowerCase().equals(commandArgs[1].toLowerCase()))
            {
                mod.Toggle();
            }
        }
    }
}
