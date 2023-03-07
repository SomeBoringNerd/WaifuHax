package lol.waifuware.waifuhax.Modules.MOVEMENT;

import lol.waifuware.waifuhax.Modules.CATEGORY;
import lol.waifuware.waifuhax.Modules.Module;
import net.minecraft.client.MinecraftClient;

public class BoatFly extends Module
{
    public BoatFly(String name, int Key, CATEGORY cat)
    {
        super(name, Key, cat);
        Create();
        desc[0] = "Make boat go brrrrrrrrrrrr";
    }

    @Override
    public void _Update()
    {
        if(MinecraftClient.getInstance().player == null || !isEnabled) return;

        if(!MinecraftClient.getInstance().player.isRiding()) return;
    }
}
