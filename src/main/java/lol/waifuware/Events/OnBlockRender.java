package lol.waifuware.Events;

import meteordevelopment.orbit.ICancellable;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;

public class OnBlockRender implements ICancellable
{
    private static final OnBlockRender onBlockRender = new OnBlockRender();

    private MatrixStack matrices;
    private BlockPos pos;

    private BlockState state;

    public static OnBlockRender get(MatrixStack matrices, BlockPos pos, BlockState state)
    {
        onBlockRender.matrices = matrices;
        onBlockRender.pos = pos;
        onBlockRender.state = state;
        return onBlockRender;
    }

    public BlockPos getPos(){
        return pos;
    }
    public MatrixStack getMatrices(){
        return matrices;
    }

    public BlockState getState(){
        return state;
    }

    @Override
    public void setCancelled(boolean cancelled)
    {

    }

    @Override
    public boolean isCancelled() {
        return false;
    }
}