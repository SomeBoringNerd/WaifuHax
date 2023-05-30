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
        previous_in = "";
        if(!packet.profileIds().isEmpty())
        {
            PlayerListEntry playerListEntry = MinecraftClient.getInstance().getNetworkHandler().getPlayerListEntry(packet.profileIds().get(0));
            if(!Objects.equals(previous_out, playerListEntry.getProfile().getName()))
            {
                previous_out = playerListEntry.getProfile().getName();
                Waifuhax.EVENT_BUS.post(OnPlayerDisconnect.get(playerListEntry.getProfile().getName()));
            }
        }
    }
}
