package lol.waifuware.ClickGUI;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lol.waifuware.ClickGUI.SettingPanel.SettingPanelBase;
import lol.waifuware.ClickGUI.SettingPanel.SliderPanel;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.ModuleManager;
import lol.waifuware.Modules.GUI.ClickGUI;
import lol.waifuware.Settings.BooleanSetting;
import lol.waifuware.Settings.IntSetting;
import lol.waifuware.Settings.ModeSetting;
import lol.waifuware.Settings.Setting;
import lol.waifuware.Util.ChatUtil;
import lol.waifuware.Waifuhax;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import org.json.JSONObject;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CategoryPanel
{
    public int /*x, y,*/ width, height, dragX, dragY;
    public CATEGORY category;

    public IntSetting xSet = new IntSetting("xSet", 0, 9999, 0, 1, "X position of a panel", "x");
    public IntSetting ySet = new IntSetting("ySet", 0, 9999, 0, 1, "Y position of a panel", "y");

    public boolean isPressed;

    public boolean onModule;

    private List<ModuleButton> modList;
    private List<Setting> settings;
    int moduleAmount = 0;
    public CategoryPanel(CATEGORY category, int x, int y, int width, int height)
    {
        this.category = category;
        xSet.setValue(x);
        ySet.setValue(y);
        this.width = width;
        this.height = height;

        onModule = true;

        settings = new ArrayList<>();
        modList = new ArrayList<>();

        int offset = 16;

        settings.add(xSet);
        settings.add(ySet);

        for(AbstractModule mod : ModuleManager.getInstance().getModsInCat(category))
        {
            modList.add(new ModuleButton(mod, this, offset));
            offset += 16;
        }

        moduleAmount = ModuleManager.getInstance().getModsInCat(category).size();

        this.height = offset + 2;
        Create();
    }

    boolean showModule()
    {
        return !modList.isEmpty() && onModule;
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta)
    {
        DrawableHelper.fill(matrices, xSet.getValueInt() - 2, ySet.getValueInt() - 2, xSet.getValueInt() + width + 2, (showModule() ? ySet.getValueInt() + height + 2 + offset: ySet.getValueInt() + 13), ClickGUI.getInstance().getColor("MainColor").getRGB());

        DrawableHelper.fill(matrices, xSet.getValueInt(), ySet.getValueInt(), xSet.getValueInt() + width, (showModule() ? ySet.getValueInt() + height + offset : ySet.getValueInt() + 12 ), ClickGUI.getInstance().getColor("BackgroundColor").getRGB());

        DrawableHelper.fill(matrices, xSet.getValueInt() - 2, ySet.getValueInt() + 12, xSet.getValueInt() + width + 2, ySet.getValueInt() + 14 , ClickGUI.getInstance().getColor("MainColor").getRGB());

        MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, "『" + category.name + "』", xSet.getValueInt() + 2 , ySet.getValueInt() + 2, Color.white.getRGB());

        MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, "[" + moduleAmount + "]", xSet.getValueInt() + (width - 20) , ySet.getValueInt() + 2, Color.white.getRGB());

        if(showModule())
        {
            for (ModuleButton butt : modList)
            {
                butt.render(matrices, mouseX, mouseY, delta);
            }
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button)
    {
        if(isHovered(mouseX, mouseY) && button == 0)
        {
            isPressed = true;
            dragX = (int) (mouseX - xSet.getValueInt());
            dragY = (int) (mouseY - ySet.getValueInt());
        }
        else if(isHovered(mouseX, mouseY) && button == 1)
        {
            onModule = !onModule;
        }

        for(ModuleButton butt : modList)
        {
            butt.mouseClicked(mouseX, mouseY, button);
        }
    }

    public void mouseRelease(double mouseX, double mouseY, int button)
    {
        if(button == 0 && isPressed) isPressed = false;

        for(ModuleButton buttons : modList){
            buttons.mouseRelease(mouseX, mouseY, button);
        }

        Save();
    }

    public void UpdatePosition(double mouseX, double mouseY)
    {
        if(isPressed){
            xSet.setValue((int)(mouseX - dragX));
            ySet.setValue((int)(mouseY - dragY));
        }
    }

    public boolean isHovered(double mouseX, double mouseY)
    {
        return (mouseX > xSet.getValueInt()) && (mouseX < xSet.getValueInt() + width) && (mouseY > ySet.getValueInt()) && (mouseY < ySet.getValueInt() + 14);
    }

    int offset = 0;

    public void Update()
    {
        offset = 0;
        if (onModule)
        {
            for(ModuleButton button : modList)
            {
                button.offset = button.defaultOffset + offset;
                if(button.extended)
                {
                    for(SettingPanelBase panel : button.panels)
                    {
                        if(panel.setting.getVisible())
                        {
                            if(panel instanceof SliderPanel) offset += 26;
                            else offset += 18;
                        }
                    }
                }
            }
        }
    }


    /**********
     *  You like spaghetti ?
     **********/

    public void Save()
    {
        JSONObject object = new JSONObject();

        for(Setting setting : settings)
        {
            if(setting instanceof IntSetting e)
            {
                object.append((String) setting.name, e.getValue());
            }
        }

        try
        {
            FileWriter writer = new FileWriter("WaifuHax/cat/" + category.name + ".WaifuConfig");

            writer.write(object.toString());

            writer.close();
        }
        catch (IOException e)
        {
            Waifuhax.Log("A panel couldn't be saved : " + e.toString());
        }
    }
    public void Create()
    {
        File file = new File("WaifuHax/cat/" + category.name + ".WaifuConfig");
        if(!file.exists())
        {
            try
            {
                file.createNewFile();

                JSONObject object = new JSONObject();

                for(Setting setting : settings)
                {
                    if(setting instanceof IntSetting e)
                    {
                        object.append((String) setting.name, e.getValue());
                    }
                }

                try
                {
                    FileWriter writer = new FileWriter("WaifuHax/cat/" + category.name + ".WaifuConfig");

                    writer.write(object.toString());

                    writer.close();
                }catch (IOException ignored){}
            }
            catch (IOException e){
                Waifuhax.Log("A panel couldn't be created : " + e.toString());
            }
        }
        else
        {
            Load();
        }
    }

    public void Load()
    {
        try
        {
            Waifuhax.Log("Load " + category.name);
            JsonParser jsonP = new JsonParser();

            JsonObject json = (JsonObject)jsonP.parse(new FileReader("WaifuHax/cat/" + category.name + ".WaifuConfig"));

            //settings.clear();
            for(Map.Entry entry : json.entrySet())
            {
                for(Setting setting : settings)
                {
                    // fuck you wiga
                    if(setting.name.trim().equalsIgnoreCase(entry.getKey().toString().trim()))
                    {
                        IntSetting e = (IntSetting)setting;
                        e.setValue(Double.parseDouble(entry.getValue().toString().replace("[", "").replace("]", "").replace("\"", "")));
                    }
                }
            }
        }
        catch (IOException e)
        {
            Waifuhax.Log("A panel couldn't be loaded : " + e.toString());
        }
    }
}
