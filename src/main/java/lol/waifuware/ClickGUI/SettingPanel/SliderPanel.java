package lol.waifuware.ClickGUI.SettingPanel;

import lol.waifuware.ClickGUI.ClickGUI;
import lol.waifuware.ClickGUI.ModuleButton;
import lol.waifuware.Events.OnRenderScreen;
import lol.waifuware.Settings.IntSetting;
import lol.waifuware.Settings.Setting;
import lol.waifuware.Util.ChatUtil;
import lol.waifuware.Waifuhax;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SliderPanel extends SettingPanelBase
{

    public IntSetting integer = (IntSetting) setting;

    private boolean slide;

    public SliderPanel(Setting setting, ModuleButton parent, int offset) {
        super(setting, parent, offset);
        integer = (IntSetting) setting;
        Waifuhax.EVENT_BUS.subscribe(this);
        if(setting.getName().toLowerCase().contains("color")){
            setting.setVisible(false);
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta)
    {
        if(setting.getVisible())
        {
            double diff = Math.min(parent.parent.width, Math.max(0, mouseX - parent.parent.xSet.getValueInt()));
            int renderWidth = (int) ((parent.parent.width - 2) * (((integer.getValue() - integer.getMin()) / (integer.getMax() - integer.getMin()))));

            if(slide)
            {
                if(diff == 0)
                {
                    integer.setValue(integer.getMin());
                }
                else
                {
                    double e = Round((diff / parent.parent.width) * (integer.getMax() - integer.getMin()) + integer.getMin(), 2);
                    integer.setValue(e);
                }
            }

            MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, integer.getName() + " : " + String.valueOf(integer.getValueFloat()), parent.parent.xSet.getValueInt() + 5, parent.parent.ySet.getValueInt() + parent.offset + offset + 4, -1);
            MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, "§7--------------------", parent.parent.xSet.getValueInt() + 2 , parent.parent.ySet.getValueInt() + parent.offset + offset + 4 + 10, -1);
            MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, "§8§l|", parent.parent.xSet.getValueInt() + renderWidth, parent.parent.ySet.getValueInt() + parent.offset + offset + 4 + 10, -1);

        }
    }

    double Round(double value, int place){
        if(place < 0){
            return value;
        }

        BigDecimal db = new BigDecimal(value);
        db.setScale(place, RoundingMode.HALF_UP);
        return db.doubleValue();
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button)
    {
        if(isHovered(mouseX, mouseY))
        {
            slide = true;
        }
    }

    @Override
    public void mouseRelease(double mouseX, double mouseY, int button)
    {
        slide = false;
        parent.mod.Save();
    }

    @Override
    public boolean isHovered(double mouseX, double mouseY){
        return ((mouseX > parent.parent.xSet.getValueInt()) && (mouseX < parent.parent.xSet.getValueInt() + parent.parent.width) && (mouseY > parent.parent.ySet.getValueInt() + parent.offset + offset + 10) && (mouseY < parent.parent.ySet.getValueInt() + parent.offset + offset + 10 + 16)) && parent.extended;
    }
}
