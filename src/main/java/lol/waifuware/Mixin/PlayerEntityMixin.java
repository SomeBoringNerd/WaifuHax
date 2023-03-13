package lol.waifuware.Mixin;

import lol.waifuware.Events.OnTickEvent;
import lol.waifuware.Modules.ModuleManager;
import lol.waifuware.Waifuhax;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin
{
    @Inject(method = "tick", at = @At("TAIL"))
    private void Update(CallbackInfo ci)
    {
        Waifuhax.EVENT_BUS.post(OnTickEvent.get());
    }
}
