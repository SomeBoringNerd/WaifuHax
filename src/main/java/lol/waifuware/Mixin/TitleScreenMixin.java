package lol.waifuware.Mixin;

import lol.waifuware.Screens.NewTitleScreen;
import lol.waifuware.Waifuhax;
import lol.waifuware.ClickGUI.ClickGUI;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Mutable
    @Shadow @Final public static Text COPYRIGHT;

    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info)
    {
        new ClickGUI();
        MinecraftClient.getInstance().setScreen(new NewTitleScreen());
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo info) {
        Render(matrices);
    }

    private void Render(MatrixStack matrices)
    {

    }
}
