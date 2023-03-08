package lol.waifuware.Modules.CHAT;

import lol.waifuware.Events.OnMessageReceive;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.Interfaces.Module;
import meteordevelopment.orbit.EventHandler;

@Module(name = "Suffix", key = 0, cat = CATEGORY.CHAT)
public class Suffix extends AbstractModule
{
    public Suffix()
    {
        super();
        Create();

        desc[0] = "Add some fancy text after your messages";
    }

    @EventHandler
    public void OnSendMessage(OnMessageReceive event)
    {
        event.setMessage((event.getMessage() + " | ＷａｉｆｕＨａｘ Ｖ２"));
    }
}
