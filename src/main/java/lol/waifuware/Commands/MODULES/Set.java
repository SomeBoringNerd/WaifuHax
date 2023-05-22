package lol.waifuware.Commands.MODULES;

import lol.waifuware.Commands.Interfaces.AbstractCommand;
import lol.waifuware.Commands.Interfaces.BadCommandException;
import lol.waifuware.Commands.Interfaces.Command;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.ModuleManager;
import lol.waifuware.Settings.BooleanSetting;
import lol.waifuware.Settings.IntSetting;
import lol.waifuware.Settings.ModeSetting;
import lol.waifuware.Settings.Setting;
import lol.waifuware.Util.ChatUtil;

import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

@Command(name = "set", usage = "-set <module> <setting name> <value>", description = "change mod settings")
public class Set extends AbstractCommand
{
    @Override
    public void Execute(String[] command) throws BadCommandException {
        if(command.length < 3){
            throw new BadCommandException(getName() + " : " + getUsage());
        }else{
            for (AbstractModule mod : ModuleManager.modules)
            {
                if(mod.name.toLowerCase(Locale.ROOT).trim().equals(command[1].toLowerCase(Locale.ROOT).trim()))
                {
                    if(command[2].toLowerCase().trim().equals("key"))
                    {
                        ModuleManager.bindModule(mod);
                        ChatUtil.SendMessage("Press any key to bind the module " + command[1]);
                    }else{
                        if(command.length < 4){
                            throw new BadCommandException(getName() + " : " + getUsage());
                        }
                        for(Setting setting : mod.getSettings())
                        {
                            if(setting.name.toLowerCase().trim().equals(command[2].toLowerCase().trim()))
                            {
                                if (setting instanceof IntSetting e)
                                {
                                    double j;
                                    Pattern p = Pattern.compile("0x([A-F 0-9])\\w+");
                                    if(p.matcher(command[3]).find())
                                    {
                                        j = (Integer.parseInt(command[3].replace("0x", ""),16));
                                    }else{
                                        j = Long.parseLong(command[3]);
                                    }
                                    e.setValue(Double.parseDouble("" + j));
                                } else if (setting instanceof BooleanSetting e)
                                {
                                    e.setEnabled(command[3].toLowerCase().equals("true"));
                                } else if (setting instanceof ModeSetting e) {
                                    e.setMode((command[3]));
                                }

                                ChatUtil.SendMessage("Set " + command[2] + " from module " + command[1] + " to " + command[3]);
                            }
                        }
                    }
                }
            }
        }
    }
}
