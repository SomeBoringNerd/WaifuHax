package lol.waifuware.waifuhax.Modules.AUTOMATION;

import lol.waifuware.waifuhax.Modules.CATEGORY;
import lol.waifuware.waifuhax.Modules.Module;

public class ChatBot extends Module
{
    public ChatBot(String name, int Key, CATEGORY cat)
    {
        super(name, Key, cat);
        desc[0] = "Cool chat bot";
        desc[1] = "-chatbot to see how to set";
        desc[2] = "it up.";
    }

    @Override
    public void onChat(String message)
    {
        //@TODO : implement api
    }
}
