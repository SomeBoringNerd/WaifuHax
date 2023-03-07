package lol.waifuware.waifuhax.Modules.COMBAT;

import lol.waifuware.waifuhax.Modules.CATEGORY;
import lol.waifuware.waifuhax.Modules.Module;

public class AutoTotem extends Module
{

    public AutoTotem(String name, int Key, CATEGORY cat)
    {
        super(name, Key, cat);

        desc[0] = "Put a totem in your offhand if";
        desc[1] = "you have some in your inventory";

        Create();
    }
}
