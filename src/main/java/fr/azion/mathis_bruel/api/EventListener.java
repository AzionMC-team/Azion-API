package fr.azion.mathis_bruel.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import fr.azion.mathis_bruel.api.DataBase.connection;
import org.bukkit.Bukkit;
import org.bukkit.command.defaults.SpreadPlayersCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.help.HelpTopic;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

public class EventListener implements Listener {

	API main;
	public EventListener(API main) {
		this.main = main;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String playername = player.getName();
		final connection connection2 = API.getdbDbmanageur().getConnection();
		try {
			final Connection connection = connection2.getConnection();
			String query = "SELECT UUID, Money, Rank FROM Player WHERE UUID=?";
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			preparedStatement.setString(1, player.getUniqueId().toString());
			final ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				//d�j� enregistr�
			}else {
				//� enregistr�
				PreparedStatement preparedStatement2 = (PreparedStatement) connection.prepareStatement("INSERT INTO `Player`(`UUID`, `Money`, `Rank`, `Civilisation`, `Permissions`) VALUES (?,?,?,?,?)");
				preparedStatement2.setString(1, player.getUniqueId().toString());
				preparedStatement2.setInt(2, 0);
				preparedStatement2.setString(3, "Membre");
				preparedStatement2.setString(4, "");
				preparedStatement2.setString(5, "");
				preparedStatement2.executeUpdate();
				preparedStatement2.close();
			}
			resultSet.close();
			connection.close();
			preparedStatement.close();
		} catch (SQLException e) {
		}

		//add permissions
		API.getApi.permissionsManager.setupPermissions(player);

	}

	@EventHandler
	public void leave(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		API.getApi.attachment.remove(player.getUniqueId());
	}


}
