package lol.waifuware.waifuhax.Modules.GUI;

import com.google.gson.Gson;
import lol.waifuware.waifuhax.GlobalVariables;
import lol.waifuware.waifuhax.Modules.CATEGORY;
import lol.waifuware.waifuhax.Modules.Module;
import lol.waifuware.waifuhax.Waifuhax;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

public class Watermark extends Module
{
    public Watermark(String name, int Key)
    {
        super(name, Key, CATEGORY.GUI);

        Create();

        desc[0] = "Fancy text on your screen";
    }

    @Override
    public void Render(MatrixStack matrice)
    {
        String name = MinecraftClient.getInstance().player.getEntityName();
        MinecraftClient.getInstance().textRenderer.drawWithShadow(matrice, "WaifuHax V1.0 | " + name, 5, 5, fromRGBA(255, 255, 255, 255 ));
    }

    private int fromRGBA(int r, int g, int b, int a) {
        return (r << 16) + (g << 8) + (b) + (a << 24);
    }

    @Override
    public void onActivate() {

    }

    @Override
    public void onDisable() {

    }
}
