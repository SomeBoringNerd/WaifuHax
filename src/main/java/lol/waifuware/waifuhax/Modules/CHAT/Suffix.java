package lol.waifuware.waifuhax.Modules.CHAT;

import lol.waifuware.waifuhax.GlobalVariables;
import lol.waifuware.waifuhax.Modules.CATEGORY;
import lol.waifuware.waifuhax.Modules.Module;
import lol.waifuware.waifuhax.Modules.ModuleManager;
import net.minecraft.client.util.math.MatrixStack;

public class Suffix extends Module
{
    public Suffix(String name, int Key) {
        super(name, Key, CATEGORY.CHAT);

        Create();

        GlobalVariables.SuffixEnabled = isEnabled;

        desc[0] = "Add some fancy text after your messages";
    }

    @Override
    public void Update() {

    }

    @Override
    public void Render(MatrixStack matrice) {

    }

    @Override
    public void onActivate() {
        GlobalVariables.SuffixEnabled = true;
    }

    @Override
    public void onDisable() {
        GlobalVariables.SuffixEnabled = false;
    }
}
