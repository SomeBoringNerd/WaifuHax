package lol.waifuware.Commands.Interfaces;

import java.io.IOException;

public class BadCommandException extends Exception
{
    BadCommandException()
    {
        super();
    }

    public BadCommandException(String message) {
        super(message);
    }
}
