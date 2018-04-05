package nl.skelic.as;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import nl.skelic.as.commands.AttractieStatusCMD;
import nl.skelic.as.commands.AttractiesCMD;
import nl.skelic.as.commands.ZonesCMD;
import nl.skelic.as.config.LangConfig;
import nl.skelic.as.config.Config;
import nl.skelic.as.utils.Util;

public class Core extends JavaPlugin {
	
	private static Core plugin;
	private Util util;
	
	public static YamlConfiguration Lang;
	public static File Lang_File;
	public static final String prefix = (Config.PREFIX.toString() + "" + ChatColor.RESET);
	
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
		getCommand("zones").setExecutor(new ZonesCMD(this));
		
		//loading lang.yml
		/*if (Config.== "nl_NL") {*/
			loadLangNL();
		/*} else if (Config.LANG.toString() == "en_EN") {*/
			//loadLangEN();
		/*}*/
		
		//loading config
		loadConfig();
		
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
	
	public void loadLangNL() {
		File lang = new File(getDataFolder() + File.separator + "Language" + File.separator, "nl_NL.yml");
		if (!lang.exists()) {
			try {
				getDataFolder().mkdir();
				lang.createNewFile();
	            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(lang);
	            defConfig.save(lang);
	            LangConfig.setFile(defConfig);
			} catch (IOException e) {
				e.printStackTrace();
				//Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "Kon geen language bestand aan maken.");
				//Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "Dit is een fatale fout. De Plugin is nu aan het uitschakelen!");
				Log.error("[AS] Kon geen language bestand aan maken.");
				Log.error("[AS] Dit is een fatale fout. De Plugin is nu aan het uitschakelen!");
			this.setEnabled(false);
			}
		}
				
		YamlConfiguration conf = YamlConfiguration.loadConfiguration(lang);
		for (LangConfig item : LangConfig.values()) {
			if (conf.getString(item.getPath()) == null) {
				conf.set(item.getPath(), item.getDefault());
			}
		}
		
		LangConfig.setFile(conf);
		plugin.Lang = conf;
		plugin.Lang_File = lang;
		try {
			conf.save(getLangFile());
		} catch (IOException e) {
			Log.warn("[AS] Kon niet lang.yml opslaan.");
			Log.warn("[AS] Rapporteer deze fout aan SkelicStylz(coolboys100).");
			e.printStackTrace();
		}
		
	}
	
	public void loadConfig() {
		File config = new File(getDataFolder(), "config.yml");
		if (!config.exists()) {
			try {
				getDataFolder().mkdir();
				config.createNewFile();
	            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(config);
	            defConfig.save(config);
	            Config.setFile(defConfig);
			} catch (IOException e) {
				e.printStackTrace();
				//Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "Kon geen language bestand aan maken.");
				//Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "Dit is een fatale fout. De Plugin is nu aan het uitschakelen!");
				Log.error("[AS] Kon geen language bestand aan maken.");
				Log.error("[AS] Dit is een fatale fout. De Plugin is nu aan het uitschakelen!");
			this.setEnabled(false);
			}
		}
				
		YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);
		for (Config item : Config.values()) {
			if (conf.getString(item.getPath()) == null) {
				conf.set(item.getPath(), item.getDefault());
			}
		}
		
		LangConfig.setFile(conf);
		plugin.Lang = conf;
		plugin.Lang_File = config;
		try {
			conf.save(getLangFile());
		} catch (IOException e) {
			Log.warn("[AS] Kon niet lang.yml opslaan.");
			Log.warn("[AS] Rapporteer deze fout aan SkelicStylz(coolboys100).");
			e.printStackTrace();
		}
		
	}
	
	/*public void loadLangEN() {
		File lang = new File(getDataFolder() + File.separator + "Language" + File.separator, "en_EN.yml");
		if (!lang.exists()) {
			try {
				getDataFolder().mkdir();
				lang.createNewFile();
	            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(lang);
	            //LangConfig.NOPERM.toString().replaceAll("no-permission", "&cSorry, you don't have permission to use this command!");
	            //LangConfig.NOTPLR.toString().replaceAll("player-only", "&cThis command can not be used in the Console!");
	            //LangConfig.CMDNF.toString().replaceAll("command-not-found", "&cCommando not found!");
	            defConfig.save(lang);
	            LangConfig.setFile(defConfig);
			} catch (IOException e) {
				e.printStackTrace();
				//Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "Kon geen language bestand aan maken.");
				//Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "Dit is een fatale fout. De Plugin is nu aan het uitschakelen!");
				Log.error("[AS] Couldn't create language file.");
				Log.error("[AS] This is a fatal error. The plugin is now disabling!");
			this.setEnabled(false);
			}
		}
				
		YamlConfiguration conf = YamlConfiguration.loadConfiguration(lang);
		for (LangConfig item : LangConfig.values()) {
			if (conf.getString(item.getPath()) == null) {
				conf.set(item.getPath(), item.getDefault());
			}
		}
		
		LangConfig.setFile(conf);
		plugin.Lang = conf;
		plugin.Lang_File = lang;
		try {
			conf.save(getLangFile());
		} catch (IOException e) {
			Log.warn("[AS] Failed to save lang.yml.");
			Log.warn("[AS] Report this error to SkelicStylz(coolboys100).");
			e.printStackTrace();
		}
		
	}*/
	
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
