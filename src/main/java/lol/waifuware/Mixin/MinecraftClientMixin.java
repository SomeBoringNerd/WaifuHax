package lol.waifuware.Mixin;

import lol.waifuware.Waifuhax;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import org.spongepowered.asm.mixin.Mixin;
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
        String uuid = MinecraftClient.getInstance().getSession().getUuid();
        if (Waifuhax.NO_JUNE_UUIDS.contains(uuid)) return "WaifuHax for Minecraft 1.19.4";

        java.util.Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        if(month == Calendar.JUNE) return "WaifuHax for Minecraft 1.19.4 wish you a happy pride month";

        return "WaifuHax for Minecraft 1.19.4";
    }
}
