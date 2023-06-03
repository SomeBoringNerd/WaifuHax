package lol.waifuware.Mixin;

import lol.waifuware.Events.OnPlayerConnect;
import lol.waifuware.Events.OnPlayerDisconnect;
import lol.waifuware.Util.ChatUtil;
import lol.waifuware.Waifuhax;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.network.packet.s2c.play.PlayerListS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerRemoveS2CPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin
{
    @Shadow @Final private Map<UUID, PlayerListEntry> playerListEntries;
    String previous_out = "";

    String previous_in = "";
    @Inject(method = "onPlayerRemove", at = @At("HEAD"))
    public void onPlayerDisconnect(PlayerRemoveS2CPacket packet, CallbackInfo ci)
    {
        if(MinecraftClient.getInstance().player == null || MinecraftClient.getInstance().player.age < 30) return;
        if(!packet.profileIds().isEmpty())
        {
            PlayerListEntry playerListEntry = MinecraftClient.getInstance().getNetworkHandler().getPlayerListEntry(packet.profileIds().get(0));
            if(previous_out != playerListEntry.getProfile().getName() && playerListEntry.getProfile().getName() != MinecraftClient.getInstance().player.getGameProfile().getName())
            {
                previous_out = playerListEntry.getProfile().getName();
                previous_in = "";
                Waifuhax.EVENT_BUS.post(OnPlayerDisconnect.get(playerListEntry.getProfile().getName()));
            }
        }
    }

    @Inject(method = "onPlayerList", at = @At("HEAD"))
    public void onPlayerLogin(PlayerListS2CPacket packet, CallbackInfo ci)
    {
        if(MinecraftClient.getInstance().player == null || MinecraftClient.getInstance().player.age < 30) return;
        if(packet.getActions().contains(PlayerListS2CPacket.Action.ADD_PLAYER) && !packet.getEntries().isEmpty())
        {
            if(previous_in != packet.getEntries().get(0).profile().getName() && packet.getEntries().get(0).profile().getName() != MinecraftClient.getInstance().player.getGameProfile().getName())
            {
                previous_in = packet.getEntries().get(0).profile().getName();
                previous_out = "";
                Waifuhax.EVENT_BUS.post(OnPlayerConnect.get(packet.getEntries().get(0).profile().getName()));
            }
        }
    }
}
