package nl.skelic.as.utils;

import org.bukkit.ChatColor;

import nl.skelic.as.config.Configs;

public enum MsgUtil {
	NOPERM(Util.prefix + Configs.getConfigs().color(Configs.getConfigs().getLang().getString("errors.no-permissions"))),
	NOTPLR(Util.prefix + Configs.getConfigs().color(Configs.getConfigs().getLang().getString("errors.player-only"))),
	CMDNF(Util.prefix + Configs.getConfigs().color(Configs.getConfigs().getLang().getString("errors.command-not-found")));
	
	private static MsgUtil msgU;
	
	private String message;
	
	MsgUtil(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String color(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
	
	public static MsgUtil MsgU() {
		return msgU;
	}
}