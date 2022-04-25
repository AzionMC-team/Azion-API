package fr.azion.mathis_bruel.api.api.data;

import fr.azion.mathis_bruel.api.DataBase.connection;
import fr.azion.mathis_bruel.api.API;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class Rank {
    public boolean isRank(String rank){
        final connection connection2 = API.getdbDbmanageur().getConnection();
        try {
            final Connection connection = connection2.getConnection();
            String query = "SELECT Name, Permissions, Prefix, Suffix, ChatColor, ColorName FROM Ranks WHERE Name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, rank);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                resultSet.close();
                connection.close();
                preparedStatement.close();
                return true;
            }else {
                resultSet.close();
                connection.close();
                preparedStatement.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public HashMap<String, String> getRank(String rankName){
        HashMap<String, String> rank = new HashMap<>();

        final connection connection2 = API.getdbDbmanageur().getConnection();
        try {
            final Connection connection = connection2.getConnection();
            String query = "SELECT Name, Permissions, Prefix, Suffix, ChatColor, ColorName FROM Ranks WHERE Name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, rankName);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                System.out.println(resultSet.getString("Prefix"));
                rank.put("Name", resultSet.getString("Name").replace("&", "§"));
                rank.put("Permissions", resultSet.getString("Permissions").replace("&", "§"));
                rank.put("Prefix", resultSet.getString("Prefix").replace("&", "§"));
                rank.put("Suffix", resultSet.getString("Suffix").replace("&", "§"));
                rank.put("ChatColor", resultSet.getString("ChatColor").replace("&", "§"));
                rank.put("ColorName", resultSet.getString("ColorName").replace("&", "§"));
                resultSet.close();
                connection.close();
                preparedStatement.close();
            }else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rank;
    }
    public boolean hasPermission(String rankName, String permission){

        final connection connection2 = API.getdbDbmanageur().getConnection();
        try {
            final Connection connection = connection2.getConnection();
            String query = "SELECT Name, Permissions, Prefix, Suffix, ChatColor, ColorName FROM Ranks WHERE Name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, rankName);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                ArrayList<String> perms = new ArrayList<>(Arrays.asList(resultSet.getString("Permissions").split(",")));
                resultSet.close();
                connection.close();
                preparedStatement.close();
                if(perms.contains(permission)){
                    return true;
                }else return false;
            }else {
                resultSet.close();
                connection.close();
                preparedStatement.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<String> getPermission(String rankName){
        final connection connection2 = API.getdbDbmanageur().getConnection();
        try {
            final Connection connection = connection2.getConnection();
            String query = "SELECT Name, Permissions, Prefix, Suffix, ChatColor, ColorName FROM Ranks WHERE Name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, rankName);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                ArrayList<String> perms = new ArrayList<>(Arrays.asList(resultSet.getString("Permissions").split(",")));

                resultSet.close();
                connection.close();
                preparedStatement.close();
                return perms;
            }else {
                resultSet.close();
                connection.close();
                preparedStatement.close();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean create(String rankName){
        final connection connection2 = API.getdbDbmanageur().getConnection();
        try {
            final Connection connection = connection2.getConnection();
            String query = "SELECT Name, Permissions, Prefix, Suffix, ChatColor, ColorName FROM Ranks WHERE Name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, rankName);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                resultSet.close();
                connection.close();
                preparedStatement.close();
                return false;
            }else {
                PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO `Ranks`(`Name`, `Permissions`, `Prefix`, `Suffix`, `ChatColor`, `ColorName`) VALUES (?,?,?,?,?,?)");
                preparedStatement2.setString(1, rankName);
                preparedStatement2.setString(2, "");
                preparedStatement2.setString(3, "");
                preparedStatement2.setString(4, "");
                preparedStatement2.setString(5, "");
                preparedStatement2.setString(6, "");
                preparedStatement2.executeUpdate();
                preparedStatement2.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean delete(String rankName){
        final connection connection2 = API.getdbDbmanageur().getConnection();
        try {
            final Connection connection = connection2.getConnection();
            String query = "SELECT Name, Permissions, Prefix, Suffix, ChatColor, ColorName FROM Ranks WHERE Name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, rankName);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                PreparedStatement preparedStatement2 = connection.prepareStatement("DELETE FROM `Ranks` WHERE Name=?");
                preparedStatement2.setString(1, rankName);
                preparedStatement2.executeUpdate();
                preparedStatement2.close();
                return true;
            }else {
                resultSet.close();
                connection.close();
                preparedStatement.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean setName(String rankName, String newName){
        try {
            this.update(rankName, "Name", newName);
            return true;
        } catch (Exception e) {}
        return false;
    }
    public boolean addPermission(String rankName, String Permssion){
        final connection connection2 = API.getdbDbmanageur().getConnection();
        try {
            final Connection connection = connection2.getConnection();
            String query = "SELECT Name, Permissions, Prefix, Suffix, ChatColor, ColorName FROM Ranks WHERE Name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, rankName);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                ArrayList<String> perms = new ArrayList<>(Arrays.asList(resultSet.getString("Permissions").split(",")));
                perms.add(Permssion);
                AtomicReference<String> permsStr = new AtomicReference<>("");
                perms.forEach(perm -> {
                    if(permsStr.get() == ""){
                        permsStr.set(perm);
                    }else{
                        permsStr.set(permsStr + ", " + perm);
                    }
                });
                try {
                    this.update(rankName, "Permissions", permsStr.get());
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                resultSet.close();
                connection.close();
                preparedStatement.close();
            }else {
                resultSet.close();
                connection.close();
                preparedStatement.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean setPrefix(String rankName, String newPrefix){
        try {
            this.update(rankName, "Prefix", newPrefix);
            return true;
        } catch (Exception e) {}
        return false;
    }
    public boolean setSuffix(String rankName, String newSuffix){
        try {
            this.update(rankName, "Suffix", newSuffix);
            return true;
        } catch (Exception e) {}
        return false;
    }
    public boolean setChatColor(String rankName, String newChatColor){
        try {
            this.update(rankName, "ChatColor", newChatColor);
            return true;
        } catch (Exception e) {}
        return false;
    }
    public boolean setColorName(String rankName, String newColorName){
        try {
            this.update(rankName, "ColorName", newColorName);
            return true;
        } catch (Exception e) {}
        return false;
    }

    public void update(String rankName, String whoUpdate, String newUpdate) throws Exception {
        final connection connection2 = API.getdbDbmanageur().getConnection();
        try {
            final Connection connection = connection2.getConnection();
            String query = "SELECT Name, Permissions, Prefix, Suffix, ChatColor, ColorName FROM Ranks WHERE Name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, rankName);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                PreparedStatement preparedStatement2 = connection.prepareStatement("UPDATE `Ranks` SET `?`=? WHERE Name=?");
                preparedStatement2.setString(1, whoUpdate);
                preparedStatement2.setString(2, newUpdate);
                preparedStatement2.setString(3, rankName);
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
