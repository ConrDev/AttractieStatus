package nl.skelic.as.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import nl.skelic.as.Core;
import nl.skelic.as.config.Configs;

public class Util {
	
	private Core plugin;
	
	public static final String prefix = (Configs.getConfigs().color(Configs.getConfigs().getConfig().get("prefix") + " ") + ChatColor.RESET);
	
	public Util(Core pluginInstance) {
		plugin = pluginInstance;
	}
	
	public void saveYML(File file, YamlConfiguration config) {
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveFile(FileConfiguration config, File file) {
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}