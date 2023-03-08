package lol.waifuware.Modules.GUI;

import lol.waifuware.Events.OnRenderScreen;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.Interfaces.Module;
import lol.waifuware.Modules.ModuleManager;
import lol.waifuware.Modules.CATEGORY;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;

import java.util.Map;

@Module(name = "ArrayList", key = 0, cat = CATEGORY.GUI)
public class ArrayList extends AbstractModule
{
    public ArrayList() {
        super();
        Create();

        desc[0] = "Show toggled modules";
    }

    private int fromRGBA(int r, int g, int b, int a) {
        return (r << 16) + (g << 8) + (b) + (a << 24);
    }
    @EventHandler
    public void Render(OnRenderScreen event)
    {
        MinecraftClient.getInstance().textRenderer.drawWithShadow(event.getMatrices(), "Active modules : ", 5, 15, fromRGBA(255, 255, 255, 255 ));
        int i = 10;
        for (Map.Entry<String, AbstractModule> modMap : ModuleManager.modules.entrySet())
        {
            AbstractModule mod = modMap.getValue();
            if(mod.isEnabled){
                MinecraftClient.getInstance().textRenderer.drawWithShadow(event.getMatrices(), "> " + mod.name, 5, 15 + i, fromRGBA(255, 255, 255, 255));
                i += 10;
            }
        }
    }
}
