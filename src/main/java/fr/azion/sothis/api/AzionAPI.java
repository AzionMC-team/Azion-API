package fr.azion.sothis.api;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import fr.azion.sothis.api.database.DatabaseManager;
import fr.azion.sothis.api.event.Join;
import fr.azion.sothis.api.listeners.AzionListener;
import fr.azion.sothis.api.listeners.ListenerManager;
import fr.azion.sothis.api.managers.GradeManager;
import fr.azion.sothis.api.managers.ObjectManager;
import fr.azion.sothis.api.managers.ReportManager;
import fr.azion.sothis.api.managers.UserManager;
import fr.azion.sothis.api.pojo.User;
import fr.azion.sothis.api.tools.title.TitleBuilder;
import fr.azion.sothis.api.tools.title.TitleOptions;
import jdk.internal.dynalink.MonomorphicCallSite;
import org.bson.Document;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class AzionAPI {

    private static Plugin plugin;
    private static String instanceName;

    private static AzionAPI instance;

    private static DatabaseManager databaseManager;
    private static ListenerManager listenerManager;
    private static UserManager userManager;
    private static GradeManager gradeManager;
    private static ReportManager reportManager;
    private static ObjectManager objectManager;

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

        listenerManager = new ListenerManager();

        userManager = new UserManager(databaseManager);
        gradeManager = new GradeManager(databaseManager);
        reportManager = new ReportManager(databaseManager, listenerManager);

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

    public void registerEvent(AzionListener clazz) {
        listenerManager.addListener(clazz);
    }

    public <T> MongoCollection<T> createCollection(String name, Class T) {
        MongoIterable<String> listCol = databaseManager.getMongoDatabase().listCollectionNames();
        int nmb = 0;
        while (listCol.iterator().hasNext()) {
            if(listCol.iterator().next().equals("name")) {
                nmb++;
            }
        }
        if(nmb == 0) {
            databaseManager.getMongoDatabase().createCollection(name);
        }
        try {
            MongoCollection<T> collection = databaseManager.getMongoDatabase().getCollection(name, T);
            databaseManager.getCollections().put(name, collection);
            return collection;
        } catch (Exception e) {
            plugin.getLogger().severe("The object are not valid or the collection doesn't exist");
            e.printStackTrace();
            plugin.getPluginLoader().disablePlugin(plugin);
        }
        return null;
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

    public ReportManager getReportManager() {
        return reportManager;
    }

    public void sendTitle(Player player, String title , ChatColor chatColorTitle, String subtitle, ChatColor chatColorSubTitle, TitleOptions options) {
        TitleBuilder.sendTitle(player,title,chatColorTitle,subtitle,chatColorSubTitle,options);
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}
