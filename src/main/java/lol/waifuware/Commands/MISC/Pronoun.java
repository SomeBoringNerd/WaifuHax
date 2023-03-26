package lol.waifuware.Commands.MISC;

import lol.waifuware.Commands.Interfaces.AbstractCommand;
import lol.waifuware.Commands.Interfaces.Command;
import lol.waifuware.Util.ChatUtil;
import net.minecraft.client.MinecraftClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Command(name = "pronoun")
public class Pronoun extends AbstractCommand
{

    public static String username, self_pronoun;

    @Override
    public void Execute(String[] command)
    {
        if(command.length > 0){
            username = command[1];

            Thread t = new Thread(new ThreadedRequest(username), "request");
            t.start();

        }else{
            ChatUtil.SendMessage("§4ERROR : NOT ENOUGH ARGUMENTS PROVIDED !§r");
        }
    }

    public static String getFormatedPronouns(String value){
        switch (value){
            case "hh":
                return "he/him";
            case "hi":
                return "he/it";
            case "hs":
                return "he/she";
            case "ht":
                return "he/they";
            case "ih":
                return "it/him";
            case "ii":
                return "it/its";
            case "is":
                return "it/she";
            case "it":
                return "it/they";
            case "shh":
                return "she/he";
            case "sh":
                return "she/her";
            case "si":
                return "she/it";
            case "st":
                return "she/they";
            case "th":
                return "they/him";
            case "ti":
                return "they/its";
            case "ts":
                return "they/she";
            case "tt":
                return "they/they";
            default:
                return "%TO_REPLACE%";
        }
    }
}


