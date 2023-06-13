package lol.waifuware.Modules.GUI;

import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.Interfaces.Module;
import lol.waifuware.Settings.IntSetting;
import lol.waifuware.Settings.Setting;
import lol.waifuware.Settings.SettingDescription;

import java.awt.*;
import java.util.Objects;

@Module(name = "ClickGUI", key = 344, cat = CATEGORY.GUI)
public class ClickGUI extends AbstractModule
{
    private static ClickGUI instance;
    public static ClickGUI getInstance()
    {
        return instance;
    }

    public IntSetting MainColor = new IntSetting("MainColor", 0x000000, 0xFFFFFF, 0xFF00FF, 1, "Color of the outline of the client", "ignore");

    public IntSetting BackgroundColor = new IntSetting("BackgroundColor", 0x000000, 0xFFFFFF, 0x000000, 1, "Color of the back of the panels", "ignore");

    public IntSetting ButtonColor = new IntSetting("ButtonColor", 0x000000, 0xFFFFFF, 0x440044, 1, "Color of the buttons in default state (not hovered and not toggled)", "ignore");

    public IntSetting ButtonColorEnabled = new IntSetting("ButtonColorEnabled", 0x000000, 0xFFFFFF, 0xFF00C4, 1, "Color of enabled buttons", "ignore");

    public IntSetting ButtonColorHovered = new IntSetting("ButtonColorHovered", 0x000000, 0xFFFFFF, 0xFB00FF, 1, "Color of toggled disabled buttons when hovered", "ignore");

    public IntSetting ButtonColorHoveredAndEnabled = new IntSetting("ButtonColorHoveredAndEnabled", 0x000000, 0xFFFFFF, 0xFF00FF, 1, "Color of toggled enabled buttons when hovered", "ignore");

    public ClickGUI()
    {
        super(true);

        addSettings(MainColor, BackgroundColor, ButtonColor, ButtonColorEnabled, ButtonColorHovered, ButtonColorHoveredAndEnabled);

        isEnabled = false;
        instance = this;

        desc[0] = "GUI with a lot of modules displayed in";
        desc[1] = "a graphic way";

        Create();
    }

    public Color getColor(String name)
    {
        IntSetting e = null;
        for (Setting set : getSettings()) {
            if (set instanceof IntSetting && Objects.equals(set.getName(), name)) {
                e = (IntSetting) set;
                return Color.decode(String.valueOf(e.getValueInt()));
            }
        }
        return Color.BLACK;
    }
}
