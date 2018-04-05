package nl.skelic.as.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.skelic.as.Core;
import nl.skelic.as.menus.Menus;
import nl.skelic.as.utils.MsgUtil;

public class ZonesCMD implements CommandExecutor {
	
	private Core plugin;
	
	public ZonesCMD(Core plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(MsgUtil.NOTPLR.getMessage());
			return false;
		}
		
		Player player = (Player) sender;
		if (sender.hasPermission("attractiestatus.attracties")) {
			Menus.zonesMenu(player);
			return false;
		}
	return false;
	}
}
