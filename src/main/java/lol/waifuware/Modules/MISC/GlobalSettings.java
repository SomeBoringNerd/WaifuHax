package lol.waifuware.Modules.MISC;

import lol.waifuware.Events.OnTickEvent;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.Interfaces.Module;
import lol.waifuware.Settings.BooleanSetting;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Module(name = "Global Settings", key = 0, cat = CATEGORY.MISC)
public class GlobalSettings extends AbstractModule
{

    public final static BooleanSetting Description = new BooleanSetting("DescriptionHUD", true, "HUD that show what a module do", "ignore");
    public final static BooleanSetting MetaData = new BooleanSetting("Arraylist Metadata", true, "additional info about a module in arraylist", "-am");
    public final static BooleanSetting ToggleMessage = new BooleanSetting("Toggle messages", true, "Send a message in chat (client side) when a module is toggled", "ignore");

    public GlobalSettings()
    {
        super(true);
        addSettings(Description, ToggleMessage, MetaData);
        desc[0] = "Global settings that are used all over";
        desc[1] = "the client";
        Create();
    }

    @Override
    public void onDisable()
    {
        // I need this code to run.
        Toggle(true);
    }

    private final List<PlayerListEntry> entityList = new ArrayList<>();
    private final List<PlayerListEntry> oldPlayerList = new ArrayList<>();

    @EventHandler
    private void OnTickEvent(OnTickEvent e)
    {
        if(MinecraftClient.getInstance().world == null) return;
        if(MinecraftClient.getInstance().player == null) return;
        if(MinecraftClient.getInstance().isInSingleplayer()) return;

        entityList.clear();

        for (PlayerListEntry player : MinecraftClient.getInstance().getNetworkHandler().getPlayerList())
        {
            // easy, this mean a player just logged in
            if(!entityList.contains(player))
            {
                entityList.add(player);
            }
        }
    }
}
