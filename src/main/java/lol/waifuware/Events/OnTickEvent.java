package lol.waifuware.Events;

import meteordevelopment.orbit.ICancellable;

public class OnTickEvent implements ICancellable
{
    private static OnTickEvent onTickEvent = new OnTickEvent();

    public static OnTickEvent get()
    {
        return onTickEvent;
    }
    @Override
    public void setCancelled(boolean cancelled) {

    }

    @Override
    public boolean isCancelled() {
        return false;
    }
}
