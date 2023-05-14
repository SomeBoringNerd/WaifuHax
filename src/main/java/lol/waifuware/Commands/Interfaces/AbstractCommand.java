package lol.waifuware.Commands.Interfaces;

import lol.waifuware.Events.OnMessageSend;
import lol.waifuware.Waifuhax;
import meteordevelopment.orbit.EventHandler;

public abstract class AbstractCommand implements ICommand
{
    private final String name;

    public String getName(){
        return name;
    }

    public AbstractCommand()
    {
        Command command = this.getClass().getAnnotation(Command.class);
        Waifuhax.EVENT_BUS.subscribe(this);
        name = command.name();
    }

    @EventHandler
    public void MessageEvent(OnMessageSend event)
    {
        if(!event.getMessage().startsWith("-" + name)) return;

        Execute(event.getMessage().split(" "));
    }
}
