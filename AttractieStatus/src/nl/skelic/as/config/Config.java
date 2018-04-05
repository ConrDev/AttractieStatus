package nl.skelic.as.config;

import org.bukkit.configuration.file.YamlConfiguration;

import net.md_5.bungee.api.ChatColor;

public enum Config {
	PREFIX("prefix", "&6[AS] "),
	LANG("Language", "nl_NL");
	
	private String path;
	private String def;
	private static YamlConfiguration config;
	
	Config(String path, String start) {
		this.path = path;
		this.def = start;
	}
	
	public static void setFile(YamlConfiguration config) {
		config = config;
	}
	
    @Override
    public String toString() {
        if (this == PREFIX)
            return ChatColor.translateAlternateColorCodes('&', config.getString(this.path, def)) + " ";
        return ChatColor.translateAlternateColorCodes('&', config.getString(this.path, def));
    }
	
	public String getDefault() {
		return this.def;
	}
	
	public String getPath() {
		return this.path;
	}
}
