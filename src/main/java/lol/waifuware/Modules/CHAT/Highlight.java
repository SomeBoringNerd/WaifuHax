package lol.waifuware.Modules.CHAT;

import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.Interfaces.Module;

@Module(name = "HighLight", key = 0, cat = CATEGORY.CHAT)
public class Highlight extends AbstractModule
{
    public Highlight()
    {
        super();

        Create();

        desc[0] = "Highlight your username in messages";
        isWorkInProgress = true;
    }
}
