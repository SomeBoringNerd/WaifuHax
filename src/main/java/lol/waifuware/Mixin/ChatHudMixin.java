package lol.waifuware.Mixin;

import lol.waifuware.Events.OnMessageReceive;
import lol.waifuware.Util.ChatUtil;
import lol.waifuware.Waifuhax;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mixin(ChatHud.class)
public abstract class ChatHudMixin
{
    @Shadow public abstract void addMessage(Text message);

    boolean ignore = false;
    @Inject(method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V", at = @At("HEAD"))
    private void addMessage(Text message, MessageSignatureData signature, MessageIndicator indicator, CallbackInfo ci)
    {
        // this is utterly fucking retarded
        if(ignore) return;
        String pattern = "literal\\{([^}]+)\\}\\[style=\\{\\}\\]";

        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(message.toString());
        String sMessage = "";
        String username = "";

        if (matcher.find()) {
            username = matcher.group(1);
        }

        if (matcher.find()) {
            sMessage = matcher.group(1);
        }
        if(!Objects.equals(sMessage, "") && !Objects.equals(username, ""))
        {
            ignore = true;
            Waifuhax.EVENT_BUS.post(OnMessageReceive.get(sMessage, username));

            if(OnMessageReceive.get().isModified())
            {
                ci.cancel();
                addMessage(Text.of("<" + username + "> " + sMessage));
            }
            ignore = false;
        }
    }
}
