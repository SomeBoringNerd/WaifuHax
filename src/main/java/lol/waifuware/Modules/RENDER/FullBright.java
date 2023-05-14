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
        desc[0] = "\"Let there be light !\"";
        desc[1] = "- God, Genesis 1:3 of the Torah";
        Create();
    }
}
