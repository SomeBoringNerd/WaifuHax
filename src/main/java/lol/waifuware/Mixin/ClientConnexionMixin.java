package lol.waifuware.Mixin;

import lol.waifuware.Events.OnMessageReceive;
import lol.waifuware.Events.OnMessageSend;
import lol.waifuware.Events.OnPacketEvent;
import lol.waifuware.Waifuhax;
import net.fabricmc.fabric.mixin.content.registry.VillagerEntityMixin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.network.packet.s2c.play.ChatMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerListS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.network.packet.s2c.play.PlayerListS2CPacket.Action.ADD_PLAYER;

@Mixin(ClientConnection.class)
public class ClientConnexionMixin
{
    private boolean dontSend = false;

    // @TODO : replace that with event
    @Inject(method = "send(Lnet/minecraft/network/packet/Packet;)V", at = @At("HEAD"), cancellable = true)
    private void send(Packet<?> packet, CallbackInfo ci)
    {

        OnPacketEvent.Receive packetEvent = Waifuhax.EVENT_BUS.post(OnPacketEvent.Receive.get(packet));

        if (packet instanceof ChatMessageC2SPacket)
        {
            if(dontSend) return;

            String message = (((ChatMessageC2SPacket) packet).chatMessage());

            OnMessageSend e = Waifuhax.EVENT_BUS.post(OnMessageSend.get(message));

            if(e.isModified() && !message.startsWith("-") && !message.startsWith("#"))
            {
                ci.cancel();
                dontSend = true;
                assert MinecraftClient.getInstance().player != null;
                MinecraftClient.getInstance().player.networkHandler.sendChatMessage(e.getMessage());
                dontSend = false;
            }

            if(message.startsWith("-"))
            {
                ci.cancel();
            }
        }
    }
}