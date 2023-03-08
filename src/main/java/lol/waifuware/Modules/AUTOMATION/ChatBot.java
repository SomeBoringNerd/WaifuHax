package lol.waifuware.Modules.AUTOMATION;

import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.Interfaces.Module;

@Module(name = "ChatBot", key = 0, cat = CATEGORY.BOT)
public class ChatBot extends AbstractModule
{
    public ChatBot()
    {
        super();
        Create();

        desc[0] = "Cool chat bot";
        desc[1] = "-chatbot to see how to set";
        desc[2] = "it up.";
    }
}
