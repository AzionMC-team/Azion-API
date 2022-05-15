package fr.sothis.api.socket;

import com.google.gson.JsonObject;
import fr.sothis.api.AzionAPI;
import fr.sothis.api.listeners.ListenerManager;
import fr.sothis.api.pojo.Report;
import io.socket.client.Ack;
import io.socket.client.Socket;
import org.json.JSONException;
import org.json.JSONObject;

public class ServerInterface {

    private Socket socket;

    public ServerInterface(Socket socket, ListenerManager listenerManager) {
        this.socket = socket;

        socket.on("create report", args -> {
            Report report = AzionAPI.getInstance().getReportManager().getReport((String) args[0]);
            listenerManager.deflectReportListener(report);
        });
    }

}
