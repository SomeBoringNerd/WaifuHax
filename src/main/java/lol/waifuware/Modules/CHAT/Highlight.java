package lol.waifuware.Modules.CHAT;

import lol.waifuware.Events.OnMessageReceive;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.Interfaces.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;

@Module(name = "HighLight", key = 0, cat = CATEGORY.CHAT)
public class Highlight extends AbstractModule
{
    public Highlight()
    {
        super();

        Create();

        desc[0] = "Highlight your username in messages";
        isWorkInProgress = true;
    }

    @EventHandler
    public void OnMessageReceive(OnMessageReceive e)
    {
        e.setMessage(e.getMessage().replace(MinecraftClient.getInstance().player.getEntityName(), "§c§l" + MinecraftClient.getInstance().player.getEntityName() + "§r"));
    }
}
