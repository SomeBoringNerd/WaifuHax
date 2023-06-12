package lol.waifuware.Events;

import meteordevelopment.orbit.ICancellable;
import net.minecraft.client.util.math.MatrixStack;

public class OnWorldRender implements ICancellable
{
    private static final OnWorldRender onWorldRender = new OnWorldRender();

    private MatrixStack matrices;

    public static OnWorldRender get(MatrixStack matrices)
    {
        onWorldRender.matrices = matrices;
        return onWorldRender;
    }

    public MatrixStack getMatrices(){
        return matrices;
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