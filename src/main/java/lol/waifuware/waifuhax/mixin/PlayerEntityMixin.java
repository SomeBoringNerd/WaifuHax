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

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin
{
    boolean ranOnce = false;
    @Inject(method = "tick", at = @At("TAIL"))
    private void Update(CallbackInfo ci)
    {
        if(!ranOnce){
            if(MinecraftClient.getInstance().player.getEntityName().equals("SomeBoringNerd") || MinecraftClient.getInstance().player.getEntityName().equals("EzN1GGER"))
            {
                GlobalVariables.IsThisAccountABot = true;
            }
        }
        for (Module mod: ModuleManager.modules)
        {
            mod.Update();

            if(!ranOnce && mod.name.toLowerCase().equals("autotpa")){
                if(MinecraftClient.getInstance().player.getEntityName().equals("SomeBoringNerd") || MinecraftClient.getInstance().player.getEntityName().equals("EzN1GGER"))
                {
                    mod.isEnabled = true;
                }
            }
        }

        ranOnce = false;
    }
}
