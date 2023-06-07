package lol.waifuware.Screens;

import lol.waifuware.ClickGUI.CategoryPanel;
import lol.waifuware.Modules.GUI.ModuleList;
import lol.waifuware.Modules.GUI.Watermark;
import lol.waifuware.Util.ChatUtil;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class HUDEditor extends Screen
{

    public static HUDEditor instance;

    public HUDEditor()
    {
        super(Text.literal("WaifuHax - HUDEditor"));
        instance = this;
    }

    @Override
    protected void init()
    {
        super.init();

        // sometimes the arraylist is not movable unless I use this hack (or toggle it on and off manually)
        ModuleList.getInstance().Toggle();
        ModuleList.getInstance().Toggle();

        Watermark.getInstance().Toggle();
        Watermark.getInstance().Toggle();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button)
    {
        if(ModuleList.isEnabled())
        {
            ModuleList.getInstance().mouseClicked(mouseX, mouseY, button);
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
        if(ModuleList.isEnabled())
        {
            ModuleList.getInstance().mouseRelease(mouseX, mouseY, button);
        }

        if(Watermark.isEnabled())
        {
            Watermark.getInstance().mouseRelease(mouseX, mouseY, button);
        }

        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta)
    {
        if(ModuleList.isEnabled())
        {
            ModuleList.getInstance().UpdatePosition(mouseX, mouseY);
        }

        if(Watermark.isEnabled())
        {
            Watermark.getInstance().UpdatePosition(mouseX, mouseY);
        }
    }
}
