package lol.waifuware.waifuhax.mixin;

import lol.waifuware.waifuhax.Modules.RENDER.FullBright;
import net.minecraft.client.render.LightmapTextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(LightmapTextureManager.class)
public class LightMapRenderer
{
    @ModifyArgs(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/texture/NativeImage;setColor(III)V"))
    private void update(Args args)
    {
        if (FullBright.getInstance().isEnabled)
        {
            args.set(2, 0xFFFFFFFF);
        }
    }
}
