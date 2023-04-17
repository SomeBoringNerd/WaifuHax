package lol.waifuware.Modules.GUI;

import lol.waifuware.Events.OnRenderScreen;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.Interfaces.Module;
import lol.waifuware.Util.GlobalUtil;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.option.ChatVisibility;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.util.annotation.Debug;
import net.minecraft.util.math.Direction;

@Module(name = "Coordinate", key = 0, cat = CATEGORY.GUI)
public class Coordinates extends AbstractModule
{
    public Coordinates()
    {
        Create();
    }

    @EventHandler
    public void onRender(OnRenderScreen event)
    {
        int x = MinecraftClient.getInstance().player.getBlockX();
        int z = MinecraftClient.getInstance().player.getBlockZ();
        int y = MinecraftClient.getInstance().player.getBlockY();

        if(MinecraftClient.getInstance().cameraEntity != null)
        {
            Direction direction = MinecraftClient.getInstance().cameraEntity.getHorizontalFacing();

            if(!(MinecraftClient.getInstance().currentScreen instanceof ChatScreen))
            {
                MinecraftClient.getInstance().textRenderer.drawWithShadow(event.getMatrices(), "§c[§7" + getFormatedDirection(direction) + "§c] §dX : " + x + " §f|§d Y : " + y + " §f|§d Z : " + z, 5, MinecraftClient.getInstance().getWindow().getScaledHeight() - 12,0xFFFFFF);
            }else
            {
                MinecraftClient.getInstance().textRenderer.drawWithShadow(event.getMatrices(), "§c[§7" + getFormatedDirection(direction) + "§c] §dX : " + x + " §f|§d Y : " + y + " §f|§d Z : " + z, 5, MinecraftClient.getInstance().getWindow().getScaledHeight() - 27,0xFFFFFF);
            }
        }
    }

    private String getFormatedDirection(Direction direction)
    {
        switch(direction){
            default : return "?????";
            case NORTH: return "North";
            case SOUTH: return "South";
            case EAST: return "East";
            case WEST: return "West";
        }
    }
}
