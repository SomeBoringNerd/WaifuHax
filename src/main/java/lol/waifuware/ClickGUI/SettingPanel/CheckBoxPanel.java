package lol.waifuware.ClickGUI.SettingPanel;

import lol.waifuware.ClickGUI.ModuleButton;
import lol.waifuware.Settings.BooleanSetting;
import lol.waifuware.Settings.Setting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class CheckBoxPanel extends SettingPanelBase
{

    private BooleanSetting enabled = (BooleanSetting)setting;

    public CheckBoxPanel(Setting setting, ModuleButton parent, int offset)
    {
        super(setting, parent, offset);
        this.enabled = (BooleanSetting) setting;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, enabled.getName(), parent.parent.x + 5, parent.parent.y + parent.offset + offset + 4, -1);
        MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, enabled.getEnabled() ? "§2[V]§r" : "§4[X]§r", parent.parent.x + (int)(parent.parent.width - 20), parent.parent.y + parent.offset + offset + 4, -1);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button)
    {
        if(isHovered(mouseX, mouseY))
        {
            enabled.Toggle();
            parent.mod.Save();
        }
    }
}
