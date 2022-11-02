package lol.waifuware.waifuhax.mixin;

import lol.waifuware.waifuhax.GlobalVariables;
import lol.waifuware.waifuhax.commands.Command;
import lol.waifuware.waifuhax.commands.CommandManager;
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
    @Inject(method = "send(Lnet/minecraft/network/Packet;Lnet/minecraft/network/PacketCallbacks;)V", at = @At("HEAD"), cancellable = true)
    private void send(Packet<?> packet, PacketCallbacks packetCallback, CallbackInfo callback) {
        if (packet instanceof ChatMessageC2SPacket)
        {
            ChatMessageC2SPacket pack = (ChatMessageC2SPacket) packet;

            if(pack.chatMessage().startsWith("-"))
            {
                for (Command mod : CommandManager.Commands)
                {
                    if (pack.chatMessage().toLowerCase().trim().startsWith("-" + mod.name.toLowerCase().trim())) {
                        mod.Execute(pack.chatMessage().replace("-", ""));
                        callback.cancel();
                    }
                }
            }
        }
    }
}