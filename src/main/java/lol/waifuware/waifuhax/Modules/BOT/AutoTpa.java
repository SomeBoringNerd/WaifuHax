package lol.waifuware.waifuhax.Modules.BOT;

import lol.waifuware.waifuhax.GlobalVariables;
import lol.waifuware.waifuhax.Modules.Module;
import lol.waifuware.waifuhax.Waifuhax;
import lol.waifuware.waifuhax.util.ChatUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AutoTpa extends Module
{

    public static List<String> whitelist = new ArrayList();

    public AutoTpa(String name, int Key)
    {
        super(name, Key);
    }

    private void getWhitelist(String playerName) throws IOException
    {
        Thread t = new Thread(new ThreadedRequest(playerName), "request");
        t.start();
    }

    @Override
    public void onChat(String message)
    {
        if(message.startsWith("Type /tpy"))
        {
            String[] array = message.split(" ");

            try {
                getWhitelist(array[2]);
            } catch (IOException e) {
                ChatUtil.SendMessage(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void Update() {

    }

    @Override
    public void Render(MatrixStack matrice) {

    }

    @Override
    public void onActivate() {

    }

    @Override
    public void onDisable() {

    }

    public static String getTpaBot(String BaseName)
    {
        switch (BaseName){
            case "achille":
                return "SomeBoringNerd";
            case "degand":
                return "EzN1GGER";
            default:
                return "${b0T} (there is no tpbot at this base)";
        }
    }
}

class ThreadedRequest implements Runnable
{
    private String _Player;
    private static final String USER_AGENT = "Mozilla/5.0";
    public ThreadedRequest(String player_name)
    {
        _Player = player_name;
    }

    private void autoTpa() throws IOException
    {
        URL obj = new URL("https://feurgroup-api.falling-from.space/member?member=" + _Player.toLowerCase());

        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = httpURLConnection.getResponseCode();
        ChatUtil.SendMessage("Response from API : " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject json = new JSONObject(response.toString());
            System.out.println(json.toString());

            int code = json.getInt("returnCode");
            ChatUtil.SendMessage("API returned §4" + code + "§r");
            switch (code){
                case 200:
                    ChatUtil.SendMessage(json.getString("canAccessAllBases"));
                    ChatUtil.SendMessage(json.getString("assignedBase"));
                    ChatUtil.SendMessage(json.getString("message"));

                    if(MinecraftClient.getInstance().player.getEntityName().equals("SomeBoringNerd"))
                    {
                        if(json.getString("canAccessAllBases").equals("yes") || json.getString("assignedBase").equals("achille"))
                        {
                            MinecraftClient.getInstance().player.sendCommand("tpy " + json.getString("message"));
                        }else
                        {
                            MinecraftClient.getInstance().player.sendCommand("msg " + json.getString("message") + " you are not assigned to this base, but the base " + json.getString("assignedBase") + ". TPA to " + AutoTpa.getTpaBot(json.getString("assignedBase")) + " in order to teleport to your base");
                            MinecraftClient.getInstance().player.sendCommand("tpn " + _Player);
                        }
                    }else if(MinecraftClient.getInstance().player.getEntityName().equals("EzN1GGER"))
                    {
                        if(json.getString("canAccessAllBases").equals("yes") || json.getString("assignedBase").equals("degand"))
                        {
                            MinecraftClient.getInstance().player.sendCommand("tpy " + json.getString("message"));
                        }else
                        {
                            MinecraftClient.getInstance().player.sendCommand("msg " + json.getString("message") + " you are not assigned to this base, but the base " + json.getString("assignedBase") + ". TPA to " + AutoTpa.getTpaBot(json.getString("assignedBase")) + " in order to teleport to your base");
                            MinecraftClient.getInstance().player.sendCommand("tpn " + _Player);
                        }
                    }
                    else
                    {
                        MinecraftClient.getInstance().player.sendCommand("msg " + _Player + " you are not a member of FeurGroup or whitelisted to one of our base, but you can join us to fix that mistake here : discord.gg/ReK658v4bs");
                        MinecraftClient.getInstance().player.sendCommand("tpn " + _Player);
                    }
                    break;
                case 404:
                    MinecraftClient.getInstance().player.sendCommand("msg " + _Player + " you are not a member of FeurGroup or whitelisted to one of our base, but you can join us to fix that mistake here : discord.gg/ReK658v4bs");
                    MinecraftClient.getInstance().player.sendCommand("tpn " + _Player);
                    break;
                default:
                    MinecraftClient.getInstance().player.sendCommand("msg " + _Player + " against all ods, you somehow managed to trigger an impossible event to occur. Are you proud ? (you should be)");
                    MinecraftClient.getInstance().player.sendCommand("tpn " + _Player);
                    break;
            }
        }else{
            MinecraftClient.getInstance().player.sendCommand("msg " + _Player + " an unknown error has occured and I can't communicate with the API at the moment. here's the error code : " + responseCode);
            MinecraftClient.getInstance().player.sendCommand("tpn " + _Player);
        }
    }

    @Override
    public void run()
    {
        try {
            autoTpa();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}