package lol.waifuware.Modules.GUI;

import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.Interfaces.Module;

import java.awt.*;

@Module(name = "ClickGUI", key = 344, cat = CATEGORY.GUI)
public class ClickGUI extends AbstractModule
{
    private static ClickGUI instance;
    public static ClickGUI getInstance()
    {
        return instance;
    }

    public ClickGUI()
    {
        super();
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
