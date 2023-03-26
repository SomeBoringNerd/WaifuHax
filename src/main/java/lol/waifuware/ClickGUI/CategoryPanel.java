package lol.waifuware.ClickGUI;

import lol.waifuware.ClickGUI.SettingPanel.SettingPanelBase;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.ModuleManager;
import lol.waifuware.Modules.GUI.ClickGUI;
import lol.waifuware.Util.ChatUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryPanel
{
    public int x, y, width, height, dragX, dragY;
    public CATEGORY category;

    public boolean isPressed;

    public boolean onModule;

    private List<ModuleButton> modList;
    int moduleAmount = 0;
    public CategoryPanel(CATEGORY category, int x, int y, int width, int height)
    {
        this.category = category;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        onModule = true;

        modList = new ArrayList<>();

        int offset = 16;

        for(AbstractModule mod : ModuleManager.getInstance().getModsInCat(category))
        {
            modList.add(new ModuleButton(mod, this, offset));
            offset += 16;
        }

        moduleAmount = ModuleManager.getInstance().getModsInCat(category).size();

        this.height = offset + 2;
    }

    boolean showModule()
    {
        return !modList.isEmpty() && onModule;
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta)
    {
        DrawableHelper.fill(matrices, x - 2, y - 2, x + width + 2, (showModule() ? y + height + 2 + offset: y +13), ClickGUI.getInstance().getColor("MainColor").getRGB());

        DrawableHelper.fill(matrices, x, y, x + width, (showModule() ? y + height + offset : y + 12 ), ClickGUI.getInstance().getColor("BackgroundColor").getRGB());

        DrawableHelper.fill(matrices, x - 2, y + 12, x + width + 2, y + 14 , ClickGUI.getInstance().getColor("MainColor").getRGB());

        MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, category.name, x + 2 , y + 2, Color.white.getRGB());
        int e = ("[" + moduleAmount + "]").length();
        MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, "[" + moduleAmount + "]", x + (width - 20) , y + 2, Color.white.getRGB());

        if(showModule())
        {
            for (ModuleButton butt : modList)
            {
                butt.render(matrices, mouseX, mouseY, delta);
            }
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button)
    {
        if(isHovered(mouseX, mouseY) && button == 0)
        {
            isPressed = true;
            dragX = (int) (mouseX - x);
            dragY = (int) (mouseY - y);
        }
        else if(isHovered(mouseX, mouseY) && button == 1)
        {
            onModule = !onModule;
        }

        for(ModuleButton butt : modList)
        {
            butt.mouseClicked(mouseX, mouseY, button);
        }
    }

    public void mouseRelease(double mouseX, double mouseY, int button)
    {
        if(button == 0 && isPressed) isPressed = false;

        for(ModuleButton buttons : modList){
            buttons.mouseRelease(mouseX, mouseY, button);
        }
    }

    public void UpdatePosition(double mouseX, double mouseY)
    {
        if(isPressed){
            x = (int)(mouseX - dragX);
            y = (int)(mouseY - dragY);
        }
    }

    public boolean isHovered(double mouseX, double mouseY)
    {
        return (mouseX > x) && (mouseX < x + width) && (mouseY > y) && (mouseY < y + 14);
    }

    int offset = 0;

    public void Update()
    {
        offset = 0;
        if (onModule)
        {
            for(ModuleButton button : modList)
            {
                button.offset = button.defaultOffset + offset;
                if(button.extended)
                {
                    for(SettingPanelBase panel : button.panels)
                    {
                        if(panel.setting.getVisible())
                        {
                            offset += 18;
                        }
                    }
                }
            }
        }
    }
}
