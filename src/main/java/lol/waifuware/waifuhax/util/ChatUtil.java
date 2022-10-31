package lol.waifuware.waifuhax.util;

import lol.waifuware.waifuhax.mixin.IChatHud;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public class ChatUtil
{
    public static void SendMessage(String message)
    {
        MutableText msg = Text.literal(message);

        ((IChatHud) MinecraftClient.getInstance().inGameHud.getChatHud()).add(msg, 0);
    }
}
