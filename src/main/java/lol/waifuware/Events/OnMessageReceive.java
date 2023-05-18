package lol.waifuware.Events;

import meteordevelopment.orbit.ICancellable;

import java.util.UUID;

public class OnMessageReceive  implements ICancellable
{
    private static final OnMessageReceive ON_MESSAGE_SEND = new OnMessageReceive();
    private String message;
    private boolean modified;

    private String sender;

    private boolean cancelled;

    public static OnMessageReceive get(String message, String sender) {
        ON_MESSAGE_SEND.setCancelled(false);
        ON_MESSAGE_SEND.message = message;
        ON_MESSAGE_SEND.modified = false;
        ON_MESSAGE_SEND.cancelled = false;
        ON_MESSAGE_SEND.sender = sender;
        return ON_MESSAGE_SEND;
    }

    public static OnMessageReceive get(){
        return ON_MESSAGE_SEND;
    }

    @Override
    public void setCancelled(boolean cancelled)
    {
        this.cancelled = cancelled;
    }

    public String getSender(){
        return sender;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
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
