package lol.waifuware.Modules.MOVEMENT;

import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.Interfaces.Module;

@Module(name = "Jesus", key = 0, cat = CATEGORY.MOVEMENT)
public class Jesus extends AbstractModule
{

    public Jesus()
    {
        desc[0] = "Allow you to walk on water";
        desc[1] = "and snow";


        Create();
    }
}
