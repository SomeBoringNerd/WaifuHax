package lol.waifuware.Mixin;

import lol.waifuware.Events.OnMessageReceive;
import lol.waifuware.Waifuhax;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketCallbacks;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class ClientConnexionMixin
{
    private boolean dontSend = false;

    // @TODO : replace that with event
    @Inject(method = "send(Lnet/minecraft/network/Packet;Lnet/minecraft/network/PacketCallbacks;)V", at = @At("HEAD"), cancellable = true)
    private void send(Packet<?> packet, PacketCallbacks packetCallback, CallbackInfo callback)
    {
        if (packet instanceof ChatMessageC2SPacket)
        {
            if(dontSend) return;

            String message = (((ChatMessageC2SPacket) packet).chatMessage());

            OnMessageReceive e = Waifuhax.EVENT_BUS.post(OnMessageReceive.get(message));

            if(e.isModified() && !message.startsWith("-") && !message.startsWith("#"))
            {
                callback.cancel();
                dontSend = true;
                MinecraftClient.getInstance().player.networkHandler.sendChatMessage(e.getMessage());
                dontSend = false;
            }

            if(message.startsWith("-"))
            {
                callback.cancel();
            }
        }
    }
}