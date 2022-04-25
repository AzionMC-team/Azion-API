package fr.azion.mathis_bruel.api;

import fr.azion.mathis_bruel.api.Commands.Test;
import fr.azion.mathis_bruel.api.DataBase.DbCredentials;
import fr.azion.mathis_bruel.api.DataBase.connection;
import fr.azion.mathis_bruel.api.DataBase.dbmanageur;
import fr.azion.mathis_bruel.api.api.PermissionsManager;
import fr.azion.mathis_bruel.api.api.account.Civilisation;
import fr.azion.mathis_bruel.api.api.account.Money;
import fr.azion.mathis_bruel.api.api.account.Permissions;
import fr.azion.mathis_bruel.api.api.account.Rank;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class API extends JavaPlugin{
	private static dbmanageur dbmanageur;
	public DbCredentials DbCredentials;
	public connection connection;
	public Rank accountRank;
	public fr.azion.mathis_bruel.api.api.data.Rank dataRank;
	public Money accountMoney;
	public Civilisation accountCivilisation;
	public Permissions accountPermissions;
	public PermissionsManager permissionsManager;
	public static API getApi;
	public HashMap<UUID, PermissionAttachment> attachment = new HashMap<>();
	@Override
	public void onEnable() {
		getApi = this;
		dbmanageur = new dbmanageur();
		accountRank = new Rank(this);
		dataRank = new fr.azion.mathis_bruel.api.api.data.Rank();
		accountMoney = new Money(this);
		accountCivilisation = new Civilisation(this);
		accountPermissions = new Permissions();
		permissionsManager = new PermissionsManager();
		System.out.println("[AzionAPI] L'api viens de ce lancer.");
		
		
		getServer().getPluginManager().registerEvents(new EventListener(this), this);
		getCommand("test").setExecutor(new Test(this));
		
	}
	
	@Override
	public void onDisable() {
		System.out.println("[AzionAPI] L'api viens de s'eteindre.");
	}
	public static dbmanageur getdbDbmanageur() {
		return dbmanageur;
	}

	public Rank accountRank(){
		return accountRank;
	}
	public fr.azion.mathis_bruel.api.api.data.Rank dataRank() { return dataRank; }
	public Money accountMoney() { return accountMoney; }
	public Civilisation accountCivilisation() { return accountCivilisation; }
	public Permissions accountPermissions() { return accountPermissions; }
	public PermissionsManager permissionsManager() { return permissionsManager; }
}
