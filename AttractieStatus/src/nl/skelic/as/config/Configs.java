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
	
	private	File langNLFile;
	private FileConfiguration langNL;
	
	private	File langENFile;
	private FileConfiguration langEN;
	
	private	File attractiesFile;
	private FileConfiguration attracties;
	
	private	File zonesFile;
	private FileConfiguration zones;
	
	private static Configs configManager;
	
	public Configs(Core plugin) {
		configManager = this;
		configFile = new File(plugin.getDataFolder(), "config.yml");
		config = YamlConfiguration.loadConfiguration(configFile);
		langNLFile = new File(plugin.getDataFolder() + File.separator + "Languages", "nl_NL.yml");
		langNL = YamlConfiguration.loadConfiguration(configFile);
		langENFile = new File(plugin.getDataFolder() + File.separator + "Languages", "en_US.yml");
		langEN = YamlConfiguration.loadConfiguration(configFile);
		attractiesFile = new File(plugin.getDataFolder(), "attracties.yml");
		attracties = YamlConfiguration.loadConfiguration(attractiesFile);
		zonesFile = new File(plugin.getDataFolder(), "zones.yml");
		zones = YamlConfiguration.loadConfiguration(zonesFile);
	}
	
	public void save() {
		try {
			config.save(configFile);
			langNL.save(langNLFile);
			langEN.save(langENFile);
			attracties.save(attractiesFile);
			zones.save(zonesFile);
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
	
	public File getLangENFile() {
		return langENFile;
	}
	
	public FileConfiguration getLangEN() {
		return langEN;
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
