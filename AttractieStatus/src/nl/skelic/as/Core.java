package nl.skelic.as;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import nl.skelic.as.commands.AttractieStatusCMD;
import nl.skelic.as.commands.AttractiesCMD;
import nl.skelic.as.commands.ZonesCMD;
import nl.skelic.as.config.Configs;
import nl.skelic.as.config.Load;
import nl.skelic.as.events.SignEvent;
import nl.skelic.as.utils.Util;

public class Core extends JavaPlugin {
	
	private static Core plugin;
	private Util util;
	
	public static YamlConfiguration Lang;
	public static File Lang_File;
	//public static final String prefix = Util.prefix;
	
	@Override
	public void onEnable() {
		// Loading Settings
		PluginManager pm = Bukkit.getServer().getPluginManager();
		plugin = this;
		
		// Loading constructors
		new Configs(this);
		
		// Loading Configs
		Load.Config();
		Load.Lang();
		Load.LangNL();
		
		// Loadin Util
		util = new Util(this);
		
		// Loading Commands
		getCommand("attractiestatus").setExecutor(new AttractieStatusCMD(this));
		getCommand("attracties").setExecutor(new AttractiesCMD(this));
		getCommand("zones").setExecutor(new ZonesCMD(this));
		
		// Loading Events
		pm.registerEvents(new SignEvent(this), this);
		
		// Finished Message
		Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.DARK_GREEN + "------{AttractionStatus Plugin}-----");
		Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.DARK_GREEN + "|" + ChatColor.GREEN + "      Created by: SkelicStylz    " + ChatColor.DARK_GREEN + "|");
		Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.DARK_GREEN + "|" + ChatColor.GREEN + "           Version: v" + getDescription().getVersion() + "         " + ChatColor.DARK_GREEN + "|");
		Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.DARK_GREEN + "|" + ChatColor.GREEN + "      Plugin Status: Enabled     " + ChatColor.DARK_GREEN + "|");
		Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.DARK_GREEN + "-----------------------------------");
		
		// Loading Folders
		if (new File(getDataFolder(), "Attractions").mkdirs()) Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GREEN + "Generated Attractions folder!");
		if (new File(getDataFolder(), "Zones").mkdirs()) Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GREEN + "Generated Zones folder!");
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.DARK_GREEN + "------{AttractionStatus Plugin}-----");
		Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.DARK_GREEN + "|" + ChatColor.GREEN + "      Created by: SkelicStylz    " + ChatColor.DARK_GREEN + "|");
		Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.DARK_GREEN + "|" + ChatColor.GREEN + "           Version: v" + getDescription().getVersion() + "         " + ChatColor.DARK_GREEN + "|");
		Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.DARK_GREEN + "|" + ChatColor.GREEN + "      Plugin Status: Disabled    " + ChatColor.DARK_GREEN + "|");
		Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.DARK_GREEN + "-----------------------------------");
		//Configs.getConfigs().save();
	}
	
	public static Core getInstance() {
		return plugin;
	}

	public Util getUtil() {
		return util;
	}
	
	public YamlConfiguration getLang() {
		return Lang;
	}
	
	public File getLangFile() {
		return Lang_File;
	}
}
