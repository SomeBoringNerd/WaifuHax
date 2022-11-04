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
        GlobalVariables.IsThisAccountABot = !GlobalVariables.IsThisAccountABot;
        ChatUtil.SendMessage("Bot mode has been turned " + (GlobalVariables.IsThisAccountABot ? "§aON" : "§4OFF"));
        /*
        if(GlobalVariables.IsThisAccountABot){
            MinecraftClient.getInstance().player.sendChatMessage(">This account is being used as a bot by FeurGroup (commands available for everyone will eventually be added)", Text.literal(">This account is being used as a bot by FeurGroup (commands available for everyone will eventually be added)"));
        }else{
            MinecraftClient.getInstance().player.sendChatMessage(">This account is no longer being used as a bot", Text.literal(">This account is no longer being used as a bot"));
        }*/
    }
}
