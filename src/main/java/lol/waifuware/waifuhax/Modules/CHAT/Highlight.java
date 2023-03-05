package lol.waifuware.waifuhax.Modules.CHAT;

import lol.waifuware.waifuhax.GlobalVariables;
import lol.waifuware.waifuhax.Modules.CATEGORY;
import lol.waifuware.waifuhax.Modules.Module;
import net.minecraft.client.util.math.MatrixStack;

public class Highlight extends Module
{
    public Highlight(String name, int Key) {
        super(name, Key, CATEGORY.CHAT);

        Create();

        GlobalVariables.HighLightEnabled = isEnabled;

        desc[0] = "Highlight your username in messages";
    }

    @Override
    public void Update()
    {

    }

    @Override
    public void Render(MatrixStack matrice) {

    }

    @Override
    public void onActivate() {
        GlobalVariables.HighLightEnabled = true;
    }

    @Override
    public void onDisable() {
        GlobalVariables.HighLightEnabled = false;
    }
}
