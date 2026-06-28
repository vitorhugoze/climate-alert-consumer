package org.application.services;

import com.google.gson.Gson;
import okhttp3.*;
import org.application.services.dto.SendTextMessageDTO;
import org.infrastructure.database.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MessagingService {

    private static final String API_URL = System.getenv("WAHA_URL") != null ? System.getenv("WAHA_URL") : "http://localhost:12052";
    private static final String API_KEY = System.getenv("WAHA_API_KEY");
    private static final Gson gson = new Gson();

    public static Boolean sendWhatsAppMessage(String chatId, String message) {
        OkHttpClient client = new OkHttpClient();

        SendTextMessageDTO sendTextMessageDTO = new SendTextMessageDTO(chatId, message, "default");

        Request request = new Request.Builder()
                .url(API_URL + "/api/sendText")
                .header("X-Api-Key", API_KEY)
                .post(RequestBody.create(gson.toJson(sendTextMessageDTO), MediaType.parse("application/json")))
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.isSuccessful();
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static List<String> getAlertDestinations() {
        List<String> destinations = new ArrayList<>();

        try (Connection connection = Database.getConnection(); Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT ID, CHAT_ID FROM TB_DESTINATIONS")) {
                while (rs.next()) {
                    String chatId = rs.getString("CHAT_ID");
                    destinations.add(chatId);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return destinations;
    }

}
