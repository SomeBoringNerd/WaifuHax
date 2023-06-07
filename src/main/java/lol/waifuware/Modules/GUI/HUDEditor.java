package lol.waifuware.Modules.GUI;

import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.Interfaces.Module;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

@Module(name = "HUD-Editor", key = GLFW.GLFW_KEY_Y, cat = CATEGORY.GUI)
public class HUDEditor extends AbstractModule
{
    public HUDEditor()
    {
        super(true);
        desc[0] = "Allow to move gui elements";
        Create();
        instance = this;
    }

    private static HUDEditor instance;

    public static HUDEditor getInstance(){
        return instance;
    }

    @Override
    public void onEnable()
    {
        MinecraftClient.getInstance().setScreen(lol.waifuware.Screens.HUDEditor.instance);
        Toggle(false);
    }
}
