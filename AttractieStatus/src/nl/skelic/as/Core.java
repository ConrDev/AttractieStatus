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
	
	public static final String prefix = (ChatColor.AQUA + "[GHG] " + ChatColor.RESET);
	
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
		Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.DARK_BLUE + "------{AttractieStatus Plugin}-----");
		Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.DARK_BLUE + "|" + ChatColor.BLUE + "       Created by: SkelicStylz   " + ChatColor.DARK_BLUE + "|");
		Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.DARK_BLUE + "|" + ChatColor.BLUE + "           Version: v" + getDescription().getVersion() + "         " + ChatColor.DARK_BLUE + "|");
		Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.DARK_BLUE + "|" + ChatColor.BLUE + "      Plugin Status:  Enabled    " + ChatColor.DARK_BLUE + "|");
		Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.DARK_BLUE + "-----------------------------------");
		//loadConfiguration();
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.DARK_BLUE + "------{AttractieStatus Plugin}-----");
		Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.DARK_BLUE + "|" + ChatColor.BLUE + "       Created by: SkelicStylz   " + ChatColor.DARK_BLUE + "|");
		Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.DARK_BLUE + "|" + ChatColor.BLUE + "           Version: v" + getDescription().getVersion() + "         " + ChatColor.DARK_BLUE + "|");
		Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.DARK_BLUE + "|" + ChatColor.BLUE + "      Plugin Status: Disabled    " + ChatColor.DARK_BLUE + "|");
		Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.DARK_BLUE + "-----------------------------------");
	}
	
	public static Core getInstance() {
		return plugin;
	}

	public Util getUtil() {
		return util;
	}
}
