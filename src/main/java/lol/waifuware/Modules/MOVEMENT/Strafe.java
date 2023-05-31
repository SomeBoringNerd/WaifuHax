package lol.waifuware.Modules.MOVEMENT;

import lol.waifuware.Events.OnTickEvent;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.Interfaces.Module;
import lol.waifuware.Settings.IntSetting;
import lol.waifuware.Settings.NumberType;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.util.math.Vec3d;

@Module(name = "Strafe", key = 0, cat = CATEGORY.MOVEMENT)
public class Strafe extends AbstractModule
{

    private MinecraftClient mc;

    public IntSetting strafeSpeed = new IntSetting("strafe speed", 0.15, 1, 0.25, 0.05, "Speed at which you go", "ss", NumberType.FLOAT);
    public Strafe()
    {
        addSetting(strafeSpeed);

        Create();
        mc = MinecraftClient.getInstance();
    }

    @Override
    public String getDisplayName() {
        return name + " §c[§r§4" + strafeSpeed.getValueFloat() + "§c]";
    }

    @EventHandler
    public void onTick(OnTickEvent event)
    {
        if(mc.player != null) {
            if ((mc.player.forwardSpeed != 0 || mc.player.sidewaysSpeed != 0))
            {
                if (!mc.player.isSprinting() && !Sprint.isEnabled())
                {
                    mc.player.setSprinting(true);
                }

                mc.player.setVelocity(new Vec3d(0, mc.player.getVelocity().y, 0));
                mc.player.updateVelocity(strafeSpeed.getValueFloat(), new Vec3d(mc.player.sidewaysSpeed, 0, mc.player.forwardSpeed));

                double vel = Math.abs(mc.player.getVelocity().getX()) + Math.abs(mc.player.getVelocity().getZ());

                if (vel >= 0.12 && mc.player.isOnGround()) {
                    mc.player.updateVelocity(0.1f, new Vec3d(mc.player.sidewaysSpeed, 0, mc.player.forwardSpeed));
                    mc.player.jump();
                }
            }
        }
    }
}
