package lol.waifuware.Modules.GUI;

import lol.waifuware.ClickGUI.ModuleButton;
import lol.waifuware.Events.OnRenderScreen;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.Interfaces.Module;
import lol.waifuware.Modules.MISC.GlobalSettings;
import lol.waifuware.Modules.ModuleManager;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Settings.IntSetting;
import lol.waifuware.Settings.ModeSetting;
import lol.waifuware.Util.ChatUtil;
import lol.waifuware.Waifuhax;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.client.network.ClientPlayerEntity;

import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparingInt;

@Module(name = "ArrayList", key = 0, cat = CATEGORY.GUI)
public class ArrayList extends AbstractModule
{

    public ModeSetting sortMode = new ModeSetting("Sort mode", "alphabet", "how the arraylist is sorted", "srt", "size", "alphabet");
    
    public IntSetting xPos = new IntSetting("X pos", 0, 1920, 4, 1, "X position of arraylist", "-x");
    public IntSetting yPos = new IntSetting("Y pos", 0, 1080, 4, 1, "Y position of arraylist", "-y");

    public ArrayList() {
        super();

        xPos.setVisible(false);
        yPos.setVisible(false);

        addSettings(sortMode, xPos, yPos);
        instance = this;
        Create();

        desc[0] = "Show toggled modules";
    }

    private static ArrayList instance;

    public static ArrayList getInstance()
    {
        return instance;
    }

    private int fromRGBA(int r, int g, int b, int a) {
        return (r << 16) + (g << 8) + (b) + (a << 24);
    }
    int width;

    public boolean isHovered(double mouseX, double mouseY)
    {
        return (mouseX > xPos.getValueInt()) && (mouseX < xPos.getValueInt() + width) && (mouseY > yPos.getValueInt()) && (mouseY < yPos.getValueInt() + 17);
    }

    @EventHandler
    public void Render(OnRenderScreen event)
    {

        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        int ping = -1;
        if(player.getUuid() != null)
        {
            if(player.networkHandler.getPlayerListEntry(player.getUuid()) != null)
                ping = player.networkHandler.getPlayerListEntry(player.getUuid()).getLatency();
        }

        int iOffset = 10;

        for (AbstractModule mod : sortedModules())
        {
            if(mod.isEnabled && !mod.fakeModule())
            {
                iOffset += 10;
            }
        }
        width = xPos.getValueInt() + 225;
        DrawableHelper.fill(event.getMatrices(), xPos.getValueInt(), yPos.getValueInt(), xPos.getValueInt() + 225, yPos.getValueInt() + iOffset + 15, ClickGUI.getInstance().getColor("MainColor").getRGB());
        DrawableHelper.fill(event.getMatrices(), xPos.getValueInt() + 2, yPos.getValueInt() + 2, xPos.getValueInt() + 223, yPos.getValueInt() + iOffset + 13, ClickGUI.getInstance().getColor("BackgroundColor").getRGB());

        MinecraftClient.getInstance().textRenderer.drawWithShadow(event.getMatrices(), "§c[§dActive Modules§c]§r | Ping : " + getColorFromPing(ping) + "§r | FPS : " + MinecraftClient.getInstance().getCurrentFps(), xPos.getValueInt() + 4, yPos.getValueInt() + 5, fromRGBA(255, 255, 255, 255 ));
        DrawableHelper.fill(event.getMatrices(), xPos.getValueInt(), yPos.getValueInt() + 15, xPos.getValueInt() + 225, yPos.getValueInt() + 17 , ClickGUI.getInstance().getColor("MainColor").getRGB());

        int i = 10;
        for (AbstractModule mod : sortedModules())
        {
            if(mod.isEnabled && !mod.fakeModule()){
                MinecraftClient.getInstance().textRenderer.drawWithShadow(event.getMatrices(), "§a> §r" + (GlobalSettings.MetaData.getEnabled() ? mod.getDisplayName() : mod.getName()), xPos.getValueInt() + 10, yPos.getValueInt() + i + 10, fromRGBA(255, 255, 255, 255));
                i += 10;
            }
        }
    }

    public List<AbstractModule> sortedModules()
    {
        List<AbstractModule> enabled = getEnabledModules();

        if(sortMode.getIndex() == 0)
            enabled.sort(comparingInt(m -> (int) MinecraftClient.getInstance().textRenderer.getWidth(((AbstractModule)m).getName())).reversed());
        else
            enabled.sort(Comparator.comparing(AbstractModule::getName));

        return enabled;
    }

    public List<AbstractModule> getEnabledModules()
    {
        List<AbstractModule> mods = new java.util.ArrayList<>();

        for(AbstractModule mod : ModuleManager.modules){
            if(mod.isEnabled && !mod.fakeModule())
            {
                mods.add(mod);
            }
        }

        return mods;
    }

    @Override
    public String getDisplayName() {
        return name + " §c[§4" + sortMode.getModeAtIndex() + "§c]";
    }

    private String getColorFromPing(int ping)
    {
        if(ping <= -1){
            return "§4[CANT GET INFO]";
        }else if(ping <= 20){
            return "§2" + ping;
        }else if (ping <= 50){
            return "§a" + ping;
        }else if (ping <= 100){
            return "§6" + ping;
        }else if (ping <= 250){
            return "§c" + ping;
        }else{
            return "§4" + ping;
        }
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
            Waifuhax.Log("1");
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
            Waifuhax.Log("2");
            xPos.setValue((int)(mouseX - dragX));
            yPos.setValue((int)(mouseY - dragY));
        }
    }
}

