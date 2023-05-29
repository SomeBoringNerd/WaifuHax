package lol.waifuware.ClickGUI;

import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.GUI.Watermark;
import lol.waifuware.Modules.ModuleManager;
import lol.waifuware.Waifuhax;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ClickGUI extends Screen
{
    public static ClickGUI instance;
    public DescriptionPanel descPan;
    private List<CategoryPanel> panels;

    public ClickGUI()
    {
        super(Text.literal("WaifuHax - ClickGUI"));

        instance = this;

        panels = new ArrayList<>();

        descPan = new DescriptionPanel();

        int offset = 0;
        int previous = 5;
        for(CATEGORY category : CATEGORY.values())
        {
            CategoryPanel p = new CategoryPanel(category, 5 + offset, previous, 125, 30);
            panels.add(p);
            previous += p.height;

            if(previous >= MinecraftClient.getInstance().getWindow().getScaledHeight())
            {
                offset += 130;
                previous = 5;
            }
        }
    }

    public ClickGUI(Text title)
    {
        super(title);
    }

    @Override
    protected void init()
    {
        super.init();

        // sometime the arraylist is not movable unless I use this hack (or toggle it on and off manually)
        lol.waifuware.Modules.GUI.ArrayList.getInstance().Toggle();
        lol.waifuware.Modules.GUI.ArrayList.getInstance().Toggle();

        Watermark.getInstance().Toggle();
        Watermark.getInstance().Toggle();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta)
    {
        for(CategoryPanel panel : panels)
        {
            panel.render(matrices, mouseX, mouseY, delta);
            panel.UpdatePosition(mouseX, mouseY);
        }

        if(lol.waifuware.Modules.GUI.ArrayList.isEnabled())
        {
            lol.waifuware.Modules.GUI.ArrayList.getInstance().UpdatePosition(mouseX, mouseY);
        }

        if(Watermark.isEnabled())
        {
            Watermark.getInstance().UpdatePosition(mouseX, mouseY);
        }

        descPan.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button)
    {
        for(CategoryPanel panel : panels)
        {
            panel.mouseClicked(mouseX, mouseY, button);
        }

        descPan.mouseClicked(mouseX, mouseY, button);

        if(lol.waifuware.Modules.GUI.ArrayList.isEnabled())
        {
            lol.waifuware.Modules.GUI.ArrayList.getInstance().mouseClicked(mouseX, mouseY, button);
        }

        if(Watermark.isEnabled())
        {
            Watermark.getInstance().mouseClicked(mouseX, mouseY, button);
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button)
    {
        for(CategoryPanel panel : panels)
        {
            panel.mouseRelease(mouseX, mouseY, button);
        }

        if(lol.waifuware.Modules.GUI.ArrayList.isEnabled())
        {
            lol.waifuware.Modules.GUI.ArrayList.getInstance().mouseRelease(mouseX, mouseY, button);
        }

        if(Watermark.isEnabled())
        {
            Watermark.getInstance().mouseRelease(mouseX, mouseY, button);
        }

        descPan.mouseRelease(mouseX, mouseY, button);

        return super.mouseReleased(mouseX, mouseY, button);
    }
}
