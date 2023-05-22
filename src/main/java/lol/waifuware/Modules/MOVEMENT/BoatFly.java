package lol.waifuware.Modules.MOVEMENT;

import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.Interfaces.Module;
import net.minecraft.client.MinecraftClient;

@Module(name = "BoatFly", key = 0, cat = CATEGORY.MOVEMENT)
public class BoatFly extends AbstractModule
{
    public BoatFly()
    {
        super();
        Create();
        desc[0] = "Make boat go brrrrrrrrrrrr";
        isWorkInProgress = true;
    }
}
