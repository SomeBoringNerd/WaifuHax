package lol.waifuware.waifuhax.mixin;

import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMixin {



    @Inject(method = "setNewItemName", at = @At("HEAD"))
    public void setNewItemName(String _newItemName, CallbackInfo ci)
    {
        _newItemName = _newItemName.replace("&&", "§");
    }
}
