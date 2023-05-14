package lol.waifuware.Modules.MISC;

import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.Interfaces.Module;
import lol.waifuware.Settings.BooleanSetting;

@Module(name = "Global Settings", key = 0, cat = CATEGORY.MISC)
public class GlobalSettings extends AbstractModule
{

    public final static BooleanSetting Description = new BooleanSetting("DescriptionHUD", true, "HUD that show what a module do", "ignore");
    public final static BooleanSetting ToggleMessage = new BooleanSetting("Toggle messages", true, "Send a message in chat (client side) when a module is toggled", "ignore");

    public GlobalSettings()
    {
        super(true);
        addSettings(Description, ToggleMessage);
        desc[0] = "Global settings that are used all over";
        desc[1] = "the client";
        Create();
    }
}
