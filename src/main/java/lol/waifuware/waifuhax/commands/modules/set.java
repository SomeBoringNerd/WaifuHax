package lol.waifuware.waifuhax.commands.modules;

import lol.waifuware.waifuhax.Modules.Module;
import lol.waifuware.waifuhax.Modules.ModuleManager;
import lol.waifuware.waifuhax.commands.Command;
import lol.waifuware.waifuhax.util.ChatUtil;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

import java.util.Locale;
import java.util.Map;

public class set extends Command
{

    public set(String name)
    {
        super(name);
    }

    @Override
    public void Execute(String command)
    {
        String[] commandArgs = command.split(" ");

        if(commandArgs.length < 2){
            ChatUtil.SendMessage("§4ERROR : NOT ENOUGH ARGUMENTS PROVIDED !§r");
        }else{
            for (Module mod: ModuleManager.modules)
            {
                if(mod.name.toLowerCase(Locale.ROOT).trim().equals(commandArgs[1].toLowerCase(Locale.ROOT).trim()))
                {
                    if(commandArgs[2].toLowerCase().trim().equals("key"))
                    {
                        ModuleManager.bindModule(mod);
                        ChatUtil.SendMessage("Press any key to bind the module " + commandArgs[0]);
                    }else{
                        for(Map.Entry<String, Object> entry : mod.settings.entrySet())
                        {
                            if(entry.getKey().toLowerCase(Locale.ROOT).trim().equals(commandArgs[2].toString().toLowerCase(Locale.ROOT).trim()))
                            {
                                entry.setValue(commandArgs[2]);
                                mod.save();
                                ChatUtil.SendMessage("Set " + commandArgs[1] + " from module " + commandArgs[0] + " to " + commandArgs[2]);
                            }
                        }
                    }
                }
            }
        }
    }
}
