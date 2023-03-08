package lol.waifuware.Commands.Interfaces;

import lol.waifuware.Events.OnMessageReceive;
import meteordevelopment.orbit.EventHandler;

public interface ICommand
{
    void Execute(String[] command);
}
