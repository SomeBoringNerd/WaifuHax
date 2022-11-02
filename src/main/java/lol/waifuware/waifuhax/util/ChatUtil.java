package lol.waifuware.waifuhax.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public class ChatUtil
{
    public static void SendMessage(String message)
    {
        MutableText msg = Text.literal("[§dWaifuHax§r] > " + message);

        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(msg);
    }
}
