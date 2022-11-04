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
            if(MinecraftClient.getInstance().player.getEntityName().equals("2b2flat"))
            {
                GlobalVariables.IsThisAccountABot = true;
            }
        }
        for (Module mod: ModuleManager.modules)
        {
            mod.Update();

            if(!ranOnce && mod.name.trim().toLowerCase().equals("autotpa") && MinecraftClient.getInstance().player.getEntityName().equals("2b2flat")){
                mod.isEnabled = true;
            }
        }

        ranOnce = false;
    }
}
