package lol.waifuware.Modules.RENDER;

import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.Interfaces.Module;

@Module(name = "XRay", key = 0, cat = CATEGORY.RENDER)
public class Xray extends AbstractModule
{
    public Xray() {
        super();
        isWorkInProgress = true;
    }
}
