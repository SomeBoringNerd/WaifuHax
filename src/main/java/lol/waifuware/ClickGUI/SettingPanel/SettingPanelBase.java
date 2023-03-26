package lol.waifuware.ClickGUI.SettingPanel;

import lol.waifuware.ClickGUI.ModuleButton;
import lol.waifuware.Settings.Setting;
import net.minecraft.client.util.math.MatrixStack;

public class SettingPanelBase
{

    public Setting setting;
    public ModuleButton parent;

    public int offset;

    public SettingPanelBase(Setting setting, ModuleButton parent, int offset)
    {
        this.setting = setting;
        this.parent = parent;
        this.offset = offset;
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta)
    {

    }

    public void mouseClicked(double mouseX, double mouseY, int button)
    {

    }

    public void mouseRelease(double mouseX, double mouseY, int button)
    {

    }

    public boolean isHovered(double mouseX, double mouseY){
        return (mouseX > parent.parent.x) && (mouseX < parent.parent.x + parent.parent.width) && (mouseY > parent.parent.y + parent.offset + offset) && (mouseY < parent.parent.y + parent.offset + offset + 16);
    }
}
