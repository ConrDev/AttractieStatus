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
		//config.addDefault("Language", "en_US");
		
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
		// Errors
		langNL.addDefault("errors.no-permissions", "&cSorry, u hebt geen permissie om dit commando uit te voeren!");
		langNL.addDefault("errors.player-only", "&cDit commando kan niet gebruikt worden in de Console!");
		langNL.addDefault("errors.command-not-found", "&cCommando niet gevonden!");
		langNL.addDefault("errors.dexist", "bestaat niet!");
		langNL.addDefault("errors.exists", "bestaat al!");
		langNL.addDefault("errors.wrong-command", "Dit commando is fout! u moet dit in typen:");
		
		//Messages
		langNL.addDefault("messages.succes-tp", "u bent geteleporteerd naard");
		langNL.addDefault("messages.succes-removed", "is succesfully verwijderd!");
		langNL.addDefault("messages.succes-created", "is succesfully aangemaakt!");
		langNL.addDefault("messages.zone-changed", "Zone is veranderd naar");
		langNL.addDefault("messages.status-changed", "Status is veranderd naar");
		
		// Messages for AttractionData
		langNL.addDefault("messages.attraction.attdata-creating", "AttractieData maken voor");
		langNL.addDefault("messages.attraction.attdata-loading", "AttractieData laden voor");
		langNL.addDefault("messages.attraction.attdata-saving", "AttractieData opslaan voor");
		
		// Messages for Attractions
		langNL.addDefault("messages.attraction.not-found", "De attractie kon niet gevonden worden");
		
		// Messages for ZoneData
		langNL.addDefault("messages.zone.zonedata-creating", "ZoneData maken voor");
		langNL.addDefault("messages.zone.zonedata-loading", "ZoneData laden voor");
		langNL.addDefault("messages.zone.zonedata-saving", "ZoneData opslaan voor");
		
		// Messages for Variables
		langNL.addDefault("messages.variables.name", "Name variable toevoegen aan");
		langNL.addDefault("messages.variables.status", "Status variable toevoegen aan");
		langNL.addDefault("messages.variables.zone", "Zone variable toevoegen aan");
		langNL.addDefault("messages.variables.owner", "Owner variable toevoegen aan");
		langNL.addDefault("messages.variables.icon", "Icon variable toevoegen aan");
		langNL.addDefault("messages.variables.attraction", "Attraction variable toevoegen aan");
		
		// Messages for Attraction Signs
		langNL.addDefault("messages.asigns.created", "Attractie Sign succesvol aangemaakt!");
		langNL.addDefault("messages.asigns.removed", "De attractie sign is succesvol verwijderd!");
		
		// Commands
		langNL.addDefault("commands.as-help", "laat u alle commandos zien van AttractieStatus");
		langNL.addDefault("commands.as-add", "voeg een Attractie met de status toe aan de lijst");
		langNL.addDefault("commands.as-remove", "verwijder een Attractie van de lijst");
		langNL.addDefault("commands.as-setstatus", "verander de Status van een Attractie");
		langNL.addDefault("commands.as-addzone", "voeg een Zone toe aan de lijst");
		langNL.addDefault("commands.as-setzone", "verander de Zone van een Attractie");
		langNL.addDefault("commands.as-removezone", "verwijder een Zone van de lijst");
		langNL.addDefault("commands.as-tp", "tp naar een Attracties op een makkelijk manier!");
		langNL.addDefault("commands.as-list", "laat u een lijst zien van alle Attracties en Zones");
		langNL.addDefault("commands.as-msg1", "Er zijn geen Attracties gevonden!");
		langNL.addDefault("commands.as-msg2", "Misschien moet u er een paar maken ;)");
				
		langNL.options().copyDefaults(true);
		Configs.getConfigs().save();
	}
	
	public static void Lang() {
		FileConfiguration lang = Configs.getConfigs().getLang();
		// Errors
		lang.addDefault("errors.no-permissions", "&cSorry, you don't have permission to use this command!");
		lang.addDefault("errors.player-only", "&cThis command can not be used in the Console!");
		lang.addDefault("errors.command-not-found", "&cCommand not found!");
		lang.addDefault("errors.dexist", "doesn't exist!");
		lang.addDefault("errors.exists", "already exists!");
		lang.addDefault("errors.wrong-command", "The command is wrong! You need to type this:");
		
		//Messages
		lang.addDefault("messages.succes-tp", "you are teleported to");
		lang.addDefault("messages.succes-removed", "is succesfully removed!");
		lang.addDefault("messages.succes-created", "is succesfully created!");
		lang.addDefault("messages.zone-changed", "Zone is changed to");
		lang.addDefault("messages.status-changed", "Status is changed to");
		
		// Messages for AttractionData
		lang.addDefault("messages.attraction.attdata-creating", "Creating AttractionData for");
		lang.addDefault("messages.attraction.attdata-loading", "Loading AttractionData for");
		lang.addDefault("messages.attraction.attdata-saving", "Saving AttractionData for");
		
		// Messages for Attractions
		lang.addDefault("messages.attraction.not-found", "The attraction could not be found");
		
		// Messages for ZoneData
		lang.addDefault("messages.zone.zonedata-creating", "Creating ZoneData for");
		lang.addDefault("messages.zone.zonedata-loading", "Loading ZoneData for");
		lang.addDefault("messages.zone.zonedata-saving", "Saving ZoneData for");
		
		// Messages for Variables
		lang.addDefault("messages.variables.name", "Adding Name variable to");
		lang.addDefault("messages.variables.status", "Adding Status variable to");
		lang.addDefault("messages.variables.zone", "Adding Zone variable to");
		lang.addDefault("messages.variables.owner", "Adding Owner variable to");
		lang.addDefault("messages.variables.icon", "Adding Icon variable to");
		lang.addDefault("messages.variables.attraction", "Adding Attraction variable to");
		
		// Messages for Attraction Signs
		lang.addDefault("messages.asigns.created", "Attraction Sign succesfully created!");
		lang.addDefault("messages.asigns.removed", "The attraction sign is succesfully removed!");
		
		// Commands
		lang.addDefault("commands.as-help", "Let you show all the AttractionStatus commands");
		lang.addDefault("commands.as-add", "Add an Attraction with the status to the list");
		lang.addDefault("commands.as-remove", "Remove an Attraction from lijst");
		lang.addDefault("commands.as-setstatus", "Change the Status of an Attraction");
		lang.addDefault("commands.as-addzone", "Add a Zone to the list");
		lang.addDefault("commands.as-setzone", "Change the Zone of an Attraction");
		lang.addDefault("commands.as-removezone", "Remove a Zone from the list");
		lang.addDefault("commands.as-tp", "Tp to an Attraction simply!");
		lang.addDefault("commands.as-list", "Let you show a list of all the Attraction and Zones");
		lang.addDefault("commands.as-msg1", "There are no Attractions found!");
		lang.addDefault("commands.as-msg2", "Maybe you should make a few ;)");
		
		lang.options().copyDefaults(true);
		Configs.getConfigs().save();
	}
	
}
