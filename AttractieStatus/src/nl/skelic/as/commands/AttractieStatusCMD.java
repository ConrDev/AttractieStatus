package nl.skelic.as.commands;

import java.io.File;
import java.io.FilenameFilter;
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
import nl.skelic.as.managers.AttractiesManager;
import nl.skelic.as.utils.AttractiesUtil;
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
				sender.sendMessage(ChatColor.GOLD + "/AttractieStatus tp <Attractie Naam>" + ChatColor.GRAY + " - tp naar de Attractie!");
				sender.sendMessage(ChatColor.GOLD + "/AttractieStatus list" + ChatColor.GRAY + " - laat een lijst zien van alle Attracties");
				sender.sendMessage(ChatColor.YELLOW + "-===================================- ");
				return false;
			}
			
			Player player = (Player) sender;
			Location loc = player.getLocation();
			attractieDataFile = new File(plugin.getDataFolder() + File.separator + "Attracties" + File.separator, args[1].toString() + ".yml");
			attractieDataConfig = YamlConfiguration.loadConfiguration(attractieDataFile);
			if (args[0].equalsIgnoreCase("add")) {
				if (sender.hasPermission("attractiestatus.add")) {
					if (args.length < 2) {
						sender.sendMessage(Core.prefix + ChatColor.RED + "Het commando is fout! U moet dit invoeren: /AttractieStatus add <Attractie Naam>");
						return false;
					} else {
						//attractieDataFile = new File(plugin.getDataFolder() + File.separator + "Attracties" + File.separator, args[1].toString() + ".yml");
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
						//attractieDataConfig = YamlConfiguration.loadConfiguration(attractieDataFile);
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
						//attractieDataFile = new File(plugin.getDataFolder() + File.separator + "Attracties" + File.separator, args[1].toString() + ".yml");
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
						if (!attractieDataFile.exists()) {
							sender.sendMessage(Core.prefix + ChatColor.RED + args[1].toString() + " Bestaat niet!");
							return false;
						} else {
							attractieDataConfig.set("Status", args[2].toString());
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
						sender.sendMessage(Core.prefix + ChatColor.RED + "addzone");
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
						sender.sendMessage(Core.prefix + ChatColor.RED + "setzone");
						return false;
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
						//attractieDataFile = new File(plugin.getDataFolder() + File.separator + "Attracties" + File.separator, args[1].toString() + ".yml");
						if (!attractieDataFile.exists()) {
							sender.sendMessage(Core.prefix + ChatColor.RED + args[1].toString() + " Bestaat niet!");
							return false;
						} else {
							//attractieDataConfig = YamlConfiguration.loadConfiguration(attractieDataFile);
							player.teleport(new Location(player.getWorld(), attractieDataConfig.getDouble("Cordinaten.X"), attractieDataConfig.getDouble("Cordinaten.Y"), attractieDataConfig.getDouble("Cordinaten.Z")));
							sender.sendMessage(Core.prefix + ChatColor.YELLOW + "Succesvol geteleporteerd naar " + args[1].toString() + "!");
							return false;
						}
					}
				}
			}
			
			if (args[0].equalsIgnoreCase("list")) {
				if (AttractiesManager.getAttractiesManager().attracties.size() != 0) {
					sender.sendMessage(ChatColor.YELLOW + "-==========- " + ChatColor.GOLD + "Attractie Lijst" + ChatColor.YELLOW + " -==========- ");
					for (AttractiesUtil attractie : AttractiesManager.getAttractiesManager().attracties.values()) {
						sender.sendMessage(ChatColor.YELLOW + "" + attractie.getId() + ". " + ChatColor.GOLD + attractie.getName() + " " + ChatColor.GRAY + "- " + "staat in " + attractie.getZone());
					}
					sender.sendMessage(ChatColor.YELLOW + "-===================================- ");
					return false;
				} else {
					sender.sendMessage(ChatColor.YELLOW + "-==========- " + ChatColor.GOLD + "Attractie Lijst" + ChatColor.YELLOW + " -==========- ");
					sender.sendMessage(ChatColor.GOLD + "Er zijn geen Attracties gevonden!");
					sender.sendMessage(ChatColor.GRAY + "Misschien moet u er een paar maken ;)");
					sender.sendMessage(ChatColor.YELLOW + "-===================================- ");
					return false;
				}
			}
		}
		return false;
	}
}
