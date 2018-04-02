package nl.skelic.as.utils;

import org.bukkit.ChatColor;

import nl.skelic.as.Core;

public enum MsgUtil {
	
	NOPERM(Core.prefix + ChatColor.RED + "Sorry you don't have permissions to preform that command!"),
	NOTPLR(Core.prefix + ChatColor.RED + "This command can't be used in the console!"),
	CMDNF(Core.prefix + ChatColor.RED + "Command not found!");
	
	private String message;
	
	MsgUtil(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String colorMessage(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
}