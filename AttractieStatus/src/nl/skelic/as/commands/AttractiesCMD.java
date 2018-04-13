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
			return false;
		} else {
		Player player = (Player) sender;
		if (args.length < 1) {
			if (sender.hasPermission("attractiestatus.attracties")) {
				Menus.attractiesMenu(player);
				return false;
			}
		} else {
			if (args[0].equalsIgnoreCase("edit")) {
				if (sender.hasPermission("attractiestatus.attracties.edit")) {
					Menus.attEditMenu(player);
					return false;
				}
			}
		}
	}
	return false;
	}
}
