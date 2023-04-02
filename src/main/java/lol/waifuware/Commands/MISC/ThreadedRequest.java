package lol.waifuware.Commands.MISC;

import lol.waifuware.Util.ChatUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public class ThreadedRequest implements Runnable {

    private String _UUID;
    private String _Player;
    private static final String USER_AGENT = "Mozilla/5.0";

    public boolean self;

    // note : don't remove %UUID% as it is a placeholder updated when needed

    private final String UUID_GETTER = "https://api.mojang.com/users/profiles/minecraft/%UUID%";
    private String PRONOUN_DB = "https://pronoundb.org/api/v1/lookup?platform=minecraft&id=%UUID%";

    public ThreadedRequest(String player_name) {
        _Player = player_name;
        this.self = false;
    }

    public ThreadedRequest(String player_name, boolean self) {
        _Player = player_name;
        this.self = self;
    }

    private void getUUID(String url_to_request) throws IOException {
        url_to_request = url_to_request.replace("%UUID%", _Player);
        URL obj = new URL(url_to_request);

        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();

        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = httpURLConnection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject json = new JSONObject(response.toString());
            System.out.println(json.toString());
            _UUID = json.getString("id");


            PRONOUN_DB = PRONOUN_DB.replace("%UUID%", addDashesToUUID(_UUID));
            getPronouns(PRONOUN_DB);
        } else if (!self) {
            ChatUtil.SendMessage("URL : " + UUID_GETTER);
            ChatUtil.SendMessage("GET request not worked");
        }
    }

    public static String addDashesToUUID(String uuid) {
        String dashedUUID = "";
        dashedUUID += uuid.substring(0, 8) + "-";
        dashedUUID += uuid.substring(8, 12) + "-";
        dashedUUID += uuid.substring(12, 16) + "-";
        dashedUUID += uuid.substring(16, 20) + "-";
        dashedUUID += uuid.substring(20);
        return dashedUUID;
    }

    private void getPronouns(String url_to_request) throws IOException {
        URL obj = new URL(url_to_request);
        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            JSONArray json = new JSONArray("[" + response.toString() + "]");

            for (int i = 0; i != json.length(); i++) {
                if (!Objects.equals(json.getJSONObject(i).getString("pronouns"), "unspecified")) {
                    if (!Pronoun.getFormatedPronouns(json.getJSONObject(i).getString("pronouns")).equals("%TO_REPLACE%")) {
                        if (!self) {
                            ChatUtil.SendMessage(Pronoun.username + "'s pronouns are : " + Pronoun.getFormatedPronouns(json.getJSONObject(i).getString("pronouns")));
                        } else {
                            Pronoun.self_pronoun = Pronoun.getFormatedPronouns(json.getJSONObject(i).getString("pronouns"));
                        }
                    } else {
                        switch (json.getJSONObject(i).getString("pronouns")) {
                            case "any" -> {
                                if (self) {
                                    Pronoun.self_pronoun = "any";
                                } else
                                    ChatUtil.SendMessage(Pronoun.username + " is okay with any pronouns");
                            }
                            case "other" -> {
                                if (self) {
                                    Pronoun.self_pronoun = "other";
                                } else
                                    ChatUtil.SendMessage(Pronoun.username + " use another set of pronouns that PronounDB don't index");
                            }
                            case "ask" -> {
                                if (self) {
                                    Pronoun.self_pronoun = "ask me";
                                } else ChatUtil.SendMessage(Pronoun.username + " prefer you ask directly");
                            }
                            case "avoid" -> {
                                if (self) {
                                    Pronoun.self_pronoun = "none";
                                } else
                                    ChatUtil.SendMessage(Pronoun.username + " would rather not use pronouns, refer to " + Pronoun.username + " by username");
                            }
                            default -> {
                                if (self) {
                                    Pronoun.self_pronoun = "unspecified";
                                } else
                                    ChatUtil.SendMessage(Pronoun.username + " either don't use PronounDB.org or haven't set their pronouns.");
                            }
                        }
                    }
                }
            }

            in.close();
        } else {
            ChatUtil.SendMessage("GET request not worked");
        }
    }

    @Override
    public void run() {
        try {
            getUUID(UUID_GETTER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
