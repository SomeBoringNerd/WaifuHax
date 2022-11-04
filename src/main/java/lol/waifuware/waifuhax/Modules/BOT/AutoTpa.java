package lol.waifuware.waifuhax.Modules.BOT;

import lol.waifuware.waifuhax.GlobalVariables;
import lol.waifuware.waifuhax.Modules.Module;
import lol.waifuware.waifuhax.Waifuhax;
import lol.waifuware.waifuhax.util.ChatUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;
import java.util.List;

public class AutoTpa extends Module
{

    public static List<String> whitelist = new ArrayList();

    public AutoTpa(String name, int Key)
    {
        super(name, Key);

        whitelist.add("SomeBoringNerd");
        whitelist.add("DarkNicoMars");
        whitelist.add("CDara");
        whitelist.add("Flandreu");
        whitelist.add("Daaaivy");
        whitelist.add("Firedark98");
        whitelist.add("MrLollypop");
        whitelist.add("Anchor_Aura");
        whitelist.add("Variton");
        whitelist.add("Noctoack");
        whitelist.add("20ma");
        whitelist.add("2oma");
        whitelist.add("Freebox");
        whitelist.add("Neit420");
        whitelist.add("ValEstLa");
        whitelist.add("MrZimbabwe_");
        whitelist.add("Khyx");
        whitelist.add("EzN1gger");
    }

    @Override
    public void onChat(String message)
    {
        if(message.startsWith("Type /tpy"))
        {
            String[] array = message.split(" ");

            if(whitelist.contains(array[2])){
                MinecraftClient.getInstance().player.sendCommand("tpy " + array[2].trim());
                ChatUtil.SendMessage("/tpy " + array[2]);
            }else{
                MinecraftClient.getInstance().player.sendCommand("msg " + array[2] + " is seems like you are not a member of FeurGroup. you can join us here : https://discord.gg/ReK658v4bs");
            }
        }
    }

    @Override
    public void Update() {

    }

    @Override
    public void Render(MatrixStack matrice) {

    }

    @Override
    public void onActivate() {

    }

    @Override
    public void onDisable() {

    }
}
