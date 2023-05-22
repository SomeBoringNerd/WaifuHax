package lol.waifuware.Mixin;

import lol.waifuware.Events.OnRenderScreen;
import lol.waifuware.Waifuhax;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin
{
    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(MatrixStack matrixStack, float tickDelta, CallbackInfo info)
    {
        if(!MinecraftClient.getInstance().options.debugEnabled)
        {
            Waifuhax.EVENT_BUS.post(OnRenderScreen.get(matrixStack));
        }
    }
}
