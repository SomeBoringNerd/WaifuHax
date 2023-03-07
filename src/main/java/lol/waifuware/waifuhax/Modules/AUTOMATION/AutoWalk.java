package lol.waifuware.waifuhax.Modules.AUTOMATION;

import lol.waifuware.waifuhax.Modules.CATEGORY;
import lol.waifuware.waifuhax.Modules.Module;
import net.minecraft.client.MinecraftClient;

public class AutoWalk extends Module
{
    public AutoWalk(String name, int Key, CATEGORY cat)
    {
        super(name, Key, cat);
    }

    @Override
    public void Update() {
        if(!(MinecraftClient.getInstance().player != null && MinecraftClient.getInstance().world != null)) return;
    }
}

