package lol.waifuware.waifuhax.mixin;

import lol.waifuware.waifuhax.Modules.Module;
import lol.waifuware.waifuhax.Modules.ModuleManager;
import lol.waifuware.waifuhax.Waifuhax;
import lol.waifuware.waifuhax.commands.Command;
import lol.waifuware.waifuhax.commands.CommandManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public class ChatHudMixin
{
    @Inject(at = @At("HEAD"), method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;ILnet/minecraft/client/gui/hud/MessageIndicator;Z)V", cancellable = true)
    private void onAddMessage(Text message, @Nullable MessageSignatureData signature, int ticks, @Nullable MessageIndicator indicator, boolean refresh, CallbackInfo info)
    {
        Waifuhax.Log(message.getString());
        Waifuhax.Log(message.getString().replace("<" + MinecraftClient.getInstance().player.getEntityName() + "> -", ""));
        Waifuhax.Log(MinecraftClient.getInstance().player.getEntityName());

        if(message.getString().startsWith("<" + MinecraftClient.getInstance().player.getEntityName() + "> " + "-"))
        {
            for (Command mod : CommandManager.Commands)
            {
                Waifuhax.Log("Command : " + mod.name + " | typed : " + message.getString().replace("<" + MinecraftClient.getInstance().player.getEntityName() + "> -", ""));
                if (message.getString().replace("<" + MinecraftClient.getInstance().player.getEntityName() + "> -", "").toLowerCase().trim().startsWith(mod.name.trim().toLowerCase())) {
                    mod.Execute(message.getString().replace("<" + MinecraftClient.getInstance().player.getEntityName() + "> -", ""));
                    info.cancel();
                }
            }
        }
    }
}
