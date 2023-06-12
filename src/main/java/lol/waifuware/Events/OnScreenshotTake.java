package lol.waifuware.Events;

import meteordevelopment.orbit.ICancellable;

public class OnScreenshotTake implements ICancellable
{
    private static final OnScreenshotTake ON_SCREENSHOT_TAKE = new OnScreenshotTake();

    public static OnScreenshotTake get() {
        ON_SCREENSHOT_TAKE.setCancelled(false);
        return ON_SCREENSHOT_TAKE;
    }

    @Override
    public void setCancelled(boolean cancelled) {

    }

    @Override
    public boolean isCancelled() {
        return false;
    }
}
