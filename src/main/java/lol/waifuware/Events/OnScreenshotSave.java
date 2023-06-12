package lol.waifuware.Events;

import meteordevelopment.orbit.ICancellable;

public class OnScreenshotSave implements ICancellable
{
    private static final OnScreenshotSave ON_SCREENSHOT_TAKE = new OnScreenshotSave();

    public static OnScreenshotSave get() {
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
