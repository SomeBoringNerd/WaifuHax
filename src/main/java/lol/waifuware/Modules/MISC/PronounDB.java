package lol.waifuware.Modules.MISC;

import lol.waifuware.Events.OnMessageReceive;
import lol.waifuware.Events.OnMessageSend;
import lol.waifuware.Events.OnTickEvent;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.Interfaces.Module;
import lol.waifuware.Util.ChatUtil;
import lol.waifuware.Waifuhax;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.world.GameMode;

import java.util.HashMap;
import java.util.UUID;

@Module(name = "PronounDB", key = 0, cat = CATEGORY.MISC)
public class PronounDB extends AbstractModule
{

    private HashMap<UUID, String> playerModMap = new HashMap<>();

    public PronounDB()
    {
        desc[0] = "Display pronouns of player";
        desc[1] = "in chat overlay / nametag";
        desc[2] = "if they have a https://pronoundb.org";
        desc[3] = "account";
        Create();
    }

    @EventHandler
    public void onMessage(OnMessageReceive event)
    {
        if(playerModMap.containsKey(event.getSender()))
        {

        }
    }
}
