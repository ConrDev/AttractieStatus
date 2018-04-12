package nl.skelic.as.config;

import java.io.File;

import org.bukkit.ChatColor;

import nl.skelic.as.Core;

public class Attracties {
	
	private static Attracties attractiesManager;
	private Core plugin;
	private Configs cfg;
	
	public Attracties(Core plugin) {
		this.plugin = plugin;
		attractiesManager = this;
		cfg = Configs.getConfigs();
	}
	
	public Attracties getAttracties() {
		return attractiesManager;
	}
	
	File aFolder = new File(plugin.getDataFolder() + File.separator + "Attracties" + File.separator);
	File[] aList = aFolder.listFiles();
	
	public void LoadAttracties() {
		if (cfg.getAttracties().contains(null)) return;
		for (int i = 0; i < aList.length; i++) {
			if (aList[i].isFile()) {
				//cfg.getAttracties().set(aList[i].getName().replace(".yml", ""), "");
				cfg.getAttracties().set(aList[i].getName().replace(".yml", "")+ ".Zone", aList[i].toPath());
			}
		}
	}
	
}
