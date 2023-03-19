package lol.waifuware.Mixin;

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
        COPYRIGHT = Text.literal("WaifuWare INC 2023");
        Waifuhax.Log("Mixin title screen was loaded");
        new ClickGUI();
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo info) {
        Render(matrices);
    }

    private void Render(MatrixStack matrices)
    {
        MinecraftClient mc = MinecraftClient.getInstance();
        int y = 5;
        int x = 5;
        mc.textRenderer.drawWithShadow(matrices, "WaifuHax by SomeBoringNerd", x, y, 0xFFFFFF);
    }
}
