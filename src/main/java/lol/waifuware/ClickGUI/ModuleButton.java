package lol.waifuware.ClickGUI;

import lol.waifuware.ClickGUI.SettingPanel.CheckBoxPanel;
import lol.waifuware.ClickGUI.SettingPanel.ModePanel;
import lol.waifuware.ClickGUI.SettingPanel.SettingPanelBase;
import lol.waifuware.ClickGUI.SettingPanel.SliderPanel;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.GUI.ClickGUI;
import lol.waifuware.Settings.BooleanSetting;
import lol.waifuware.Settings.IntSetting;
import lol.waifuware.Settings.ModeSetting;
import lol.waifuware.Settings.Setting;
import lol.waifuware.Util.ChatUtil;
import lol.waifuware.Waifuhax;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;
import java.util.List;

public class ModuleButton
{
    public AbstractModule mod;
    public CategoryPanel parent;
    public int offset;
    public int defaultOffset;
    public boolean extended;
    public List<SettingPanelBase> panels;

    public ModuleButton(AbstractModule mod, CategoryPanel parent, int offset)
    {
        this.mod = mod;
        this.parent = parent;
        this.offset = offset;
        this.defaultOffset = offset;
        this.panels = new ArrayList<>();
        int setOffset = 18;

        for(Setting setting : mod.getSettings())
        {
            if(setting.getVisible())
            {
                if (setting instanceof IntSetting e) {
                    panels.add(new SliderPanel(setting, this, setOffset));
                    setOffset += 26;
                } else if (setting instanceof BooleanSetting e) {
                    panels.add(new CheckBoxPanel(setting, this, setOffset));
                    setOffset += 18;
                } else if (setting instanceof ModeSetting e) {
                    panels.add(new ModePanel(setting, this, setOffset));
                    setOffset += 18;
                }
            }
        }
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta)
    {
        if(isHovered(mouseX, mouseY))
        {
            DescriptionPanel.butt = this;
            DrawableHelper.fill(matrices, parent.xSet.getValueInt()+ 1 , parent.ySet.getValueInt() + offset + 2, parent.xSet.getValueInt() + parent.width - 1, parent.ySet.getValueInt() + (offset) + 14 , (mod.isEnabled ? ClickGUI.getInstance().getColor("ButtonColorHoveredAndEnabled").getRGB() : ClickGUI.getInstance().getColor("ButtonColorHovered").darker().darker().getRGB()));
        }
        else
        {
            if(DescriptionPanel.butt != null)
            {
                if(DescriptionPanel.butt == this)
                {
                    DescriptionPanel.butt = null;
                }
            }
            DrawableHelper.fill(matrices, parent.xSet.getValueInt() + 1 , parent.ySet.getValueInt() + offset + 2, parent.xSet.getValueInt() + parent.width - 1, parent.ySet.getValueInt() + (offset) + 14 , (mod.isEnabled ? ClickGUI.getInstance().getColor("ButtonColorEnabled").getRGB() : ClickGUI.getInstance().getColor("ButtonColor").getRGB()));
        }

        MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, mod.name + (mod.isWorkInProgress ? " (WIP)" : ""), parent.xSet.getValueInt() + 5, parent.ySet.getValueInt() + offset + 4, -1);
        MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, extended ? "[-]" : "[+]", parent.xSet.getValueInt() + (int)(parent.width - 20), parent.ySet.getValueInt() + offset + 4, -1);

        if(extended) {
            for (SettingPanelBase set : panels)
            {
                if(set instanceof SliderPanel)
                {
                    IntSetting integer = ((SliderPanel) set).integer;
                    int renderWidth = (int) (parent.width * (((integer.getValue() - integer.getMin()) / (integer.getMax() - integer.getMin()))));
                    DrawableHelper.fill(matrices, parent.xSet.getValueInt(), parent.ySet.getValueInt(), parent.width + renderWidth, parent.ySet.getValueInt() + 18, 0xff0000);
                }
                set.render(matrices, mouseX, mouseY, delta);
            }
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button)
    {

        if(isHovered(mouseX, mouseY) && parent.onModule)
        {
            if(button == 0)
            {
                mod.Toggle();
            }else
            {
                extended = !extended;
                parent.Update();
            }
        }

        for(SettingPanelBase set : panels){
            set.mouseClicked(mouseX, mouseY, button);
        }
    }

    public void mouseRelease(double mouseX, double mouseY, int button)
    {
        for(SettingPanelBase set : panels){
            set.mouseRelease(mouseX, mouseY, button);
        }
    }

    public boolean isHovered(double mouseX, double mouseY){
        return (mouseX > parent.xSet.getValueInt()) && (mouseX < parent.xSet.getValueInt() + parent.width) && (mouseY > parent.ySet.getValueInt() + offset) && (mouseY < parent.ySet.getValueInt() + offset + 16);
    }
}
