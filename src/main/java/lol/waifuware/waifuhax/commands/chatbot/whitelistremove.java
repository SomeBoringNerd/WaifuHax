package lol.waifuware.waifuhax.commands.chatbot;

import lol.waifuware.waifuhax.Modules.BOT.AutoTpa;
import lol.waifuware.waifuhax.commands.Command;
import lol.waifuware.waifuhax.util.ChatUtil;
import net.minecraft.client.MinecraftClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class whitelistremove extends Command {
    public whitelistremove(String name) {
        super(name);
    }

    @Override
    public void Execute(String command)
    {
        String[] array = command.split(" ");

        if (array.length >= 2)
        {
            new WhiteListRemoveRequest(array[2]);
        }
    }
}

class WhiteListRemoveRequest implements Runnable
{
    private String _Player;
    private static final String USER_AGENT = "Mozilla/5.0";
    public WhiteListRemoveRequest(String player_name)
    {
        _Player = player_name;
    }

    private void autoTpa() throws IOException
    {
        URL obj = new URL("https://feurgroup-api.falling-from.space/remove");

        ChatUtil.SendMessage("trying to call api with following args : ");
        ChatUtil.SendMessage("username=" + _Player);


        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
        httpURLConnection.setRequestProperty("username", _Player);
        httpURLConnection.setRequestProperty("password", "FeurGroupOnTopStayMadMotherfucker");

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

            ChatUtil.SendMessage("Player " + _Player + " was removed from the whitelist.");
            MinecraftClient.getInstance().player.sendCommand("msg " + _Player + " you have been removed from the FeurGroup's whitelist.");
        }else{
            ChatUtil.SendMessage("An error has occured");
        }
    }

    @Override
    public void run()
    {

    }
}