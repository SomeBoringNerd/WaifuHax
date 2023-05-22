package lol.waifuware.Commands.Interfaces;

import lol.waifuware.Events.OnMessageSend;
import lol.waifuware.Util.ChatUtil;
import lol.waifuware.Waifuhax;
import meteordevelopment.orbit.EventHandler;

public abstract class AbstractCommand implements ICommand
{
    private final String name, usage, desc;

    public String getName(){
        return name;
    }
    public String getUsage(){
        return usage;
    }
    public String getDesc(){
        return desc;
    }

    public AbstractCommand()
    {
        Command command = this.getClass().getAnnotation(Command.class);
        Waifuhax.EVENT_BUS.subscribe(this);
        name = command.name();
        usage = command.usage();
        desc = command.description();
    }

    @EventHandler
    public void MessageEvent(OnMessageSend event)
    {
        if(!event.getMessage().startsWith("-" + name)) return;
        try {
            Execute(event.getMessage().split(" "));
        }catch (BadCommandException e){
            ChatUtil.SendMessage(e.getMessage());
        }
    }
}
