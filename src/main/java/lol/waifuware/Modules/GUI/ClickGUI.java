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
    @SettingDescription(name = "Color of the outline of the client")
    public IntSetting MainColor = new IntSetting("MainColor", 0x000000, 0xFFFFFF, 0xFF00FF, 1);

    @SettingDescription(name = "Color of the back of the panels")
    public IntSetting BackgroundColor = new IntSetting("BackgroundColor", 0x000000, 0xFFFFFF, 0x000000, 1);

    @SettingDescription(name = "Color of the buttons in default state (not hovered and not toggled)")
    public IntSetting ButtonColor = new IntSetting("ButtonColor", 0x000000, 0xFFFFFF, 0x440044, 1);

    @SettingDescription(name = "Color of enabled buttons")
    public IntSetting ButtonColorEnabled = new IntSetting("ButtonColorEnabled", 0x000000, 0xFFFFFF, 0xFF00C4, 1);

    @SettingDescription(name = "Color of toggled disabled buttons when hovered")
    public IntSetting ButtonColorHovered = new IntSetting("ButtonColorHovered", 0x000000, 0xFFFFFF, 0xFB00FF, 1);

    @SettingDescription(name = "Color of toggled enabled buttons when hovered")
    public IntSetting ButtonColorHoveredAndEnabled = new IntSetting("ButtonColorHoveredAndEnabled", 0x000000, 0xFFFFFF, 0xFF00FF, 1);


    public ClickGUI()
    {
        super();

        addSettings(MainColor, BackgroundColor, ButtonColor, ButtonColorEnabled, ButtonColorHovered, ButtonColorHoveredAndEnabled);

        isEnabled = false;
        instance = this;

        Create();
    }

    public Color getColor(String name)
    {
        IntSetting e = null;
        for(Setting set : getSettings()){
            if(set instanceof IntSetting && Objects.equals(set.getName(), name)){
                e = (IntSetting) set;
                return Color.decode("" + e.getValueInt());
            }
        }
        return Color.BLACK;
    }
}
