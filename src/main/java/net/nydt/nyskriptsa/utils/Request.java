package net.nydt.nyskriptsa.utils;

import com.google.gson.Gson;
import net.nydt.nyskriptsa.NySkriptSA;
import net.nydt.nyskriptsa.objects.SAUser;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Request {
    public static SAUser retrieveUser(String username) {
        try {
            URL url = new URL("https://api.superawesome.dk/players/" + username);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                Gson gson = new Gson();
                try (InputStreamReader reader = new InputStreamReader(connection.getInputStream())) {
                    SAUser user = gson.fromJson(reader, SAUser.class);
                    connection.disconnect();
                    return user;
                }
            } else {
                NySkriptSA.getInstance().getLogger().warning("Fejl ved hentning af bruger: " + connection.getResponseCode());
                connection.disconnect();
                return null;
            }
        } catch (Exception e) {
            NySkriptSA.getInstance().getLogger().severe("Forbindelsesfejl til API: " + e.getMessage());
            return null;
        }
    }
}