package lol.waifuware.Modules.MOVEMENT;

import lol.waifuware.Events.OnTickEvent;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.Interfaces.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;
@Module(name = "Sprint", key = 0, cat = CATEGORY.MOVEMENT)
public class Sprint extends AbstractModule
{
    public Sprint()
    {
        super();
        Create();
    }

    @EventHandler
    public void onTick(OnTickEvent e){
        if(isEnabled && MinecraftClient.getInstance().player != null)
        {
            if(!MinecraftClient.getInstance().player.isSprinting())
            {
                MinecraftClient.getInstance().player.setSprinting(true);
            }
        }
    }
}
