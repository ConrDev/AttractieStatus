package nl.skelic.as.utils;

import org.bukkit.ChatColor;

import nl.skelic.as.config.Configs;

public enum MsgUtil {
	NOPERM(Util.prefix + Configs.getConfigs().color(Configs.getConfigs().getLangNL().getString("no-permissions"))),
	NOTPLR(Util.prefix + Configs.getConfigs().color(Configs.getConfigs().getLangNL().getString("player-only"))),
	CMDNF(Util.prefix + Configs.getConfigs().color(Configs.getConfigs().getLangNL().getString("command-not-found")));
	
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