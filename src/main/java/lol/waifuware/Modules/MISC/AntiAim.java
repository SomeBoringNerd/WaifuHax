package lol.waifuware.Modules.MISC;

import lol.waifuware.Events.OnTickEvent;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.Interfaces.Module;
import lol.waifuware.Settings.IntSetting;
import lol.waifuware.Util.ChatUtil;
import lol.waifuware.Util.RotationUtil;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

@Module(name = "AntiAim", key = 0, cat = CATEGORY.MISC)
public class AntiAim extends AbstractModule
{
    public IntSetting speed =  new IntSetting("Speed", 0, 100, 20, 1, "Speed at which you spin", "-s");
    int delay = 0;

    public AntiAim()
    {
        super();
        addSettings(speed);
        Create();
    }

    @EventHandler
    public void OnTick(OnTickEvent e)
    {
        delay--;
        if (delay > 0)
        {
            return;
        }
        delay = speed.getValueInt();
        RotationUtil.setClientPitch((float) ((Math.random() * 60) - 30));
        RotationUtil.setClientYaw((float) (Math.random() * 360));
        PlayerMoveC2SPacket rot = new PlayerMoveC2SPacket.LookAndOnGround(RotationUtil.getClientYaw(), RotationUtil.getClientPitch(), MinecraftClient.getInstance().player.isOnGround());
        MinecraftClient.getInstance().getNetworkHandler().sendPacket(rot);
    }
}
