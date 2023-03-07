package lol.waifuware.waifuhax.mixin;

import lol.waifuware.waifuhax.Modules.Module;
import lol.waifuware.waifuhax.Modules.ModuleManager;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(InGameHud.class)
public class InGameHudMixin
{
    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(MatrixStack matrixStack, float tickDelta, CallbackInfo info)
    {
        for (Map.Entry<String,Module> mod : ModuleManager.modules.entrySet())
        {
            if(mod.getValue().isEnabled) {
                mod.getValue().Render(matrixStack);
            }
        }
    }
}
