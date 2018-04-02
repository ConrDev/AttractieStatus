package nl.skelic.as.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.skelic.as.Core;
import nl.skelic.as.menus.Menus;
import nl.skelic.as.utils.MsgUtil;

public class AttractiesCMD implements CommandExecutor {
	
	private Core plugin;
	
	public AttractiesCMD(Core plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(MsgUtil.NOTPLR.getMessage());
			return true;
		}
		
		Player player = (Player) sender;
		if (command.getName().equalsIgnoreCase("attracties")) {
				if (sender.hasPermission("attractiestatus.attracties")) {
					Menus.zonesMenu(player);
					return true;
			}
		}
	return true;
	}
}
