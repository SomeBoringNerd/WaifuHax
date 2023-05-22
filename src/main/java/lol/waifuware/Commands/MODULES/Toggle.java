package lol.waifuware.Commands.MODULES;

import lol.waifuware.Commands.Interfaces.AbstractCommand;
import lol.waifuware.Commands.Interfaces.BadCommandException;
import lol.waifuware.Commands.Interfaces.Command;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.ModuleManager;

import java.util.Map;

@Command(name = "toggle", usage = "-toggle <module>", description = "toggle a mod for you")
public class Toggle extends AbstractCommand
{
    @Override
    public void Execute(String[] command) throws BadCommandException {
        boolean done = false;
        if(command.length > 0){
            for (AbstractModule mod : ModuleManager.modules)
            {
                if(mod.name.toLowerCase().equals(command[1].toLowerCase()))
                {
                    mod.Toggle();
                    mod.Save();
                    done = true;
                }
            }

            if(!done){
                throw new BadCommandException("Module does not exist");
            }
        }else{
            throw new BadCommandException(getName() + " : " + getUsage());
        }
    }
}
