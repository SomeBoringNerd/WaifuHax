package lol.waifuware.Modules.GUI;

import lol.waifuware.Events.OnRenderScreen;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.Interfaces.Module;
import lol.waifuware.Modules.ModuleManager;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Settings.ModeSetting;
import lol.waifuware.Util.ChatUtil;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.client.network.ClientPlayerEntity;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparingInt;

@Module(name = "ArrayList", key = 0, cat = CATEGORY.GUI)
public class ArrayList extends AbstractModule
{

    public ModeSetting sortMode = new ModeSetting("Sort mode", "alphabet", "how the arraylist is sorted", "size", "alphabet");

    public ArrayList() {
        super();

        addSetting(sortMode);

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
        int ping = -1;
        if(player.getUuid() != null)
        {
            if(player.networkHandler.getPlayerListEntry(player.getUuid()) != null)
                ping = player.networkHandler.getPlayerListEntry(player.getUuid()).getLatency();
        }

        MinecraftClient.getInstance().textRenderer.drawWithShadow(event.getMatrices(), "§c[§dActive Modules§c]§r | Ping : " + getColorFromPing(ping) + "§r | FPS : " + MinecraftClient.getInstance().getCurrentFps(), 5, 5 + Watermark.getWatermarkOffset(), fromRGBA(255, 255, 255, 255 ));
        int i = 10;
        for (AbstractModule mod : sortedModules())
        {
            if(mod.isEnabled && !mod.fakeModule()){
                MinecraftClient.getInstance().textRenderer.drawWithShadow(event.getMatrices(), "§a> §r" + mod.getDisplayName(), 5, 5 + i + Watermark.getWatermarkOffset(), fromRGBA(255, 255, 255, 255));
                i += 10;
            }
        }
    }

    public List<AbstractModule> sortedModules()
    {
        List<AbstractModule> enabled = getEnabledModules();

        if(sortMode.getIndex() == 0)
            enabled.sort(comparingInt(m -> (int) MinecraftClient.getInstance().textRenderer.getWidth(((AbstractModule)m).getName())).reversed());
        else
            enabled.sort(Comparator.comparing(AbstractModule::getName));

        return enabled;
    }

    public List<AbstractModule> getEnabledModules()
    {
        List<AbstractModule> mods = new java.util.ArrayList<>();

        for(AbstractModule mod : ModuleManager.modules){
            if(mod.isEnabled && !mod.fakeModule())
            {
                mods.add(mod);
            }
        }

        return mods;
    }

    @Override
    public String getDisplayName() {
        return name + " §c[§4" + sortMode.getModeAtIndex() + "§c]";
    }

    private String getColorFromPing(int ping)
    {
        if(ping <= -1){
            return "§4[CANT GET INFO]";
        }else if(ping <= 20){
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

