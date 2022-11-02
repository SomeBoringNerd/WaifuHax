package lol.waifuware.waifuhax.Modules.CHAT;

import lol.waifuware.waifuhax.GlobalVariables;
import lol.waifuware.waifuhax.Modules.Module;
import net.minecraft.client.util.math.MatrixStack;

public class Highlight extends Module
{
    public Highlight(String name, int Key) {
        super(name, Key);

        Create();

        GlobalVariables.HighLightEnabled = isEnabled;
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
