package nl.skelic.as.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import nl.skelic.as.Core;
import nl.skelic.as.utils.MsgUtil;
import nl.skelic.as.utils.Util;

public class AttractieStatusCMD implements CommandExecutor {
	
	private Core plugin;
	private Util util;
	public FileConfiguration attractieDataConfig;
	public YamlConfiguration attractieConfig;
	public File attractieDataFile;
	
	public AttractieStatusCMD(Core plugin) {
		this.plugin = plugin;
		util = plugin.getUtil();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(MsgUtil.NOTPLR.getMessage());
			return false;
		}
		
		if (args.length < 1) {
			sender.sendMessage(ChatColor.YELLOW + "-==========- " + ChatColor.GOLD + "AttractieStatus" + ChatColor.YELLOW + " -==========- ");
			sender.sendMessage(ChatColor.GOLD + "/AttractieStatus help" + ChatColor.GRAY + " - laat u alle commandos zien van AttractieStatus");
			sender.sendMessage(ChatColor.GOLD + "/AttractieStatus add <Attractie Naam> <Cords(X Y Z)>" + ChatColor.GRAY + " - voeg een Attractie met de status toe aan de lijst");
			sender.sendMessage(ChatColor.GOLD + "/AttractieStatus remove <Attractie Naam>" + ChatColor.GRAY + " - verwijder een Attractie van de lijst");
			sender.sendMessage(ChatColor.GOLD + "/AttractieStatus setstatus <Attractie Naam> <Status>" + ChatColor.GRAY + " - verander de Status van de Attractie");
			sender.sendMessage(ChatColor.GOLD + "/AttractieStatus addzone <Zone Naam> <Item ID> <Cords(X Y Z)>" + ChatColor.GRAY + " - hiermee verandert u de Status van de Attractie");
			sender.sendMessage(ChatColor.GOLD + "/AttractieStatus setzone <Attractie Naam> <Zone Naam>" + ChatColor.GRAY + " - hiermee verandert u de Status van de Attractie");
			sender.sendMessage(ChatColor.GOLD + "/AttractieStatus tp <Attractie Naam>" + ChatColor.GRAY + " - tp naar de Attractie!");
			sender.sendMessage(ChatColor.GOLD + "/AttractieStatus list" + ChatColor.GRAY + " - laat een lijst zien van alle Attracties");
			sender.sendMessage(ChatColor.YELLOW + "-===================================- ");
			return false;
		} else {
			if (args[0].equalsIgnoreCase("help")) {
				sender.sendMessage(ChatColor.YELLOW + "-==========- " + ChatColor.GOLD + "AttractieStatus" + ChatColor.YELLOW + " -==========- ");
				sender.sendMessage(ChatColor.GOLD + "/AttractieStatus help" + ChatColor.GRAY + " - laat u alle commandos zien van AttractieStatus");
				sender.sendMessage(ChatColor.GOLD + "/AttractieStatus add <Attractie Naam> <Cords(X Y Z)>" + ChatColor.GRAY + " - voeg een Attractie met de status toe aan de lijst");
				sender.sendMessage(ChatColor.GOLD + "/AttractieStatus remove <Attractie Naam>" + ChatColor.GRAY + " - verwijder een Attractie van de lijst");
				sender.sendMessage(ChatColor.GOLD + "/AttractieStatus setstatus <Attractie Naam> <Status>" + ChatColor.GRAY + " - verander de Status van de Attractie");
				sender.sendMessage(ChatColor.GOLD + "/AttractieStatus addzone <Zone Naam> <Item ID> <Cords(X Y Z)>" + ChatColor.GRAY + " - hiermee verandert u de Status van de Attractie");
				sender.sendMessage(ChatColor.GOLD + "/AttractieStatus setzone <Attractie Naam> <Zone Naam>" + ChatColor.GRAY + " - hiermee verandert u de Status van de Attractie");
				sender.sendMessage(ChatColor.GOLD + "/AttractieStatus removezone <Zone Naam>" + ChatColor.GRAY + " - verwijder een Zone van de lijst");
				sender.sendMessage(ChatColor.GOLD + "/AttractieStatus tp <Attractie Naam>" + ChatColor.GRAY + " - tp naar de Attractie!");
				sender.sendMessage(ChatColor.GOLD + "/AttractieStatus list" + ChatColor.GRAY + " - laat een lijst zien van alle Attracties");
				sender.sendMessage(ChatColor.YELLOW + "-===================================- ");
				return false;
			}
			
			Player player = (Player) sender;
			Location loc = player.getLocation();
			//attractieDataFile = new File(plugin.getDataFolder() + File.separator + "Attracties" + File.separator, args[1].toString() + ".yml");
			//attractieDataConfig = YamlConfiguration.loadConfiguration(attractieDataFile);
			//File zoneDataFile = new File(plugin.getDataFolder() + File.separator + "Zones" + File.separator, args[1].toString() + ".yml");
			//FileConfiguration zoneDataConfig = YamlConfiguration.loadConfiguration(zoneDataFile);
			if (args[0].equalsIgnoreCase("add")) {
				if (sender.hasPermission("attractiestatus.add")) {
					if (args.length < 2) {
						sender.sendMessage(Core.prefix + ChatColor.RED + "Het commando is fout! U moet dit invoeren: /AttractieStatus add <Attractie Naam>");
						return false;
					} else {
						attractieDataFile = new File(plugin.getDataFolder() + File.separator + "Attracties" + File.separator, args[1].toString() + ".yml");
						if (!attractieDataFile.exists()) {
							try {
								attractieDataFile.createNewFile();
								Bukkit.getConsoleSender().sendMessage(Core.prefix + ChatColor.GOLD + "AttractieData maken voor " + args[1].toString());
							} catch (IOException e) {
								e.printStackTrace();
							}
						} else {
							sender.sendMessage(Core.prefix + ChatColor.RED + args[1].toString() + " bestaat al!");
							return false;
						}
						attractieDataConfig = YamlConfiguration.loadConfiguration(attractieDataFile);
						Bukkit.getConsoleSender().sendMessage(Core.prefix + ChatColor.GOLD + "AttractieData laden voor " + args[1].toString());
						if (!attractieDataConfig.contains("Naam")) {
							attractieDataConfig.set("Naam", args[1].toString());
							Bukkit.getConsoleSender().sendMessage(Core.prefix + ChatColor.GOLD + "Naam variabel toevoegen aan " + args[1].toString());
						}
						if (!attractieDataConfig.contains("Status")) {
							attractieDataConfig.set("Status", "&cGesloten");
							Bukkit.getConsoleSender().sendMessage(Core.prefix + ChatColor.GOLD + "Status variabel toevoegen aan " + args[1].toString());
						}
						if (!attractieDataConfig.contains("Cordinaten.X")) {
							attractieDataConfig.set("Cordinaten.X", loc.getX());
							if (!attractieDataConfig.contains("Cordinaten.Y")) {
								attractieDataConfig.set("Cordinaten.Y", loc.getY());
								if (!attractieDataConfig.contains("Cordinaten.Z")) {
									attractieDataConfig.set("Cordinaten.Z", loc.getZ());
									Bukkit.getConsoleSender().sendMessage(Core.prefix + ChatColor.GOLD + " Cordinaten variabel toevoegen aan " + args[1].toString());
								}
							}
						}
						if (!attractieDataConfig.contains("Zone")) {
							attractieDataConfig.set("Zone", "geen Zone gevonden");
							Bukkit.getConsoleSender().sendMessage(Core.prefix + ChatColor.GOLD + "Zone variabel toevoegen aan " + args[1].toString());
						}
						util.saveFile(attractieDataConfig, attractieDataFile);
						if (!attractieDataConfig.contains("Owner")) {
							attractieDataConfig.set("Owner", player.getName().toString());
							Bukkit.getConsoleSender().sendMessage(Core.prefix + ChatColor.GOLD + "Owner variabel toevoegen aan " + args[1].toString());
						}
						util.saveFile(attractieDataConfig, attractieDataFile);
						Bukkit.getConsoleSender().sendMessage(Core.prefix + ChatColor.GOLD + "AttractieData opslaan voor " + args[1].toString());
						sender.sendMessage(Core.prefix + ChatColor.GREEN + args[1].toString() + " is succesvol aangemaakt!");
						return false;
					}
				} else {
					sender.sendMessage(MsgUtil.NOPERM.getMessage());
					return false;
				}
			}
				
			if (args[0].equalsIgnoreCase("remove")) {
				if (sender.hasPermission("attractiestatus.remove")) {
					if (args.length < 2) {
						sender.sendMessage(Core.prefix + ChatColor.RED + "Het commando is fout! U moet dit invoeren: /AttractieStatus remove <Attractie Naam>");
						return false;
					} else {
						attractieDataFile = new File(plugin.getDataFolder() + File.separator + "Attracties" + File.separator, args[1].toString() + ".yml");
						if (!attractieDataFile.exists()) {
							sender.sendMessage(Core.prefix + ChatColor.RED + args[1].toString() + " Bestaat niet!");
							return false;
						} else {
							attractieDataFile.delete();
							sender.sendMessage(Core.prefix + ChatColor.GREEN + args[1].toString() + " is succesvol verwijdert!");
							return false;
						}
					}
				} else {
					sender.sendMessage(MsgUtil.NOPERM.getMessage());
					return false;
				}
			}
				
			if (args[0].equalsIgnoreCase("setstatus")) {
				if (sender.hasPermission("attractiestatus.setstatus")) {
					if (args.length < 3) {
						sender.sendMessage(Core.prefix + ChatColor.RED + "Het commando is fout! U moet dit invoeren: /AttractieStatus setstatus <Attractie Naam> <Status>");
						return false;
					} else {
						attractieDataFile = new File(plugin.getDataFolder() + File.separator + "Attracties" + File.separator, args[1].toString() + ".yml");
						if (!attractieDataFile.exists()) {
							sender.sendMessage(Core.prefix + ChatColor.RED + args[1].toString() + " Bestaat niet!");
							return false;
						} else {
							attractieDataConfig = YamlConfiguration.loadConfiguration(attractieDataFile);
							attractieDataConfig.set("Status", args[2].toString());
							sender.sendMessage(Core.prefix + ChatColor.GREEN + args[1].toString() + "'s Status is verander naar " + args[2].toString());
							util.saveFile(attractieDataConfig, attractieDataFile);
							return false;
						}
					}
				} else {
					sender.sendMessage(MsgUtil.NOPERM.getMessage());
					return false;
				}
			}
			
			if (args[0].equalsIgnoreCase("addzone")) {
				if (sender.hasPermission("attractiestatus.addzone")) {
					if (args.length < 3) {
						sender.sendMessage(Core.prefix + ChatColor.RED + "Het commando is fout! U moet dit invoeren: /AttractieStatus addzone <Zone Naam> <Item ID>");
					} else {
						File zoneDataFile = new File(plugin.getDataFolder() + File.separator + "Zones" + File.separator, args[1].toString() + ".yml");
						if (!zoneDataFile.exists()) {
							try {
								zoneDataFile.createNewFile();
								Bukkit.getConsoleSender().sendMessage(Core.prefix + ChatColor.GOLD + "ZoneData maken voor " + args[1].toString());
							} catch (IOException e) {
								e.printStackTrace();
							}
						} else {
							sender.sendMessage(Core.prefix + ChatColor.RED + args[1].toString() + " bestaat al!");
							return false;
						}
						YamlConfiguration zoneDataConfig = YamlConfiguration.loadConfiguration(zoneDataFile);
						Bukkit.getConsoleSender().sendMessage(Core.prefix + ChatColor.GOLD + "ZoneData laden voor " + args[1].toString());
						if (!zoneDataConfig.contains("Naam")) {
							zoneDataConfig.set("Naam", args[1].toString());
							Bukkit.getConsoleSender().sendMessage(Core.prefix + ChatColor.GOLD + "Naam variabel toevoegen aan " + args[1].toString());
						}
						
						if (!zoneDataConfig.contains("Icon")) {
							zoneDataConfig.set("Icon", args[2].toString());
							Bukkit.getConsoleSender().sendMessage(Core.prefix + ChatColor.GOLD + "Icon variabel toevoegen aan " + args[1].toString());
						}
						if (!zoneDataConfig.contains("Cordinaten.X")) {
							zoneDataConfig.set("Cordinaten.X", loc.getX());
							if (!zoneDataConfig.contains("Cordinaten.Y")) {
								zoneDataConfig.set("Cordinaten.Y", loc.getY());
								if (!zoneDataConfig.contains("Cordinaten.Z")) {
									zoneDataConfig.set("Cordinaten.Z", loc.getZ());
									Bukkit.getConsoleSender().sendMessage(Core.prefix + ChatColor.GOLD + " Cordinaten variabel toevoegen aan " + args[1].toString());
								}
							}
						}
						util.saveFile(zoneDataConfig, zoneDataFile);
						if (!zoneDataConfig.contains("Owner")) {
							zoneDataConfig.set("Owner", player.getName().toString());
							Bukkit.getConsoleSender().sendMessage(Core.prefix + ChatColor.GOLD + "Owner variabel toevoegen aan " + args[1].toString());
						}
						util.saveFile(zoneDataConfig, zoneDataFile);
						Bukkit.getConsoleSender().sendMessage(Core.prefix + ChatColor.GOLD + "ZoneData opslaan voor " + args[1].toString());
						sender.sendMessage(Core.prefix + ChatColor.GREEN + args[1].toString() + " is succesvol aangemaakt!");
						return false;
					}
				} else {
					sender.sendMessage(MsgUtil.NOPERM.getMessage());
				}
			}
			
			if (args[0].equalsIgnoreCase("setzone")) {
				if (sender.hasPermission("attractiestatus.setzone")) {
					if (args.length < 3) {
						sender.sendMessage(Core.prefix + ChatColor.RED + "Het commando is fout! U moet dit invoeren: /AttractieStatus setzone <Attractie Naam> <Zone Naam>");
						return false;
					} else {
						attractieDataFile = new File(plugin.getDataFolder() + File.separator + "Attracties" + File.separator, args[1].toString() + ".yml");
						if (!attractieDataFile.exists()) {
							sender.sendMessage(Core.prefix + ChatColor.RED + args[1].toString() + " Bestaat niet!");
							return false;
						} else {
							attractieDataConfig = YamlConfiguration.loadConfiguration(attractieDataFile);
							attractieDataConfig.set("Zone", args[2].toString());
							sender.sendMessage(Core.prefix + ChatColor.GREEN + args[1].toString() + "'s Zone is verander naar " + args[2].toString());
							util.saveFile(attractieDataConfig, attractieDataFile);
							return false;
						}
					}
				} else {
					sender.sendMessage(MsgUtil.NOPERM.getMessage());
					return false;
				}
			}
			
			if (args[0].equalsIgnoreCase("removezone")) {
				if (sender.hasPermission("attractiestatus.removezone")) {
					if (args.length < 2) {
						sender.sendMessage(Core.prefix + ChatColor.RED + "Het commando is fout! U moet dit invoeren: /AttractieStatus removezone <Zone Naam>");
						return false;
					} else {
						File zoneDataFile = new File(plugin.getDataFolder() + File.separator + "Zones" + File.separator, args[1].toString() + ".yml");
						if (!zoneDataFile.exists()) {
							sender.sendMessage(Core.prefix + ChatColor.RED + args[1].toString() + " Bestaat niet!");
							return false;
						} else {
							zoneDataFile.delete();
							//util.saveFile(attractieDataConfig, attractieDataFile);
							sender.sendMessage(Core.prefix + ChatColor.GREEN + args[1].toString() + " is succesvol verwijdert!");
							return false;
						}
					}
				} else {
					sender.sendMessage(MsgUtil.NOPERM.getMessage());
					return false;
				}
			}
			
			if (args[0].equalsIgnoreCase("tp")) {
				if (sender.hasPermission("attractiestatus.tp")) {
					if (args.length < 2) {
						sender.sendMessage(Core.prefix + ChatColor.RED + "Het commando is fout! U moet dit invoeren: /AttractieStatus tp <Attractie Naam>");
						return false;
					} else {
						attractieDataFile = new File(plugin.getDataFolder() + File.separator + "Attracties" + File.separator, args[1].toString() + ".yml");
						if (!attractieDataFile.exists()) {
							sender.sendMessage(Core.prefix + ChatColor.RED + args[1].toString() + " Bestaat niet!");
							return false;
						} else {
							attractieDataConfig = YamlConfiguration.loadConfiguration(attractieDataFile);
							player.teleport(new Location(player.getWorld(), attractieDataConfig.getDouble("Cordinaten.X"), attractieDataConfig.getDouble("Cordinaten.Y"), attractieDataConfig.getDouble("Cordinaten.Z")));
							sender.sendMessage(Core.prefix + ChatColor.YELLOW + "Succesvol geteleporteerd naar " + args[1].toString() + "!");
							return false;
						}
					}
				}
			}
			
			File aFolder = new File(plugin.getDataFolder() + File.separator + "Attracties" + File.separator);
			File[] aList = aFolder.listFiles();
			
			if (args[0].equalsIgnoreCase("list")) {
				if (aList.length != 0) {
					sender.sendMessage(ChatColor.YELLOW + "-==========- " + ChatColor.GOLD + "Attractie Lijst" + ChatColor.YELLOW + " -==========- ");
					for (int i = 0; i < aList.length; i++) {
						if (aList[i].isFile()) {
							sender.sendMessage(/*ChatColor.YELLOW + "" + (i++ < aList[i].length()) + ". " +*/ ChatColor.GOLD + aList[i].getName().replace(".yml", "") + " " + ChatColor.GRAY + "- " + "staat in Zone ");
						}
					}
					sender.sendMessage(ChatColor.YELLOW + "-===================================- ");
					return true;
				} else {
					sender.sendMessage(ChatColor.YELLOW + "-==========- " + ChatColor.GOLD + "Attractie Lijst" + ChatColor.YELLOW + " -==========- ");
					sender.sendMessage(ChatColor.GOLD + "Er zijn geen Attracties gevonden!");
					sender.sendMessage(ChatColor.GRAY + "Misschien moet u er een paar maken ;)");
					sender.sendMessage(ChatColor.YELLOW + "-===================================- ");
					return false;
				}
			}
			
			if (args[0].equals(null)) {
				sender.sendMessage(Core.prefix + ChatColor.RED + "Oeps! Dit commando bestaat niet. type /AttractieStatus of /AttractieStatus help om alle commandos te zien!");
			}
		}
		return false;
	}
}
