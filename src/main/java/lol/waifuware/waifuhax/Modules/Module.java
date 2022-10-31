package lol.waifuware.waifuhax.Modules;

import lol.waifuware.waifuhax.Waifuhax;
import net.minecraft.client.gui.screen.Overlay;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;

public abstract class Module
{
    // note : make all modules extended from this one

    public String name;

    public Module(String name, int Key)
    {
        Waifuhax.LOGGER.info("module " + name + " was loaded");

        this.name = name;
    }

    public boolean isEnabled = false;

    public void Toggle()
    {
        isEnabled = !isEnabled;

        Waifuhax.Log("Module " + name + " got toggled");

        if(isEnabled){
            onActivate();
        }else{
            onDisable();
        }
    }
    public abstract void Update();

    public abstract void Render(MatrixStack matrice);
    public abstract void onActivate();
    public abstract void onDisable();
}
