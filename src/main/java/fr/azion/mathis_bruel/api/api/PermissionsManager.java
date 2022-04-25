package fr.azion.mathis_bruel.api.api;

import fr.azion.mathis_bruel.api.API;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.help.HelpTopic;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PermissionsManager {


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
                System.out.println(cmd.getName());
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
