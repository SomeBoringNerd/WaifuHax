package lol.waifuware.Commands.MODULES;

import lol.waifuware.Commands.Interfaces.AbstractCommand;
import lol.waifuware.Commands.Interfaces.Command;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.ModuleManager;
import lol.waifuware.Util.ChatUtil;

import java.util.Locale;
import java.util.Map;
@Command(name = "set")
public class Set extends AbstractCommand
{
    @Override
    public void Execute(String[] command)
    {
        if(command.length < 2){
            ChatUtil.SendMessage("§4ERROR : NOT ENOUGH ARGUMENTS PROVIDED !§r");
        }else{
            for (Map.Entry<String, AbstractModule> modMap : ModuleManager.modules.entrySet())
            {
                AbstractModule mod = modMap.getValue();
                if(mod.name.toLowerCase(Locale.ROOT).trim().equals(command[1].toLowerCase(Locale.ROOT).trim()))
                {
                    if(command[2].toLowerCase().trim().equals("key"))
                    {
                        ModuleManager.bindModule(mod);
                        ChatUtil.SendMessage("Press any key to bind the module " + command[1]);
                    }else{
                        for(Map.Entry<String, Object> entry : mod.settings.entrySet())
                        {
                            if(entry.getKey().toLowerCase(Locale.ROOT).trim().equals(command[2].toString().toLowerCase(Locale.ROOT).trim()))
                            {
                                entry.setValue(command[3]);
                                mod.Save();
                                ChatUtil.SendMessage("Set " + command[2] + " from module " + command[1] + " to " + command[3]);
                            }
                        }
                    }
                }
            }
        }
    }
}
