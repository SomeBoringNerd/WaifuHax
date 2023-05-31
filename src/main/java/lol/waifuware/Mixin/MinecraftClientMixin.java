package lol.waifuware.Mixin;

import lol.waifuware.Util.Authentification;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Calendar;
import java.util.Date;

@Mixin(value = MinecraftClient.class, priority = 1001)
public class MinecraftClientMixin
{
    @ModifyArg(method = "updateWindowTitle", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;setTitle(Ljava/lang/String;)V"))
    private String setTitle(String original)
    {
        java.util.Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        if(month == Calendar.JUNE)
        {
            return "WaifuHax for Minecraft 1.19.4 wish you a happy pride month";
        }else{
            return "WaifuHax for Minecraft 1.19.4";
        }

    }

    @Inject(method = "<init>", at = @At("TAIL"))
    void DRM(RunArgs args, CallbackInfo ci)
    {
        if(Authentification.Authentificate() != 200)
        {
            MinecraftClient.getInstance().scheduleStop();
        }
    }
}
