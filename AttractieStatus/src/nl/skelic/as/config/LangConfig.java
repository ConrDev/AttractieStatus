package nl.skelic.as.config;

import org.bukkit.configuration.file.YamlConfiguration;

import net.md_5.bungee.api.ChatColor;

public enum LangConfig {
	//PREFIX("prefix", "&6[AS] "),
	NOPERM("no-permissions", "&cSorry, u hebt geen permissie om dit commando uit te voeren!"),
	NOTPLR("player-only", "&cDit commando kan niet gebruikt worden in de Console!"),
	CMDNF("command-not-found", "&cCommando niet gevonden!");
	
	private String path;
	private String def;
	private static YamlConfiguration lang;
	
	LangConfig(String path, String start) {
		this.path = path;
		this.def = start;
	}
	
	public static void setFile(YamlConfiguration config) {
		lang = config;
	}
	//NOPERM("no-permissions", "&cSorry you don't have permission to use this command!"),
	//NOTPLR("player-only", "&cThis command can not be used in the Console!"),
	//CMDNF("command-not-found", "&cCommand not found!");
	
	@Override
	public String toString() {
		if (/*this == PREFIX ||*/ this == NOPERM || this == NOTPLR || this == CMDNF) 
			return ChatColor.translateAlternateColorCodes('&', lang.getString(this.path, def)) + ChatColor.RESET;
		return ChatColor.translateAlternateColorCodes('&', lang.getString(this.path, def));
	}
	
	public String getDefault() {
		return this.def;
	}
	
	public String getPath() {
		return this.path;
	}
}
