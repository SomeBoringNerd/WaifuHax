package lol.waifuware.Mixin;

import com.mojang.authlib.GameProfile;
import lol.waifuware.Commands.MISC.ThreadedRequest;
import lol.waifuware.Events.OnTickEvent;
import lol.waifuware.Modules.ModuleManager;
import lol.waifuware.Waifuhax;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin
{

    // imma be honest boss, i'm going too far for that one joke
    boolean once = false;

    @Inject(method = "tick", at = @At("TAIL"))
    private void Update(CallbackInfo ci)
    {
        if(!once){
            Thread t = new Thread(new ThreadedRequest(MinecraftClient.getInstance().player.getEntityName(), true), "request");
            t.start();
            once = true;
        }
        Waifuhax.EVENT_BUS.post(OnTickEvent.get());
    }
}
