package lol.waifuware.waifuhax.mixin;

import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreen.class)
public class AnvilScreenMixin
{
    @Inject(method = "onRenamed", at = @At("HEAD"))
    private void onRenamed(String name, CallbackInfo info)
    {
        name = name.replace("&&", "§");
    }
}
