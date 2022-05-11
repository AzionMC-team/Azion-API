package fr.azion.sothis.api;

import fr.azion.sothis.api.database.DatabaseManager;
import fr.azion.sothis.api.event.Join;
import fr.azion.sothis.api.managers.GradeManager;
import fr.azion.sothis.api.managers.UserManager;
import fr.azion.sothis.api.tools.title.TitleBuilder;
import fr.azion.sothis.api.tools.title.TitleOptions;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class AzionAPI {

    private static Plugin plugin;
    private static String instanceName;

    private static AzionAPI instance;

    private static DatabaseManager databaseManager;
    private static UserManager userManager;
    private static GradeManager gradeManager;

    /**
     * Start the API (and the database connection)
     * Collect informations of plugin and the name of plugin
     * The class plugin help to register the events of the API
     * @param plug
     * @param name
     */
    public static void start(Plugin plug, String name) {
        plugin = plug;
        instanceName = name;

        databaseManager = new DatabaseManager();
        databaseManager.init();

        userManager = new UserManager(databaseManager);
        gradeManager = new GradeManager(databaseManager);

        registerEvent();
    }

    /**
     * Stop the API
     * Just close the database
     */
    public static void stop() {
        databaseManager.close();
    }

    /**
     * Just a function to stack the class ;)
     */
    private static void registerEvent() {
        PluginManager pm = plugin.getServer().getPluginManager();

        pm.registerEvents(new Join(databaseManager, userManager), plugin);
    }

    /**
     * Return the instance of the class to get access to other getters
     * @return
     */
    public static AzionAPI getInstance() {
        return instance;
    }

    /**
     * Get the manager to interact with users in database
     * @return
     */
    public UserManager getUserManager() {
        return userManager;
    }

    /**
     * Get the manager to interact with grades in database
     * @return
     */
    public GradeManager getGradeManager() {
        return gradeManager;
    }

    public void sendTitle(Player player, String title , ChatColor chatColorTitle, String subtitle, ChatColor chatColorSubTitle, TitleOptions options) {
        TitleBuilder.sendTitle(player,title,chatColorTitle,subtitle,chatColorSubTitle,options);
    }
}
