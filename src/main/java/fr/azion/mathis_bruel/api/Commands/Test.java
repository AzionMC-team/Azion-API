package fr.azion.mathis_bruel.api.Commands;

import fr.azion.mathis_bruel.api.API;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Test implements CommandExecutor {

	API main;
	public Test(API main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			//main.rank.setRankPlayer(player, "Admin");
		}else System.out.println("Seul un joueur peut executer cette commande.");
		
		return false;
	}

}
