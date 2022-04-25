package fr.azion.mathis_bruel.api.api.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.azion.mathis_bruel.api.DataBase.connection;
import fr.azion.mathis_bruel.api.API;
import org.bukkit.entity.Player;


public class Rank {
	
	API main;
	public Rank(API main) {
		this.main = main;
	}
	public boolean setRankPlayer(Player player, String rank) {
		String playerUUID = player.getUniqueId().toString();
		final connection connection2 = API.getdbDbmanageur().getConnection();
		try {
			final Connection connection = connection2.getConnection();
			String query = "SELECT UUID, Money, Rank, Civilisation, Permissions FROM Player WHERE UUID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, playerUUID);
			final ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				PreparedStatement preparedStatement2 = connection.prepareStatement("UPDATE `Player` SET `Rank`=? WHERE UUID=?");
				preparedStatement2.setString(1, rank);
				preparedStatement2.setString(2, playerUUID);
				preparedStatement2.executeUpdate();
				preparedStatement2.close();
				resultSet.close();
				connection.close();
				preparedStatement.close();
				return true;
			}else {
				resultSet.close();
				connection.close();
				preparedStatement.close();
				System.out.println("Une erreur c'est produite. Ce joueur n'est pas inscrit.");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean setRankPlayerUUID(String playerUUID, String rank) {
		final connection connection2 = API.getdbDbmanageur().getConnection();
		try {
			final Connection connection = connection2.getConnection();
			String query = "SELECT UUID, Money, Rank, Civilisation, Permissions FROM Player WHERE UUID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, playerUUID);
			final ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				PreparedStatement preparedStatement2 = connection.prepareStatement("UPDATE `Player` SET `Rank`=? WHERE UUID=?");
				preparedStatement2.setString(1, rank);
				preparedStatement2.setString(2, playerUUID);
				preparedStatement2.executeUpdate();
				preparedStatement2.close();
				resultSet.close();
				connection.close();
				preparedStatement.close();
				return true;
			}else {
				resultSet.close();
				connection.close();
				preparedStatement.close();
				System.out.println("Une erreur c'est produite. Ce joueur n'est pas inscrit. ");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public String getRankPlayer(Player player) {
		String playerUUID = player.getUniqueId().toString();
		final connection connection2 = API.getdbDbmanageur().getConnection();
		try {
			final Connection connection = connection2.getConnection();
			String query = "SELECT UUID, Money, Rank, Civilisation, Permissions FROM Player WHERE UUID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, playerUUID);
			final ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				
				return resultSet.getString("Rank");
				
			}else {
				System.out.println("Une erreur c'est produite. Ce joueur n'est pas inscrit.");
			}
			resultSet.close();
			connection.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	public String getRankPlayerUUID(String playerUUID) {
		final connection connection2 = API.getdbDbmanageur().getConnection();
		try {
			final Connection connection = connection2.getConnection();
			String query = "SELECT UUID, Money, Rank, Cvilisation FROM Player WHERE UUID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, playerUUID);
			final ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				
				return resultSet.getString("Rank");
				
			}else {
				System.out.println("Une erreur c'est produite. Ce joueur n'est pas inscrit.");
			}
			resultSet.close();
			connection.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
}
