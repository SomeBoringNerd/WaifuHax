package lol.waifuware.waifuhax.Modules.RENDER;

import lol.waifuware.waifuhax.Modules.CATEGORY;
import lol.waifuware.waifuhax.Modules.Module;

public class FullBright extends Module
{

    private static FullBright instance;
    public static FullBright getInstance(){
        return instance;
    }
    public FullBright(String name, int Key, CATEGORY cat)
    {
        super(name, Key, cat);
        instance = this;
        desc[0] = "\"Enciende las luces\"";
        desc[1] = "- Sombra";
    }
}
