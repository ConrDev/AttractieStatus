package nl.skelic.as.utils;

import org.bukkit.ChatColor;

import nl.skelic.as.Core;
import nl.skelic.as.config.LangConfig;

public enum MsgUtil {
	NOPERM(Core.prefix + LangConfig.NOPERM.toString()),
	NOTPLR(Core.prefix + LangConfig.NOTPLR.toString()),
	CMDNF(Core.prefix + LangConfig.CMDNF.toString());
	
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