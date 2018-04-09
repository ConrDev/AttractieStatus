package nl.skelic.as;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import nl.skelic.as.commands.AttractieStatusCMD;
import nl.skelic.as.commands.AttractiesCMD;
import nl.skelic.as.commands.ZonesCMD;
import nl.skelic.as.config.Configs;
import nl.skelic.as.utils.Util;

public class Core extends JavaPlugin {
	
	private static Core plugin;
	private Util util;
	
	public static YamlConfiguration Lang;
	public static File Lang_File;
	//public static final String prefix = (Util.prefix);
	
	@Override
	public void onEnable() {
		// Loading Folders
		if(new File(getDataFolder(), "Attracties").mkdirs()) { getLogger().info("Generated Attracties folder!"); }
		
		// Loading Settings
		PluginManager pm = Bukkit.getServer().getPluginManager();
		plugin = this;
		
		// Loading constructors
		new Configs(this);
		
		// Loading Configs
		loadConfig();
		
		// Loadin Util
		util = new Util(this);
		
		// Loading Commands
		getCommand("attractiestatus").setExecutor(new AttractieStatusCMD(this));
		getCommand("attracties").setExecutor(new AttractiesCMD(this));
		getCommand("zones").setExecutor(new ZonesCMD(this));
		
		// Finished Message
		Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + "------{AttractieStatus Plugin}-----");
		Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + "|" + ChatColor.YELLOW + "       Created by: SkelicStylz   " + ChatColor.GOLD + "|");
		Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + "|" + ChatColor.YELLOW + "           Version: v" + getDescription().getVersion() + "         " + ChatColor.GOLD + "|");
		Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + "|" + ChatColor.YELLOW + "      Plugin Status:  Enabled    " + ChatColor.GOLD + "|");
		Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + "-----------------------------------");
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + "------{AttractieStatus Plugin}-----");
		Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + "|" + ChatColor.YELLOW + "       Created by: SkelicStylz   " + ChatColor.GOLD + "|");
		Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + "|" + ChatColor.YELLOW + "           Version: v" + getDescription().getVersion() + "         " + ChatColor.GOLD + "|");
		Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + "|" + ChatColor.YELLOW + "      Plugin Status: Disabled    " + ChatColor.GOLD + "|");
		Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + "-----------------------------------");
		//Configs.getConfigs().save();
	}
	
	public void loadConfig() {
		FileConfiguration config = Configs.getConfigs().getConfig();
		config.addDefault("Prefix", "&6[AS]");
		config.addDefault("Language", "nl_NL");
		
		config.options().copyDefaults(true);
		
		//Languages list
		FileConfiguration langNL = Configs.getConfigs().getLangNL();
		langNL.addDefault("no-permissions", "&cSorry, u hebt geen permissie om dit commando uit te voeren!");
		langNL.addDefault("player-only", "&cDit commando kan niet gebruikt worden in de Console!");
		langNL.addDefault("command-not-found", "&cCommando niet gevonden!");
		langNL.addDefault("as-help", "laat u alle commandos zien van AttractieStatus");
		langNL.addDefault("as-add", "voeg een Attractie met de status toe aan de lijst");
		langNL.addDefault("as-remove", "verwijder een Attractie van de lijst");
		langNL.addDefault("as-setstatus", "verander de Status van een Attractie");
		langNL.addDefault("as-addzone", "voeg een Zone toe aan de lijst");
		langNL.addDefault("as-setzone", "verander de Zone van een Attractie");
		langNL.addDefault("as-removezone", "verwijder een Zone van de lijst");
		langNL.addDefault("as-tp", "tp naar een Attracties op een makkelijk manier!");
		langNL.addDefault("as-list", "laat u een lijst zien van alle Attracties en Zones");
		langNL.addDefault("as-msg1", "Er zijn geen Attracties gevonden!");
		langNL.addDefault("as-msg2", "Misschien moet u er een paar maken ;)");
		
		langNL.options().copyDefaults(true);
		
		FileConfiguration langEN = Configs.getConfigs().getLangEN();
		langEN.addDefault("no-permissions", "&cSorry, you don't have permission to use this command!");
		langEN.addDefault("player-only", "&cThis command can not be used in the Console!");
		langEN.addDefault("command-not-found", "&cCommand not found!");
		langEN.addDefault("as-help", "let you show all the AttractieStatus commands");
		langEN.addDefault("as-add", "add an Attraction with the status to the list");
		langEN.addDefault("as-remove", "remove an Attraction from lijst");
		langEN.addDefault("as-setstatus", "change the Status of an Attraction");
		langEN.addDefault("as-addzone", "add a Zone to the list");
		langEN.addDefault("as-setzone", "change the Zone of an Attraction");
		langEN.addDefault("as-removezone", "remove a Zone from the list");
		langEN.addDefault("as-tp", "tp to an Attraction simply!");
		langEN.addDefault("as-list", "let you show a list of all the Attraction and Zones");
		langEN.addDefault("as-msg1", "There are no Attractions found!");
		langEN.addDefault("as-msg2", "Maybe you should make a few ;)");
		
		langEN.options().copyDefaults(true);
		
		//attracties list
		FileConfiguration attracties = Configs.getConfigs().getAttracties();
		
		//zones list
		FileConfiguration zones = Configs.getConfigs().getZones();
		
		Configs.getConfigs().save();
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
