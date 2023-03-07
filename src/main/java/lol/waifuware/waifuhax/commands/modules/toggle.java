package lol.waifuware.waifuhax.commands.modules;

import lol.waifuware.waifuhax.Modules.Module;
import lol.waifuware.waifuhax.Modules.ModuleManager;
import lol.waifuware.waifuhax.Waifuhax;
import lol.waifuware.waifuhax.commands.Command;
import lol.waifuware.waifuhax.util.ChatUtil;

import java.util.Locale;
import java.util.Map;

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

        for (Map.Entry<String, Module> modMap : ModuleManager.modules.entrySet())
        {
            Module mod = modMap.getValue();
            Waifuhax.Log(mod.name.toLowerCase() + "|" + commandArgs[1] + "");
            Waifuhax.Log(String.valueOf(mod.name.toLowerCase().equals(commandArgs[1].toLowerCase())));
            if(mod.name.toLowerCase().equals(commandArgs[1].toLowerCase()))
            {
                mod.Toggle();
                mod.save();
                ChatUtil.SendMessage("Module " + mod.name + " was toggled " + (mod.isEnabled ? "§aON§r" : "§4OFF§r"));
            }
        }
    }
}
