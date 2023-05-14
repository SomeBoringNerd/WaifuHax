package lol.waifuware.Events;

import meteordevelopment.orbit.ICancellable;

public class OnMessageSend implements ICancellable
{
    private static final OnMessageSend ON_MESSAGE_SEND = new OnMessageSend();
    private String message;
    private boolean modified;

    public static OnMessageSend get(String message) {
        ON_MESSAGE_SEND.setCancelled(false);
        ON_MESSAGE_SEND.message = message;
        ON_MESSAGE_SEND.modified = false;
        return ON_MESSAGE_SEND;
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
