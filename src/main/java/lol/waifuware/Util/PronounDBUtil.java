package lol.waifuware.Util;

import lol.waifuware.Waifuhax;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;

public class PronounDBUtil
{

    public static String addDashesToUUID(String uuid) {
        String dashedUUID = "";
        dashedUUID += uuid.substring(0, 8) + "-";
        dashedUUID += uuid.substring(8, 12) + "-";
        dashedUUID += uuid.substring(12, 16) + "-";
        dashedUUID += uuid.substring(16, 20) + "-";
        dashedUUID += uuid.substring(20);
        return dashedUUID;
    }

    private static String getUUIDFromUsername(String username)
    {
        StringBuilder response = new StringBuilder();
        AtomicReference<String> _UUID = new AtomicReference<>("");

        Thread apiThread = new Thread(() -> {
            try {
                String MojangEndpoint = "https://api.mojang.com/users/profiles/minecraft/" + username;
                URL url = new URL(MojangEndpoint);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    JSONObject json = new JSONObject(response.toString());
                    System.out.println(json.toString());
                    _UUID.set(json.getString("id"));
                    reader.close();
                }
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        apiThread.start();

        try {
            apiThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return _UUID.get();
    }

    public static String callPronounDBApi(String username)
    {
        StringBuilder response = new StringBuilder();
        AtomicReference<String> Pronouns = new AtomicReference<>("");

        Thread apiThread = new Thread(() -> {
            try {
                String pronounDBEndpoint = "https://pronoundb.org/api/v1/lookup?platform=minecraft&id=%UUID%";

                pronounDBEndpoint = pronounDBEndpoint.replace("%UUID%", addDashesToUUID(getUUIDFromUsername(username)));
                URL url = new URL(pronounDBEndpoint);
                Waifuhax.Log(pronounDBEndpoint);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    JSONObject json = new JSONObject(response.toString());
                    System.out.println(json.toString());
                    Pronouns.set(json.getString("pronouns"));
                    reader.close();
                }
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        apiThread.start();

        try {
            apiThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return getFormatedPronouns(Pronouns.get());
    }

    private static String getFormatedPronouns(String value){
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
            case "unspecified":
                return "unspecified";
            default:
                return "UNAVAILABLE";
        }
    }

}
