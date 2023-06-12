package lol.waifuware.Mixin;

import lol.waifuware.Events.OnWorldRender;
import lol.waifuware.Waifuhax;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin
{
    @Inject(method = "renderWorld", at = @At("HEAD"))
    void RenderWorld(float tickDelta, long limitTime, MatrixStack matrices, CallbackInfo ci)
    {
        Waifuhax.EVENT_BUS.post(OnWorldRender.get(matrices));
    }
}
