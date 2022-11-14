package lol.waifuware.waifuhax.commands.chatbot;

import lol.waifuware.waifuhax.Modules.BOT.AutoTpa;
import lol.waifuware.waifuhax.commands.Command;
import lol.waifuware.waifuhax.util.ChatUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.network.message.SentMessage;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class whitelistadd extends Command {

    public whitelistadd(String name) {
        super(name);
    }

    @Override
    public void Execute(String command) {
        String[] array = command.split(" ");

        if (array.length >= 5)
        {
            // array[2] : username
            // array[3] : AllClear
            // array[4] : base

            new ThreadedRequest(array[2], array);
        }
    }
}

class ThreadedRequest implements Runnable
{
    private String _Player;
    private String[] _Args;
    private static final String USER_AGENT = "Mozilla/5.0";
    public ThreadedRequest(String player_name, String[] arguments)
    {
        _Player = player_name;
        _Args = arguments;
    }

    private void autoTpa() throws IOException
    {
        URL obj = new URL("https://feurgroup-api.falling-from.space/add");

        ChatUtil.SendMessage("trying to call api with following args : ");
        ChatUtil.SendMessage("username=" + _Player);
        ChatUtil.SendMessage("AllClear=" + _Args[3]);
        ChatUtil.SendMessage("base=" + _Args[4]);


        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
        httpURLConnection.setRequestProperty("username", _Player);
        httpURLConnection.setRequestProperty("password", "FeurGroupOnTopStayMadMotherfucker");
        httpURLConnection.setRequestProperty("AllClear", _Args[3]);
        httpURLConnection.setRequestProperty("base", _Args[4]);

        int responseCode = httpURLConnection.getResponseCode();
        ChatUtil.SendMessage("Api returned code : " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            ChatUtil.SendMessage("Player " + _Player + " was added to the whitelist.");

        }else{
            ChatUtil.SendMessage("An error has occured");
        }
    }

    @Override
    public void run()
    {

    }
}