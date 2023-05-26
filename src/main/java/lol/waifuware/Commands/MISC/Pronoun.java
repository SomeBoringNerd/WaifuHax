package lol.waifuware.Commands.MISC;

import lol.waifuware.Commands.Interfaces.AbstractCommand;
import lol.waifuware.Commands.Interfaces.BadCommandException;
import lol.waifuware.Commands.Interfaces.Command;
import lol.waifuware.Util.ChatUtil;
import lol.waifuware.Util.PronounDBUtil;
import net.minecraft.client.MinecraftClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Command(name = "pronoun", usage = "-pronoun <username>", description = "give the pronouns of a player for you, assuming they have set up PronounDB")
public class Pronoun extends AbstractCommand
{

    public static String username, self_pronoun = "UNAVAILABLE";

    @Override
    public void Execute(String[] command) throws BadCommandException {
        if(command.length > 0){
            username = command[1];

            // the new system is more usable, but now I need
            // to multithread the PronounDBUtil call. Fuck me.
            Thread CallTheFuckingCode = new Thread(() -> {
                String pronouns = PronounDBUtil.callPronounDBApi(username);

                switch (pronouns) {
                    case "ASK" -> {
                        ChatUtil.SendMessage(username + " want you to ask for pronouns");
                    }
                    case "EMPTY" -> {
                        ChatUtil.SendMessage(username + " do not have pronoundb");
                    }
                    default ->
                    {
                        if (pronouns.equals("UNAVAILABLE"))
                        {
                            ChatUtil.SendMessage("the username you mentioned is not linked to any existing minecraft account.");
                        }
                        else
                        {
                            ChatUtil.SendMessage(username + " use the pronouns " + pronouns);
                        }
                    }
                }
            });
            CallTheFuckingCode.start();
        }
        else
        {
            throw new BadCommandException(getName() + " : " + getUsage());
        }
    }
}


