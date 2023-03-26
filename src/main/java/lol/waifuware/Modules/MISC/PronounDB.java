package lol.waifuware.Modules.MISC;

import lol.waifuware.Events.OnMessageReceive;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.Interfaces.Module;
import lol.waifuware.Waifuhax;
import meteordevelopment.orbit.EventHandler;

@Module(name = "PronounDB", key = 0, cat = CATEGORY.MISC)
public class PronounDB extends AbstractModule
{
    public PronounDB()
    {
        desc[0] = "Display pronouns of player";
        desc[1] = "in chat overlay / nametag";
        desc[2] = "if they have a https://pronoundb.org";
        desc[3] = "account";
        Create();
    }

    @EventHandler
    public void onMessage(OnMessageReceive event)
    {
        Waifuhax.Log(event.getMessage());
    }
}
