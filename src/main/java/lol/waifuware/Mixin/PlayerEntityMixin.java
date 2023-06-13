package lol.waifuware.Mixin;

import com.mojang.authlib.GameProfile;
import lol.waifuware.Commands.MISC.Pronoun;
import lol.waifuware.Events.OnTickEvent;
import lol.waifuware.Modules.GUI.Watermark;
import lol.waifuware.Modules.ModuleManager;
import lol.waifuware.Util.Authentification;
import lol.waifuware.Util.PronounDBUtil;
import lol.waifuware.Waifuhax;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin
{

    @Shadow public abstract String getEntityName();

    // imma be honest boss, i'm going too far for that one joke
    boolean once = false;

    @Inject(method = "tick", at = @At("TAIL"))
    private void Update(CallbackInfo ci)
    {
        if(!once && MinecraftClient.getInstance().player != null)
        {
            if(getEntityName() == MinecraftClient.getInstance().player.getEntityName()) {
                Pronoun.self_pronoun = PronounDBUtil.callPronounDBApi(MinecraftClient.getInstance().player.getEntityName());
                once = true;
            }else{
                once = false;
            }
        }
        if(getEntityName() == MinecraftClient.getInstance().player.getEntityName())
        {
            Waifuhax.EVENT_BUS.post(OnTickEvent.get());
        }
    }
}
