package nl.skelic.as.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.md_5.bungee.api.ChatColor;
import nl.skelic.as.Core;

public class Configs {
	
	private File configFile;
	private FileConfiguration config;
	
	private	File langFile;
	private FileConfiguration lang;
	
	private	File langNLFile;
	private FileConfiguration langNL;
	
	private	File attractiesFile;
	private FileConfiguration attracties;
	
	private	File zonesFile;
	private FileConfiguration zones;
	
	private static Configs configManager;
	
	public Configs(Core plugin) {
		configManager = this;
		configFile = new File(plugin.getDataFolder(), "config.yml");
		config = YamlConfiguration.loadConfiguration(configFile);
		langFile = new File(plugin.getDataFolder(), "lang.yml");
		lang = YamlConfiguration.loadConfiguration(langFile);
		langNLFile = new File(plugin.getDataFolder(), "langNL.yml");
		langNL = YamlConfiguration.loadConfiguration(langNLFile);
		attractiesFile = new File(plugin.getDataFolder(), "attracties.yml");
		attracties = YamlConfiguration.loadConfiguration(attractiesFile);
		zonesFile = new File(plugin.getDataFolder(), "zones.yml");
		zones = YamlConfiguration.loadConfiguration(zonesFile);
	}
	
	public void save() {
		try {
			config.save(configFile);
			lang.save(langFile);
			langNL.save(langNLFile);
			//attracties.save(attractiesFile);
			//zones.save(zonesFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String color(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
	
	public File getConfigFile() {
		return configFile;
	}
	
	public FileConfiguration getConfig() {
		return config;
	}
	
	public File getLangNLFile() {
		return langNLFile;
	}
	
	public FileConfiguration getLangNL() {
		return langNL;
	}
	
	public File getLangFile() {
		return langFile;
	}
	
	public FileConfiguration getLang() {
		return lang;
	}
	
	public File getAttractiesFile() {
		return attractiesFile;
	}
	
	public FileConfiguration getAttracties() {
		return attracties;
	}
	
	public File getZonesFile() {
		return zonesFile;
	}
	
	public FileConfiguration getZones() {
		return zones;
	}
	
	public static Configs getConfigs() {
		return configManager;
	}
	
}
