package lol.waifuware.Modules.GUI;

import lol.waifuware.Events.OnRenderScreen;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.Interfaces.Module;
import lol.waifuware.Settings.BooleanSetting;
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

import java.util.Random;

@Module(name = "Coordinate", key = 0, cat = CATEGORY.GUI)
public class Coordinates extends AbstractModule
{

    public BooleanSetting FakeCoordinates = new BooleanSetting("FakeCoordinates", false, "Display fake coordinates", "--f");

    public Coordinates()
    {
        addSetting(FakeCoordinates);
        Create();

        desc[0] = "Show your coordinates along with the";
        desc[1] = "direction you are looking at";
    }

    @EventHandler
    public void onRender(OnRenderScreen event)
    {
        Random r = new Random();
        int low = -9999999;
        int high = 9999999;
        int result = r.nextInt(high-low) + low;

        int x = (FakeCoordinates.getEnabled()) ? result : MinecraftClient.getInstance().player.getBlockX();

        r = new Random();
        low = -2364367;
        high = 346931;
        result = r.nextInt(high-low) + low;

        int z = (FakeCoordinates.getEnabled()) ? result : MinecraftClient.getInstance().player.getBlockZ();
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
