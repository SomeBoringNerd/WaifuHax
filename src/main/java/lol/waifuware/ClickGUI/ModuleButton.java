package lol.waifuware.ClickGUI;

import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.GUI.ClickGUI;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

public class ModuleButton
{
    public AbstractModule mod;
    public CategoryPanel parent;
    public int offset;

    public ModuleButton(AbstractModule mod, CategoryPanel parent, int offset)
    {
        this.mod = mod;
        this.parent = parent;
        this.offset = offset;
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta)
    {

        //DrawableHelper.fill(matrices, parent.x , parent.y + offset, parent.x + parent.width, parent.y + (offset) + 16 , new Color(255, 0, 0, 255).getRGB());

        if(isHovered(mouseX, mouseY))
        {
            DescriptionPanel.butt = this;
            DrawableHelper.fill(matrices, parent.x + 1 , parent.y + offset + 2, parent.x + parent.width - 1, parent.y + (offset) + 14 , (mod.isEnabled ? ClickGUI.getInstance().getColor("ButtonColorHoveredAndEnabled").getRGB() : ClickGUI.getInstance().getColor("ButtonColorHovered").darker().darker().getRGB()));
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
            DrawableHelper.fill(matrices, parent.x + 1 , parent.y + offset + 2, parent.x + parent.width - 1, parent.y + (offset) + 14 , (mod.isEnabled ? ClickGUI.getInstance().getColor("ButtonColorEnabled").getRGB() : ClickGUI.getInstance().getColor("ButtonColor").getRGB()));
        }

        MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, mod.name, parent.x + 5, parent.y + offset + 4, -1);
        MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, "[+]", parent.x + (int)(parent.width - 20), parent.y + offset + 4, -1);
    }

    public void mouseClicked(double mouseX, double mouseY, int button)
    {
        if(isHovered(mouseX, mouseY) && parent.onModule)
        {
            if(button == 0)
            {
                mod.Toggle();
            }else{

            }
        }
    }

    public boolean isHovered(double mouseX, double mouseY){
        return (mouseX > parent.x) && (mouseX < parent.x + parent.width) && (mouseY > parent.y + offset) && (mouseY < parent.y + offset + 16);
    }
}
