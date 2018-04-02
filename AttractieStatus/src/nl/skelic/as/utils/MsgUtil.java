package nl.skelic.as.utils;

import org.bukkit.ChatColor;

import nl.skelic.as.Core;

public enum MsgUtil {
	
	NOPERM(Core.prefix + ChatColor.RED + "Sorry u hebt geen permissie om dit commando uit te voeren!"),
	NOTPLR(Core.prefix + ChatColor.RED + "Dit commando kan niet gebruikt worden in de Console!"),
	CMDNF(Core.prefix + ChatColor.RED + "Commando niet gevonden!");
	
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