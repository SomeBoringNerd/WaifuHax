package lol.waifuware.Modules.GUI;

import lol.waifuware.Events.OnRenderScreen;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.Interfaces.Module;
import lol.waifuware.Modules.ModuleManager;
import lol.waifuware.Modules.CATEGORY;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

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
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        int ping = player.networkHandler.getPlayerListEntry(player.getUuid()).getLatency();
        MinecraftClient.getInstance().textRenderer.drawWithShadow(event.getMatrices(), "§c[§dActive Modules§c]§r | Ping : " + getColorFromPing(ping) + "§r", 5, 15, fromRGBA(255, 255, 255, 255 ));
        int i = 10;
        for (Map.Entry<String, AbstractModule> modMap : ModuleManager.modules.entrySet())
        {
            AbstractModule mod = modMap.getValue();
            if(mod.isEnabled){
                MinecraftClient.getInstance().textRenderer.drawWithShadow(event.getMatrices(), "§a> §r" + mod.name, 5, 15 + i, fromRGBA(255, 255, 255, 255));
                i += 10;
            }
        }
    }

    private String getColorFromPing(int ping)
    {
        if(ping <= 20){
            return "§2" + ping;
        }else if (ping <= 50){
            return "§a" + ping;
        }else if (ping <= 100){
            return "§6" + ping;
        }else if (ping <= 250){
            return "§c" + ping;
        }else{
            return "§4" + ping;
        }
    }
}

