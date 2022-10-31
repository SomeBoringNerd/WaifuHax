package lol.waifuware.waifuhax.mixin;

import com.mojang.authlib.minecraft.BanDetails;
import lol.waifuware.waifuhax.Waifuhax;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Mutable
    @Shadow @Final public static Text COPYRIGHT;

    @Shadow @Final private boolean isMinceraft = true;

    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info)
    {
        COPYRIGHT = Text.literal("WaifuWare INC 2022");
        Waifuhax.Log("Mixin title screen was loaded");
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
        mc.textRenderer.drawWithShadow(matrices, "WaifuHax by SomeBoringNerd", x, y, fromRGBA(255, 255, 255, 255));
    }

    private int fromRGBA(int r, int g, int b, int a) {
        return (r << 16) + (g << 8) + (b) + (a << 24);
    }
}
