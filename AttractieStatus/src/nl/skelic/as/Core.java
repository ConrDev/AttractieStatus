package nl.skelic.as;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import nl.skelic.as.commands.AttractieStatusCMD;
import nl.skelic.as.commands.AttractiesCMD;
import nl.skelic.as.utils.Util;

public class Core extends JavaPlugin {
	
	private static Core plugin;
	private Util util;
	
	public static final String prefix = (ChatColor.GOLD + "[AS] " + ChatColor.RESET);
	
	@Override
	public void onEnable() {
		//Loading Configs
		if(new File(getDataFolder(), "Attracties").mkdirs()) {
			getLogger().info("Generated Attracties folder!");
		}
		
		plugin = this;
		util = new Util(this);
		
		//Loading Events
		PluginManager pm = Bukkit.getServer().getPluginManager();
		//pm.registerEvents(towersData = new TowersData(this), this);
		
		//Loading Commands
		getCommand("attractiestatus").setExecutor(new AttractieStatusCMD(this));
		getCommand("attracties").setExecutor(new AttractiesCMD(this));
		
		//Finished Message
		Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GOLD + "------{AttractieStatus Plugin}-----");
		Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GOLD + "|" + ChatColor.YELLOW + "       Created by: SkelicStylz   " + ChatColor.GOLD + "|");
		Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GOLD + "|" + ChatColor.YELLOW + "           Version: v" + getDescription().getVersion() + "         " + ChatColor.GOLD + "|");
		Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GOLD + "|" + ChatColor.YELLOW + "      Plugin Status:  Enabled    " + ChatColor.GOLD + "|");
		Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GOLD + "-----------------------------------");
		//loadConfiguration();
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GOLD + "------{AttractieStatus Plugin}-----");
		Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GOLD + "|" + ChatColor.YELLOW + "       Created by: SkelicStylz   " + ChatColor.GOLD + "|");
		Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GOLD + "|" + ChatColor.YELLOW + "           Version: v" + getDescription().getVersion() + "         " + ChatColor.GOLD + "|");
		Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GOLD + "|" + ChatColor.YELLOW + "      Plugin Status: Disabled    " + ChatColor.GOLD + "|");
		Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GOLD + "-----------------------------------");
	}
	
	public static Core getInstance() {
		return plugin;
	}

	public Util getUtil() {
		return util;
	}
}
