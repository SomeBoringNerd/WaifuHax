package lol.waifuware.ClickGUI;

import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.GUI.ClickGUI;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class DescriptionPanel
{

    public int x, y, width, height, dragX, dragY;
    public CATEGORY category;

    public boolean isPressed;
    public boolean onModule;

    public static ModuleButton butt;

    public DescriptionPanel()
    {

        y = 5;

        width = 250;
        height = 90;
    }

    public boolean isHovered(double mouseX, double mouseY)
    {
        return (mouseX > x) && (mouseX < x + width) && (mouseY > y) && (mouseY < y + height);
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta)
    {
        x = MinecraftClient.getInstance().getWindow().getScaledWidth() - 255;

        DrawableHelper.fill(matrices, x - 1, y - 1, x + width + 1 , y + height + 1, ClickGUI.getInstance().getColor("MainColor").getRGB());

        DrawableHelper.fill(matrices, x + 1, y + 1, x + width - 1, y + height - 1, ClickGUI.getInstance().getColor("BackgroundColor").getRGB());

        DrawableHelper.fill(matrices, x - 1, y + 18, x + width + 1 , y + 20, ClickGUI.getInstance().getColor("MainColor").getRGB());

        if(butt == null)
        {
            MinecraftClient.getInstance().textRenderer.draw(matrices, "Hover over a module to get it's description", x + 8, y + 6, Color.white.getRGB());
        }
        else
        {
            MinecraftClient.getInstance().textRenderer.draw(matrices, "Description of " + butt.mod.name, x + 8, y + 6, Color.white.getRGB());
            int offset = 26;
            for(int i = 0; i < 5; i++){
                MinecraftClient.getInstance().textRenderer.draw(matrices, butt.mod.desc[i], x + 6, y + offset, Color.white.getRGB());
                offset += 15;
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
    }

    public void mouseRelease(double mouseX, double mouseY, int button)
    {
        if(button == 0 && isPressed) isPressed = false;
    }

    public void UpdatePosition(double mouseX, double mouseY)
    {
        if(isPressed){
            x = (int)(mouseX - dragX);
            y = (int)(mouseY - dragY);
        }
    }
}
