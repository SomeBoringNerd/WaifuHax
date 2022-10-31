package lol.waifuware.waifuhax.mixin;

import lol.waifuware.waifuhax.Modules.Module;
import lol.waifuware.waifuhax.Modules.ModuleManager;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin
{
    @Inject(method = "tick", at = @At("TAIL"))
    private void Update(CallbackInfo ci)
    {
        for (Module mod: ModuleManager.modules)
        {
            mod.Update();
        }
    }
}
