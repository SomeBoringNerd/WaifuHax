package lol.waifuware.waifuhax.commands.modules;

import lol.waifuware.waifuhax.GlobalVariables;
import lol.waifuware.waifuhax.commands.Command;
import lol.waifuware.waifuhax.util.ChatUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class botmode extends Command
{
    public botmode(String name) {
        super(name);
    }

    @Override
    public void Execute(String command)
    {
        //GlobalVariables.IsThisAccountABot = !GlobalVariables.IsThisAccountABot;
        //ChatUtil.SendMessage("Bot mode has been turned " + (GlobalVariables.IsThisAccountABot ? "§aON" : "§4OFF"));
    }
}
