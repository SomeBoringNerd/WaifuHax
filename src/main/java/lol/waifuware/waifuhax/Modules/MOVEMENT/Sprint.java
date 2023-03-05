package lol.waifuware.waifuhax.Modules.MOVEMENT;

import lol.waifuware.waifuhax.Modules.CATEGORY;
import lol.waifuware.waifuhax.Modules.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class Sprint extends Module
{
    public Sprint(String name, int Key, CATEGORY cat)
    {
        super(name, Key, cat);
        Create();
    }

    @Override
    public void Render(MatrixStack matrice) {

    }

    @Override
    public void _Update()
    {
        if(isEnabled)
        {
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.setSprinting(true);
        }
    }

    @Override
    public void onActivate()
    {
    }

    @Override
    public void onDisable()
    {
    }
}
