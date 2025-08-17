package net.nydt.nyskriptsa.utils;

import com.google.gson.Gson;
import net.nydt.nyskriptsa.objects.SAUser;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Request {
    public static SAUser retreiveUser(String username) {
        try {
            URL url = new URL("https://api.superawesome.dk/players/" + username);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000); // 5 sekunders timeout
            connection.setConnectTimeout(5000);

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                Gson gson = new Gson();
                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                SAUser user = gson.fromJson(reader, SAUser.class);
                reader.close();
                connection.disconnect();
                return user;
            } else {
                System.err.println("Fejl ved hentning af bruger: " + connection.getResponseCode());
                connection.disconnect();
                return null;
            }
        } catch (Exception e) {
            System.err.println("Forbindelsesfejl til API: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}