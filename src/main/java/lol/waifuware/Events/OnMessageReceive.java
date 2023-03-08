package lol.waifuware.Events;

import meteordevelopment.orbit.ICancellable;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.text.Text;

public class OnMessageReceive implements ICancellable
{
    private static final OnMessageReceive onMessageReceive = new OnMessageReceive();
    private String message;
    private boolean modified;

    public static OnMessageReceive get(String message) {
        onMessageReceive.setCancelled(false);
        onMessageReceive.message = message;
        onMessageReceive.modified = false;
        return onMessageReceive;
    }




    @Override
    public void setCancelled(boolean cancelled)
    {

    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    public String getMessage()
    {
        return message;
    }

    public boolean isModified(){return modified;}

    public void setMessage(String message)
    {
        this.message = message;
        this.modified = true;
    }
}
