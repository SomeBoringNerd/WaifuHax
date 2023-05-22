package lol.waifuware.Events;

import meteordevelopment.orbit.ICancellable;

public class OnPlayerConnect implements ICancellable
{

    public static final OnPlayerConnect ON_PLAYER_DISCONNECT = new OnPlayerConnect();
    private String player;

    public static OnPlayerConnect get(String username)
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
