package fr.azion.sothis.api.event;

import fr.azion.sothis.api.database.DatabaseManager;
import fr.azion.sothis.api.managers.UserManager;
import fr.azion.sothis.api.pojo.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    private DatabaseManager databaseManager;
    private UserManager userManager;

    public Join(DatabaseManager databaseManager, UserManager userManager) {
        this.databaseManager = databaseManager;
        this.userManager = userManager;
    }

    private void addGuild(User user) {
        databaseManager.getUsers().insertOne(user);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        addGuild(new User(player.getUniqueId().toString(), player.getName(),0, null, null));
    }
}
