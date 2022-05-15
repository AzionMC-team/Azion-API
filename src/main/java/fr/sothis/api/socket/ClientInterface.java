package fr.sothis.api.socket;

import com.google.gson.JsonObject;
import io.socket.client.Ack;
import io.socket.client.Socket;
import org.bukkit.Bukkit;
import org.json.JSONException;
import org.json.JSONObject;

public class ClientInterface {

    private Socket socket;

    public ClientInterface(Socket socket) {
        this.socket = socket;
    }

    public void sendCreationReport(String uuid) {
        socket.emit("create report", 1, uuid, (Ack) args -> {
            Bukkit.getLogger().info("Sending creation report");
        });
    }
}
