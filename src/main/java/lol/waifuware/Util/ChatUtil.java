package lol.waifuware.Util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public class ChatUtil
{
    public static void SendMessage(String message)
    {
        MutableText msg = Text.literal("§4[§dWaifuHax§4]§r > " + message);
        if(MinecraftClient.getInstance().inGameHud != null) {
            MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(msg);
        }
    }

    public static void ClearLine()
    {
        MutableText msg = Text.literal("     ");
        if(MinecraftClient.getInstance().inGameHud != null) {
            MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(msg);
        }
    }
}
