package lol.waifuware.Modules.GUI;

import lol.waifuware.Events.OnRenderScreen;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.Interfaces.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;

@Module(name = "Watermark", key = 0, cat = CATEGORY.GUI)
public class Watermark extends AbstractModule
{
    public Watermark()
    {
        super();

        Create();

        desc[0] = "Fancy text on your screen";
    }

    @EventHandler
    public void Render(OnRenderScreen event)
    {
        String name = MinecraftClient.getInstance().player.getEntityName();
        MinecraftClient.getInstance().textRenderer.drawWithShadow(event.getMatrices(), "WaifuHax V2 (WIP) | " + name, 5, 5, 0xFFFFFF);
    }
}
