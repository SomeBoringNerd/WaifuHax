package lol.waifuware.waifuhax.Modules.GUI;

import lol.waifuware.waifuhax.Modules.Module;
import lol.waifuware.waifuhax.Modules.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class ArrayList extends Module
{
    public ArrayList(String name, int Key) {
        super(name, Key);

        Create();
    }

    @Override
    public void Update() {

    }

    private int fromRGBA(int r, int g, int b, int a) {
        return (r << 16) + (g << 8) + (b) + (a << 24);
    }
    @Override
    public void Render(MatrixStack matrice)
    {
        MinecraftClient.getInstance().textRenderer.drawWithShadow(matrice, "Active modules : ", 5, 15, fromRGBA(255, 255, 255, 255 ));
        int i = 10;
        for(Module mod : ModuleManager.modules){
            if(mod.isEnabled){
                MinecraftClient.getInstance().textRenderer.drawWithShadow(matrice, "> " + mod.name, 5, 15 + i, fromRGBA(255, 255, 255, 255));
                i += 10;
            }
        }
    }

    @Override
    public void onActivate() {

    }

    @Override
    public void onDisable() {

    }
}
