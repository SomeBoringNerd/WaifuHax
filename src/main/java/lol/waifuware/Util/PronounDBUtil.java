package lol.waifuware.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class PronounDBUtil
{

    public static String addDashesToUUID(String uuid)
    {
        if(uuid.isEmpty())
        {
            return "INVALID";
        }
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
                String pronounDBEndpoint = "https://pronoundb.org/api/v2/lookup?platform=minecraft&ids=%UUID%";
                String uid = addDashesToUUID(getUUIDFromUsername(username));
                if(uid.equals("INVALID"))
                {
                    Pronouns.set("UNAVAILABLE");
                }else {
                    pronounDBEndpoint = pronounDBEndpoint.replace("%UUID%", uid);
                    URL url = new URL(pronounDBEndpoint);
                    ChatUtil.Log(pronounDBEndpoint);

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

                        // if there's no pronoundb account linked to that minecraft UUID
                        if (Objects.equals(json.toString(), "{}"))
                        {
                            Pronouns.set("EMPTY");
                        }
                        else
                        {
                            JSONObject innerObject = new JSONObject(json.getJSONObject(addDashesToUUID(getUUIDFromUsername(username))).toString());
                            JSONObject setsObject = innerObject.getJSONObject("sets");
                            try {
                                JSONArray pronouns = setsObject.getJSONArray("en");

                                if (pronouns.length() == 0) {
                                    Pronouns.set("EMPTY");
                                } else if (pronouns.length() == 1) {
                                    Pronouns.set(getFormatedPronouns(pronouns.getString(0)));
                                } else
                                {
                                    for (int i = 0; i < pronouns.length(); i++) {
                                        Pronouns.set(Pronouns.get() + "/" + pronouns.getString(i));
                                    }
                                    Pronouns.set(Pronouns.get().substring(1));
                                }

                            }catch (JSONException e)
                            {
                                Pronouns.set(("UNSPECIFIED"));
                            }
                        }
                        reader.close();
                    }
                    connection.disconnect();
                }
            } catch (IOException e)
            {
                Pronouns.set("UNAVAILABLE");
                e.printStackTrace();
            }
        });

        apiThread.start();

        try {
            apiThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Pronouns.get();
    }

    private static String getFormatedPronouns(String value){
        switch (value){
            case "he":
                return "he/him";
            case "it":
                return "he/it";
            case "she":
                return "she/her";
            case "they":
                return "he/they";
            case "any":
                return "ANY";
            case "ask":
                return "ASK";
            case "avoid":
                return "AVOID";
            case "other":
                return "OTHER";
            case "UNSPECIFIED":
                return "UNSPECIFIED";
            default:
                return "UNAVAILABLE";
        }
    }

}
