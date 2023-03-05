package lol.waifuware.waifuhax.Modules.MOVEMENT;

import lol.waifuware.waifuhax.Modules.CATEGORY;
import lol.waifuware.waifuhax.Modules.Module;
import net.minecraft.client.MinecraftClient;

public class VanillaFly extends Module
{
    public VanillaFly(String name, int Key, CATEGORY cat)
    {
        super(name, Key, cat);
        desc[0] = "Fly on bad servers";
        Create();
    }

    @Override
    public void _Update() {
        assert MinecraftClient.getInstance().player != null;
        if(isEnabled)
        {
            MinecraftClient.getInstance().player.getAbilities().flying = true;
        }
    }

    @Override
    public void onDisable() {
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.getAbilities().flying = true;
    }
}
