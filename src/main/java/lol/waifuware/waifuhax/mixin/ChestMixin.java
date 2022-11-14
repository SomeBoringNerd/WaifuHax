package lol.waifuware.waifuhax.mixin;

import lol.waifuware.waifuhax.GlobalVariables;
import lol.waifuware.waifuhax.util.ChatUtil;
import net.minecraft.block.BlockState;

import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.ViewerCountManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ViewerCountManager.class)
public class ChestMixin
{
    @Inject(method = "updateViewerCount", at = @At("HEAD"))
    public void updateViewerCount(World world, BlockPos pos, BlockState state, CallbackInfo ci)
    {
        if(GlobalVariables.coordLogger)
        {
            ChatUtil.SendMessage("a chest was opened at x:" + pos.getX() + ", y:" + pos.getY() + ", z:" + pos.getZ());
        }
    }
}
