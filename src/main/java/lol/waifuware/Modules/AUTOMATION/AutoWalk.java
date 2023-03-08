package lol.waifuware.Modules.AUTOMATION;

import lol.waifuware.Events.OnTickEvent;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.Interfaces.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;

@Module(name = "AutoWalk", key = 0, cat = CATEGORY.BOT)
public class AutoWalk extends AbstractModule
{
    public AutoWalk()
    {
        super();
        Create();
        desc[0] = "Walk so you dont have too";
    }

    @EventHandler
    public void onTick(OnTickEvent e)
    {
        if(!(MinecraftClient.getInstance().player != null && MinecraftClient.getInstance().world != null)) return;
    }
}

