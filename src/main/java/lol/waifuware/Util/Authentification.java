package lol.waifuware.Util;

import net.minecraft.client.MinecraftClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Authentification
{
    public static int Authentificate()
    {
        AtomicReference<Integer> ApiResponse = new AtomicReference<>(301);
        try
        {
            HttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost("http://api.falling-from.space/auth");

// Request parameters and other properties.
            List<NameValuePair> params = new ArrayList<NameValuePair>(2);
            params.add(new BasicNameValuePair("uuid", MinecraftClient.getInstance().getSession().getUuid()));
            params.add(new BasicNameValuePair("username", MinecraftClient.getInstance().getSession().getUsername()));
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

//Execute and get the response.
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                try (InputStream inputStream = entity.getContent()) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                    StringBuilder responseBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        responseBuilder.append(line);
                    }
                    String responseString = responseBuilder.toString();
                    ChatUtil.Log(responseString);

                    // Process the response and extract the desired information.
                    // For example, if the response is in JSON format, you can use a JSON library to parse it.
                    JSONObject jsonResponse = new JSONObject(responseString);
                    int code = jsonResponse.getInt("code");
                    ApiResponse.set(code);
                }
            }
        } catch (IOException ignored){}

        return ApiResponse.get();
    }

    public static String getLatestCommitHash()
    {
        AtomicReference<String> ApiResponse = new AtomicReference<>("0x0000000");
        try
        {
            HttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost("http://api.falling-from.space/hash");

            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                try (InputStream inputStream = entity.getContent()) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                    StringBuilder responseBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        responseBuilder.append(line);
                    }
                    String responseString = responseBuilder.toString();
                    ChatUtil.Log(responseString);

                    // Process the response and extract the desired information.
                    // For example, if the response is in JSON format, you can use a JSON library to parse it.
                    JSONObject jsonResponse = new JSONObject(responseString);
                    String hash = "";
                    try {
                        hash = jsonResponse.getString("hash").substring(0, 6);
                    }catch (StringIndexOutOfBoundsException e)
                    {
                        hash = "0x00000";
                    }

                    ApiResponse.set(hash);
                }
            }
        } catch (IOException ignored){}

        return ApiResponse.get();
    }
}
