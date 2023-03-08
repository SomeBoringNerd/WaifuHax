package lol.waifuware.Commands.MODULES;

import lol.waifuware.Commands.Interfaces.AbstractCommand;
import lol.waifuware.Commands.Interfaces.Command;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.ModuleManager;

import java.util.Map;

@Command(name = "toggle")
public class Toggle extends AbstractCommand
{
    @Override
    public void Execute(String[] command)
    {
        for (Map.Entry<String, AbstractModule> modMap : ModuleManager.modules.entrySet())
        {
            AbstractModule mod = modMap.getValue();

            if(mod.name.toLowerCase().equals(command[1].toLowerCase()))
            {
                mod.Toggle();
                mod.Save();
            }
        }
    }
}
