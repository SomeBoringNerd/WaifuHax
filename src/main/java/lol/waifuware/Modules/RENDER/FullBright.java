package lol.waifuware.Modules.RENDER;

import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.Interfaces.Module;

@Module(name = "FullBright", key = 0, cat = CATEGORY.RENDER)

public class FullBright extends AbstractModule
{

    private static FullBright instance;
    public static FullBright getInstance(){
        return instance;
    }
    public FullBright()
    {
        super();
        instance = this;
        desc[0] = "\"Enciende las luces\"";
        desc[1] = "- Sombra";
        Create();
    }
}
