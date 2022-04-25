package fr.azion.mathis_bruel.api.api.account;

import fr.azion.mathis_bruel.api.API;
import fr.azion.mathis_bruel.api.DataBase.connection;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.help.HelpTopic;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class Permissions {
    public ArrayList<String> getPermissionsPlayer(Player player){
        ArrayList<String> perms = new ArrayList<>();
        String playerUUID = player.getUniqueId().toString();
        final connection connection2 = API.getdbDbmanageur().getConnection();
        try {
            final Connection connection = connection2.getConnection();
            String query = "SELECT UUID, Money, Rank, Civilisation, Permissions FROM Player WHERE UUID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, playerUUID);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                String str[] = resultSet.getString("Permissions").split(",");
                if(resultSet.getString("Permissions") != ""){
                    System.out.println("str -> "+resultSet.getString("Permissions"));
                    for(String perm : str){
                        perms.add(perm);
                    }
                }
                System.out.println(API.getApi.dataRank.getPermission(API.getApi.accountRank.getRankPlayer(player)));
                API.getApi.dataRank.getPermission(API.getApi.accountRank.getRankPlayer(player)).forEach(perm -> {
                    perms.add(perm);
                });
            }else {
            }
            resultSet.close();
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return perms;
    }
    public boolean addPermissionPlayer(Player player, String perm){
        ArrayList<String> perms = null;
        String playerUUID = player.getUniqueId().toString();
        final connection connection2 = API.getdbDbmanageur().getConnection();
        try {
            final Connection connection = connection2.getConnection();
            String query = "SELECT UUID, Money, Rank, Civilisation, Permissions FROM Player WHERE UUID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, playerUUID);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                perms = new ArrayList<>(Arrays.asList(resultSet.getString("Permissions").split(",")));
                perms.add(perm);
                AtomicReference<String> str = new AtomicReference<>("");
                perms.forEach(permTemp -> {
                   if(str.get() == ""){
                       str.set(permTemp);
                   }else str.set(str + ", " + permTemp);
                });
                this.setPermissionsPlayer(player, str.toString());
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
    public boolean setPermissionsPlayer(Player player, String perms) {
        String playerUUID = player.getUniqueId().toString();
        final connection connection2 = API.getdbDbmanageur().getConnection();
        try {
            final Connection connection = connection2.getConnection();
            String query = "SELECT UUID, Money, Rank, Civilisation, Permissions FROM Player WHERE UUID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, playerUUID);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                PreparedStatement preparedStatement2 = connection.prepareStatement("UPDATE `Player` SET `Permissions`=? WHERE UUID=?");
                preparedStatement2.setString(1, perms);
                preparedStatement2.setString(2, playerUUID);
                preparedStatement2.executeUpdate();
                preparedStatement2.close();
                resultSet.close();
                connection.close();
                preparedStatement.close();
                API.getApi.attachment.remove(player.getUniqueId());
                setupPermissions(player);
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

    public void setupPermissions(Player player) {
        PermissionAttachment attachment = player.addAttachment(API.getApi);
        API.getApi.attachment.put(player.getUniqueId(), attachment);
        permissionsSetter(player.getUniqueId(), player);
    }

    private void permissionsSetter(UUID uuid, Player player) {
        PermissionAttachment attachment = API.getApi.attachment.get(uuid);
        if(API.getApi.accountPermissions.getPermissionsPlayer(player).contains("*")){
            for(Permission perm : getPerms()){
                attachment.setPermission(perm.getName(), true);
            }
            for (HelpTopic cmd : Bukkit.getServer().getHelpMap().getHelpTopics()) {
                attachment.setPermission("minecraft.command." + cmd.getName().replace("/", ""), true);
            }
            return;
        }
        API.getApi.accountPermissions.getPermissionsPlayer(player).forEach(perm -> {
            attachment.setPermission(perm, true);
        });
    }
    public List<Permission> getPerms() {
        List<Permission> perms = new ArrayList<>();
        for (Permission perm : Bukkit.getPluginManager().getPermissions()) {
            if (!perms.contains(perm)) {
                perms.add(perm);
            }
        }
        for(Plugin pl : Bukkit.getPluginManager().getPlugins()){
            pl.getConfig();
        }

        return perms;
    }
}
