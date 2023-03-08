package lol.waifuware.Events;

import meteordevelopment.orbit.ICancellable;
import net.minecraft.client.util.math.MatrixStack;

public class OnRenderScreen implements ICancellable
{
    private static final OnRenderScreen onRenderScreen = new OnRenderScreen();

    private MatrixStack matrices;

    public static OnRenderScreen get(MatrixStack matrices)
    {
        onRenderScreen.matrices = matrices;
        return onRenderScreen;
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
