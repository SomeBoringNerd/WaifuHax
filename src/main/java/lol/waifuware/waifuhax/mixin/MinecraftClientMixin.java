package lol.waifuware.waifuhax.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = MinecraftClient.class, priority = 1001)
public class MinecraftClientMixin
{
    @ModifyArg(method = "updateWindowTitle", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;setTitle(Ljava/lang/String;)V"))
    private String setTitle(String original)
    {
        return "WaifuHax 1.0 for Minecraft 1.19.2 || By SomeBoringNerd";
    }
}
