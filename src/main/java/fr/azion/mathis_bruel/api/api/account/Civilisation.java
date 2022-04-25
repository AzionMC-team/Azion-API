package fr.azion.mathis_bruel.api.api.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.azion.mathis_bruel.api.DataBase.connection;
import fr.azion.mathis_bruel.api.API;
import org.bukkit.entity.Player;


public class Civilisation {

	API main;
	public Civilisation(API main) {
		this.main = main;
	}
	

	public void setCivilisationPlayer(Player player,String civilisation) {
		String playeruuid = player.getUniqueId().toString();
		final connection connection2 = API.getdbDbmanageur().getConnection();
		try {
			final Connection connection = connection2.getConnection();
			String query = "SELECT UUID, Money, Rank, Civilisation, Permissions FROM Player WHERE UUID=?";
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			preparedStatement.setString(1, playeruuid);
			final ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				PreparedStatement preparedStatement2 = (PreparedStatement) connection.prepareStatement("UPDATE `Player` SET `Civilisation`=? WHERE UUID=?");
				preparedStatement2.setString(1, civilisation);
				preparedStatement2.setString(2, playeruuid);
				preparedStatement2.executeUpdate();
				preparedStatement2.close();
			}else {
				System.out.println("Une erreur c'est produite. Ce joueur n'est pas inscrit.");
			}
			resultSet.close();
			connection.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void setCivilisationPlayerUUID(String playerUUID, String civilisation) {
		final connection connection2 = API.getdbDbmanageur().getConnection();
		try {
			final Connection connection = connection2.getConnection();
			String query = "SELECT UUID, Money, Rank, Civilisation, Permissions FROM Player WHERE UUID=?";
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			preparedStatement.setString(1, playerUUID);
			final ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				PreparedStatement preparedStatement2 = (PreparedStatement) connection.prepareStatement("UPDATE `Player` SET `Civilisation`=? WHERE UUID=?");
				preparedStatement2.setString(1, civilisation);
				preparedStatement2.setString(2, playerUUID);
				preparedStatement2.executeUpdate();
				preparedStatement2.close();
			}else {
				System.out.println("Une erreur c'est produite. Ce joueur n'est pas inscrit.");
			}
			resultSet.close();
			connection.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getCivilisationPlayer(Player player) {
		String playername = player.getName();
		final connection connection2 = API.getdbDbmanageur().getConnection();
		try {
			final Connection connection = connection2.getConnection();
			String query = "SELECT UUID, Money, Rank, Civilisation, Permissions FROM Player WHERE UUID=?";
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			preparedStatement.setString(1, playername.toString());
			final ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				
				return resultSet.getString("Civilisation");
				
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
	public Float getCivilisationPlayerUUID(String playerUUID) {
		final connection connection2 = API.getdbDbmanageur().getConnection();
		try {
			final Connection connection = connection2.getConnection();
			String query = "SELECT UUID, Money, Rank, Civilisation, Permissions FROM Player WHERE UUID=?";
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			preparedStatement.setString(1, playerUUID);
			final ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				
				return resultSet.getFloat("Civilisation");
				
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
