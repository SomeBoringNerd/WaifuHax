package lol.waifuware.Modules.MOVEMENT;

import lol.waifuware.Events.OnTickEvent;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.Interfaces.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

@Module(name = "VanillaFly", key = 0, cat = CATEGORY.MOVEMENT)
public class VanillaFly extends AbstractModule
{
    public VanillaFly()
    {
        super();
        desc[0] = "Fly on bad servers";
        Create();
    }

    @EventHandler
    public void onTick(OnTickEvent e){
        assert MinecraftClient.getInstance().player != null;
        if(isEnabled)
        {
            MinecraftClient.getInstance().player.getAbilities().flying = true;
        }
    }



    @Override
    public void onDisable() {
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.getAbilities().flying = false;
    }
}
