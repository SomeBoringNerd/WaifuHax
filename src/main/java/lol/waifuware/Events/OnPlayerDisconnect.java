package lol.waifuware.Events;

import meteordevelopment.orbit.ICancellable;
import net.minecraft.client.network.PlayerListEntry;

public class OnPlayerDisconnect implements ICancellable
{

    public static final OnPlayerDisconnect ON_PLAYER_DISCONNECT = new OnPlayerDisconnect();
    private String player;

    public static OnPlayerDisconnect get(String username)
    {
        ON_PLAYER_DISCONNECT.player = username;
        return ON_PLAYER_DISCONNECT;
    }

    public String getPlayer()
    {
        return player;
    }

    @Override
    public void setCancelled(boolean cancelled) {

    }

    @Override
    public boolean isCancelled() {
        return false;
    }
}
