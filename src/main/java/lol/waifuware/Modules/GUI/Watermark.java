package lol.waifuware.Modules.GUI;

import lol.waifuware.Commands.MISC.Pronoun;
import lol.waifuware.Events.OnRenderScreen;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.Interfaces.Module;
import lol.waifuware.Settings.BooleanSetting;
import lol.waifuware.Settings.IntSetting;
import lol.waifuware.Util.Authentification;
import lol.waifuware.Waifuhax;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Module(name = "Watermark", key = 0, cat = CATEGORY.GUI)
public class Watermark extends AbstractModule
{

    public BooleanSetting ShowPronoun = new BooleanSetting("ShowPronoun", false, "Show your pronouns fetched from PronounDB", "sp");
    public BooleanSetting ShowUsername = new BooleanSetting("ShowUsername", true, "Show your username", "su");

    public IntSetting xPos = new IntSetting("X pos", 0, 1920, 4, 1, "X position of arraylist", "-x");
    public IntSetting yPos = new IntSetting("Y pos", 0, 1080, 4, 1, "Y position of arraylist", "-y");

    String latestHash = "";

    public Watermark()
    {
        super();
        addSettings(ShowUsername, ShowPronoun, xPos, yPos);

        xPos.setVisible(false);
        yPos.setVisible(false);

        Create();
        desc[0] = "Fancy text on your screen";

        offset = isEnabled() ? 10 : 0;
        instance = this;
        latestHash = Authentification.getLatestCommitHash();
    }

    private static Watermark instance;

    public static Watermark getInstance()
    {
        return instance;
    }

    private static int offset;
    public static int getWatermarkOffset()
    {
        return offset;
    }

    @Override
    public String getDisplayName()
    {
        return name + " §c[" + (ShowPronoun.getEnabled() ? "§2P" : "§4P") + "§r, " + (ShowUsername.getEnabled() ? "§2U" : "§4U") + "§r§c]";
    }

    // -------------------------------------------------------------------------------------------------------- //
    //                          Trigger warning this is utterly fucking stupid                                  //
    // -------------------------------------------------------------------------------------------------------- //
    @Override                                                                                                   //
    public void onEnable() {                                                                                    //
        offset = 10;                                                                                            //
    }                                                                                                           //
    // -------------------------------------------------------------------------------------------------------- //
    @Override                                                                                                   //
    public void onDisable() {                                                                                   //
        offset = 0;                                                                                             //
    }                                                                                                           //
    //                                                                                                          //
    // -------------------------------------------------------------------------------------------------------- //


    boolean marked;
    int width = 0;
    @EventHandler
    public void Render(OnRenderScreen event)
    {
        String name = MinecraftClient.getInstance().player.getEntityName();
        String display = "§c[§5WaifuHax" + (latestHash.isEmpty() ? "" : " git-" + latestHash) + "§d" + (ShowUsername.getEnabled() ? (", " + name) : "") + "§4] " + (ShowPronoun.getEnabled() ? (Pronoun.self_pronoun != null ? "§4Pronouns : " + Pronoun.self_pronoun : "") + "§r" : "");

        width = MinecraftClient.getInstance().textRenderer.getWidth(display);
        MinecraftClient.getInstance().textRenderer.drawWithShadow(event.getMatrices(), display, xPos.getValueInt(), yPos.getValueInt(), 0xFFFFFF);
    }

    public boolean isHovered(double mouseX, double mouseY)
    {
        return (mouseX > xPos.getValueInt()) && (mouseX < xPos.getValueInt() + width) && (mouseY > yPos.getValueInt()) && (mouseY < yPos.getValueInt() + 10);
    }

    public boolean isPressed;

    int dragX, dragY;

    public void mouseClicked(double mouseX, double mouseY, int button)
    {
        if(isHovered(mouseX, mouseY) && button == 0)
        {
            isPressed = true;
            dragX = (int) (mouseX - xPos.getValueInt());
            dragY = (int) (mouseY - yPos.getValueInt());
            UpdatePosition(mouseX, mouseY);
        }
    }

    public void mouseRelease(double mouseX, double mouseY, int button)
    {
        if(button == 0 && isPressed) isPressed = false;

        Save();
    }

    public void UpdatePosition(double mouseX, double mouseY)
    {
        if(isPressed)
        {
            xPos.setValue((int)(mouseX - dragX));
            yPos.setValue((int)(mouseY - dragY));
        }
    }
}
