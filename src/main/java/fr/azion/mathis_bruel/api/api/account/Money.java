package fr.azion.mathis_bruel.api.api.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.azion.mathis_bruel.api.DataBase.connection;
import fr.azion.mathis_bruel.api.API;
import org.bukkit.entity.Player;


public class Money {
	
	API main;
	public Money(API main) {
		this.main = main;
	}
	

	public void setMoneyPlayer(Player player,float money) {
		String playerUUID = player.getUniqueId().toString();
		final connection connection2 = API.getdbDbmanageur().getConnection();
		try {
			final Connection connection = connection2.getConnection();
			String query = "SELECT UUID, Money, Rank, Civilisation, Permissions FROM Player WHERE UUID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, playerUUID);
			final ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				PreparedStatement preparedStatement2 = connection.prepareStatement("UPDATE `Player` SET `Money`=? WHERE UUID=?");
				preparedStatement2.setFloat(1, money);
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
	public void setMoneyPlayerUUID(String playerUUID, float money) {
		final connection connection2 = API.getdbDbmanageur().getConnection();
		try {
			final Connection connection = connection2.getConnection();
			String query = "SELECT UUID, Money, Rank, Civilisation, Permissions FROM Player WHERE UUID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, playerUUID);
			final ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				PreparedStatement preparedStatement2 = connection.prepareStatement("UPDATE `Player` SET `Money`=? WHERE UUID=?");
				preparedStatement2.setFloat(1, money);
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
	
	public Float getMoneyPlayer(Player player) {
		String playerUUID = player.getUniqueId().toString();
		final connection connection2 = API.getdbDbmanageur().getConnection();
		try {
			final Connection connection = connection2.getConnection();
			String query = "SELECT UUID, Money, Rank, Civilisation, Permissions FROM Player WHERE UUID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, playerUUID);
			final ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				
				return resultSet.getFloat("Money");
				
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
	public Float getMoneyPlayerUUID(String playerUUID) {
		final connection connection2 = API.getdbDbmanageur().getConnection();
		try {
			final Connection connection = connection2.getConnection();
			String query = "SELECT UUID, Money, Rank, Civilisation, Permissions FROM Player WHERE UUID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, playerUUID);
			final ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				
				return resultSet.getFloat("Money");
				
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
	
	public void addMoneyPlayer(Player player, Float money) {
		String playername = player.getName();
		final connection connection2 = API.getdbDbmanageur().getConnection();
		try {
			final Connection connection = connection2.getConnection();
			String query = "SELECT UUID, Money, Rank, Civilisation, Permissions FROM Player WHERE UUID=?";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, playername.toString());
			final ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				PreparedStatement preparedStatement2 = connection.prepareStatement("UPDATE `Player` SET `Money`=? WHERE UUID=?");
				preparedStatement2.setFloat(1, (resultSet.getFloat("Money")+money));
				preparedStatement2.setString(2, playername);
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
	
	public void addMoneyPlayerName(String playername, Float money) {
		final connection connection2 = API.getdbDbmanageur().getConnection();
		try {
			final Connection connection = connection2.getConnection();
			String query = "SELECT UUID, Money, Rank, Civilisation, Permissions, Permissions FROM Player WHERE UUID=?";
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			preparedStatement.setString(1, playername.toString());
			final ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				PreparedStatement preparedStatement2 = (PreparedStatement) connection.prepareStatement("UPDATE `Player` SET `Money`=? WHERE UUID=?");
				preparedStatement2.setFloat(1, (resultSet.getFloat("Money")+money));
				preparedStatement2.setString(2, playername);
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
}
