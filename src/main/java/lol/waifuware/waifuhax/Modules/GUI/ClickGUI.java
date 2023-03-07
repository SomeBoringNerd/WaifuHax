package lol.waifuware.waifuhax.Modules.GUI;

import lol.waifuware.waifuhax.Modules.CATEGORY;
import lol.waifuware.waifuhax.Modules.Module;

import java.awt.*;

public class ClickGUI extends Module
{
    private static ClickGUI instance;
    public static ClickGUI getInstance()
    {
        return instance;
    }

    public ClickGUI(String name, int Key, CATEGORY cat)
    {
        super(name, Key, cat);
        isEnabled = false;
        instance = this;

        settings.put("MainColor", "0xFF00FF");
        settings.put("BackgroundColor", "0x000000");
        settings.put("ButtonColor", "0x440044");
        settings.put("ButtonColorHovered", "0xFB00FF");
        settings.put("ButtonColorHoveredAndEnabled", "0xFF00FF");
        settings.put("ButtonColorEnabled", "0xFF00C4");

        Create();
    }

    public Color getColor(String name)
    {
        return Color.decode((String) settings.get(name));
    }
}
