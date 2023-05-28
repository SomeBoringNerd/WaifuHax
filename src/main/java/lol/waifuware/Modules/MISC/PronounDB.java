package lol.waifuware.Modules.MISC;

import lol.waifuware.Events.OnMessageReceive;
import lol.waifuware.Events.OnMessageSend;
import lol.waifuware.Events.OnTickEvent;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.Interfaces.Module;
import lol.waifuware.Settings.BooleanSetting;
import lol.waifuware.Util.ChatUtil;
import lol.waifuware.Waifuhax;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.world.GameMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

@Module(name = "PronounDB", key = 0, cat = CATEGORY.MISC)
public class PronounDB extends AbstractModule
{

    private HashMap<String, String> playerModMap = new HashMap<>();

    private BooleanSetting debugMessage = new BooleanSetting("Debug messages", true, "e", "sdm");

    public PronounDB()
    {
        desc[0] = "Display pronouns of player";
        desc[1] = "in chat overlay / nametag";
        desc[2] = "if they have a https://pronoundb.org";
        desc[3] = "account";

        addSettings(debugMessage);
        isWorkInProgress = true;
        Create();
    }

    @EventHandler
    public void onMessage(OnMessageReceive event)
    {
        if(playerModMap.containsKey(event.getSender()))
        {
            if(debugMessage.getEnabled())
            {
                ChatUtil.SendMessage(event.getSender() + " : " + event.getMessage());
            }
        }
    }

    @EventHandler
    public void onTick(OnTickEvent tickEvent)
    {
        if(MinecraftClient.getInstance().world == null) return;
        if(MinecraftClient.getInstance().player == null) return;
        if(MinecraftClient.getInstance().isInSingleplayer()) return;

        for (PlayerListEntry pl: MinecraftClient.getInstance().player.networkHandler.getPlayerList())
        {
            if(!playerModMap.containsKey(pl))
            {

            }
        }
    }


}
