package nl.skelic.as.config;

import org.bukkit.configuration.file.FileConfiguration;

public class Load {
	
	public static Load load;
	
	public Load() {
		load = this;
	}
	
	public static void Config() {
		FileConfiguration config = Configs.getConfigs().getConfig();
		config.addDefault("Prefix", "&6[AS]");
		config.addDefault("Language", "nl_NL");
		
		config.options().copyDefaults(true);
		
		//attracties list
		//FileConfiguration attracties = Configs.getConfigs().getAttracties();
		
		//zones list
		//FileConfiguration zones = Configs.getConfigs().getZones();
		
		Configs.getConfigs().save();
	}
	
	public static void LangNL() {
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
		Configs.getConfigs().save();
	}
	
	public static void LangEN() {
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
		Configs.getConfigs().save();
	}
	
}
