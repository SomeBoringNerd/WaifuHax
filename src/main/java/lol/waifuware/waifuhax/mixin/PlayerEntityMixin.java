package lol.waifuware.waifuhax.mixin;

import lol.waifuware.waifuhax.GlobalVariables;
import lol.waifuware.waifuhax.Modules.Module;
import lol.waifuware.waifuhax.Modules.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin
{
    int tick = 0;
    @Inject(method = "tick", at = @At("TAIL"))
    private void Update(CallbackInfo ci)
    {
        for (Map.Entry<String, Module> mod : ModuleManager.modules.entrySet())
        {
            if (mod.getValue().isEnabled)
            {
                mod.getValue().Update();
            }
        }
    }
}
