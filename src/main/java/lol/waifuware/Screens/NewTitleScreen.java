package lol.waifuware.Screens;

import com.mojang.blaze3d.systems.RenderSystem;
import lol.waifuware.Util.Authentification;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.client.gui.LogoDrawer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerWarningScreen;
import net.minecraft.client.gui.screen.option.AccessibilityOptionsScreen;
import net.minecraft.client.gui.screen.option.CreditsAndAttributionScreen;
import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.PressableTextWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceFinder;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class NewTitleScreen extends Screen {

    private final MinecraftClient client = MinecraftClient.getInstance();

    public static final CubeMapRenderer PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier("textures/gui/title/background/panorama"));
    private static final Identifier PANORAMA_OVERLAY = new Identifier("waifuhax", "background.png");

    private LogoDrawer logoDrawer;

    public NewTitleScreen() {
        super(Text.of("WaifuHax - Title Screen"));

        this.logoDrawer = (LogoDrawer) Objects.requireNonNullElseGet(logoDrawer, () -> {
            return new LogoDrawer(false);
        });
    }

    private void initWidgetsNormal(int y, int spacingY) {
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.singleplayer"), (button) -> {
            this.client.setScreen(new SelectWorldScreen(this));
        }).dimensions(this.width / 4 - 100, y, 200, 20).build());

        Text text = null;
        boolean bl = text == null;
        Tooltip tooltip = text != null ? Tooltip.of(text) : null;
        ((ButtonWidget) this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.multiplayer"), (button) ->
        {
            this.client.setScreen(new MultiplayerScreen(this));
        }).dimensions(this.width / 4 - 100, y + spacingY, 200, 20).tooltip(tooltip).build())).active = bl;

        this.addDrawableChild(ButtonWidget.builder(Text.of("Give us a star on github"), (button) -> {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/SomeBoringNerd/WaifuHax"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }}
        }).dimensions(this.width / 4 - 100, y + spacingY * 2, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.options"), (button) -> {
            this.client.setScreen(new OptionsScreen(this, this.client.options));
        }).dimensions(this.width / 4 - 100, y + spacingY * 3, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.quit"), (button) -> {
            this.client.scheduleStop();
        }).dimensions(this.width / 4 - 100, y + spacingY * 4, 200, 20).build());
    }

    protected void init() {
        int i = this.textRenderer.getWidth("COPYRIGHT");
        int j = this.width - i - 2;
        int l = this.height / 4 + 48;

        initWidgetsNormal(l, 24);
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta)
    {

        RenderSystem.setShaderTexture(0, PANORAMA_OVERLAY);
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        drawTexture(matrices, 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        this.logoDrawer.draw(matrices, this.width, 1.0f);

        MinecraftClient mc = MinecraftClient.getInstance();
        int y = 5;
        int x = 5;
        mc.textRenderer.drawWithShadow(matrices, "WaifuHaxV2 by WaifuWare", x, y, 0xFFFFFF);
        mc.textRenderer.drawWithShadow(matrices, "Copyright Mojang AB 2023", x, this.height - 15, 0xFFFFFF);

        super.render(matrices, mouseX, mouseY, delta);
    }
}
