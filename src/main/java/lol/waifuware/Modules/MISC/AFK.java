package lol.waifuware.Modules.MISC;

import lol.waifuware.Events.OnMessageReceive;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.Interfaces.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

@Module(name = "afk", key = 0, cat = CATEGORY.MISC)
public class AFK extends AbstractModule
{
    public AFK()
    {
        super();
        addSettings();
        Create();
    }

    @EventHandler
    public void onMessageReceive(OnMessageReceive e)
    {
        if(e.getSender() != MinecraftClient.getInstance().player.getEntityName() && e.getMessage().contains(MinecraftClient.getInstance().player.getEntityName()))
        {
            MinecraftClient.getInstance().getNetworkHandler().sendChatMessage(">I'm currently afk and/or dont have chat enabled, please contact me later");
        }
    }
}
