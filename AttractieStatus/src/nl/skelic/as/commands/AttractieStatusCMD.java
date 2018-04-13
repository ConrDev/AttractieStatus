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
import nl.skelic.as.config.Configs;
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
	
    public Location parseLoc(Player player, String str){
        String str2loc[]=str.split(",");
        Location loc = new Location(player.getWorld(),0,0,0);
        loc.setX(Double.parseDouble(str2loc[0]));
        loc.setY(Double.parseDouble(str2loc[1]));
        loc.setZ(Double.parseDouble(str2loc[2]));
        return loc;
    }
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(MsgUtil.NOTPLR.getMessage());
			return false;
		} else {
		if (args.length < 1) {
			sender.sendMessage(ChatColor.YELLOW + "-==========- " + ChatColor.GOLD + "AttractionStatus" + ChatColor.YELLOW + " -==========-");
			sender.sendMessage(ChatColor.GOLD + "/AttractionStatus help" + ChatColor.GRAY + " - " + Configs.getConfigs().color(Configs.getConfigs().getLang().getString("commands.as-help")));
			sender.sendMessage(ChatColor.GOLD + "/AttractionStatus add <Attraction Name>" + ChatColor.GRAY + " - " + Configs.getConfigs().color(Configs.getConfigs().getLang().getString("commands.as-add")));
			sender.sendMessage(ChatColor.GOLD + "/AttractionStatus remove <Attraction Name>" + ChatColor.GRAY + " - " + Configs.getConfigs().color(Configs.getConfigs().getLang().getString("commands.as-remove")));
			sender.sendMessage(ChatColor.GOLD + "/AttractionStatus setstatus <Attractie Name> <Status>" + ChatColor.GRAY + " - " + Configs.getConfigs().color(Configs.getConfigs().getLang().getString("commands.as-setstatus")));
			sender.sendMessage(ChatColor.GOLD + "/AttractionStatus addzone <Zone Name> <Item ID>" + ChatColor.GRAY + " - " + Configs.getConfigs().color(Configs.getConfigs().getLang().getString("commands.as-addzone")));
			sender.sendMessage(ChatColor.GOLD + "/AttractionStatus setzone <Attraction Name> <Zone Name>" + ChatColor.GRAY + " - " + Configs.getConfigs().color(Configs.getConfigs().getLang().getString("commands.as-setzone")));
			sender.sendMessage(ChatColor.GOLD + "/AttractionStatus removezone <Zone Name>" + ChatColor.GRAY + " - " + Configs.getConfigs().color(Configs.getConfigs().getLang().getString("commands.as-removezone")));
			sender.sendMessage(ChatColor.GOLD + "/AttractionStatus tp <Attraction Name>" + ChatColor.GRAY + " - " + Configs.getConfigs().color(Configs.getConfigs().getLang().getString("commands.as-tp")));
			sender.sendMessage(ChatColor.GOLD + "/AttractionStatus list" + ChatColor.GRAY + " - " + Configs.getConfigs().color(Configs.getConfigs().getLang().getString("commands.as-list")));
			sender.sendMessage(ChatColor.YELLOW + "-===================================-");
			return false;
		} else {
			if (args[0].equalsIgnoreCase("help")) {
				sender.sendMessage(ChatColor.YELLOW + "-==========- " + ChatColor.GOLD + "AttractionStatus" + ChatColor.YELLOW + " -==========-");
				sender.sendMessage(ChatColor.GOLD + "/AttractionStatus help" + ChatColor.GRAY + " - " + Configs.getConfigs().color(Configs.getConfigs().getLang().getString("commands.as-help")));
				sender.sendMessage(ChatColor.GOLD + "/AttractionStatus add <Attraction Name>" + ChatColor.GRAY + " - " + Configs.getConfigs().color(Configs.getConfigs().getLang().getString("commands.as-add")));
				sender.sendMessage(ChatColor.GOLD + "/AttractionStatus remove <Attraction Name>" + ChatColor.GRAY + " - " + Configs.getConfigs().color(Configs.getConfigs().getLang().getString("commands.as-remove")));
				sender.sendMessage(ChatColor.GOLD + "/AttractionStatus setstatus <Attractie Name> <Status>" + ChatColor.GRAY + " - " + Configs.getConfigs().color(Configs.getConfigs().getLang().getString("commands.as-setstatus")));
				sender.sendMessage(ChatColor.GOLD + "/AttractionStatus addzone <Zone Name> <Item ID>" + ChatColor.GRAY + " - " + Configs.getConfigs().color(Configs.getConfigs().getLang().getString("commands.as-addzone")));
				sender.sendMessage(ChatColor.GOLD + "/AttractionStatus setzone <Attraction Name> <Zone Name>" + ChatColor.GRAY + " - " + Configs.getConfigs().color(Configs.getConfigs().getLang().getString("commands.as-setzone")));
				sender.sendMessage(ChatColor.GOLD + "/AttractionStatus removezone <Zone Name>" + ChatColor.GRAY + " - " + Configs.getConfigs().color(Configs.getConfigs().getLang().getString("commands.as-removezone")));
				sender.sendMessage(ChatColor.GOLD + "/AttractionStatus tp <Attraction Name>" + ChatColor.GRAY + " - " + Configs.getConfigs().color(Configs.getConfigs().getLang().getString("commands.as-tp")));
				sender.sendMessage(ChatColor.GOLD + "/AttractionStatus list" + ChatColor.GRAY + " - " + Configs.getConfigs().color(Configs.getConfigs().getLang().getString("commands.as-list")));
				sender.sendMessage(ChatColor.YELLOW + "-===================================-");
				return false;
			}
			
			/*if (args[0].equalsIgnoreCase(null)) {
				sender.sendMessage(Util.prefix + ChatColor.RED + MsgUtil.CMDNF.getMessage());
			}*/
			
			Player player = (Player) sender;
			Location loc = player.getLocation();
			if (args[0].equalsIgnoreCase("add")) {
				if (sender.hasPermission("AttractionStatus.add")) {
					if (args.length < 2) {
						sender.sendMessage(Util.prefix + ChatColor.RED + Configs.getConfigs().getLang().getString("errors.wrong-command") +" /AttractionStatus add <Attractie Name>");
						return false;
					} else {
						attractieDataFile = new File(plugin.getDataFolder() + File.separator + "Attractions" + File.separator, args[1].toString() + ".yml");
						if (!attractieDataFile.exists()) {
							try {
								attractieDataFile.createNewFile();
								Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + Configs.getConfigs().getLang().getString("messages.attraction.attdata-creating") + " " + args[1].toString());
							} catch (IOException e) {
								e.printStackTrace();
							}
						} else {
							sender.sendMessage(Util.prefix + ChatColor.RED + args[1].toString() + " " + Configs.getConfigs().getLang().getString("messages.exists"));
							return false;
						}
						attractieDataConfig = YamlConfiguration.loadConfiguration(attractieDataFile);
						Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + Configs.getConfigs().getLang().getString("messages.attraction.attdata-loading") + " " + args[1].toString());
						if (!attractieDataConfig.contains("Name")) {
							attractieDataConfig.set("Name", args[1].toString());
							Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + Configs.getConfigs().getLang().getString("messages.variables.name") + " " + args[1].toString());
						}
						if (!attractieDataConfig.contains("Status")) {
							attractieDataConfig.set("Status", "&cGesloten");
							Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + Configs.getConfigs().getLang().getString("messages.variables.status") + " " + args[1].toString());
						}
						/*if (!attractieDataConfig.contains("Coordinates.X")) {
							attractieDataConfig.set("Coordinates.X", loc.getX());
							if (!attractieDataConfig.contains("Coordinates.Y")) {
								attractieDataConfig.set("Coordinates.Y", loc.getY());
								if (!attractieDataConfig.contains("Coordinates.Z")) {
									attractieDataConfig.set("Coordinates.Z", loc.getZ());
									Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + " Coordinates variabel toevoegen aan " + args[1].toString());
								}
							}
						}*/
						if (!attractieDataConfig.contains("Coordinates")) {
							attractieDataConfig.set("Coordinates", loc.getX() + "," + loc.getY() + "," + loc.getZ());
							Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + Configs.getConfigs().getLang().getString("messages.variables.cords") + " " + args[1].toString());
						}
						if (!attractieDataConfig.contains("Zone")) {
							attractieDataConfig.set("Zone", "No zone found");
							Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + Configs.getConfigs().getLang().getString("messages.variables.zone") + " " + args[1].toString());
						}
						util.saveFile(attractieDataConfig, attractieDataFile);
						if (!attractieDataConfig.contains("Owner")) {
							attractieDataConfig.set("Owner", player.getName().toString());
							Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + Configs.getConfigs().getLang().getString("messages.variables.owner") + " " + args[1].toString());
						}
						util.saveFile(attractieDataConfig, attractieDataFile);
						Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + Configs.getConfigs().getLang().getString("messages.attraction.attdata-saving") + " " + args[1].toString());
						sender.sendMessage(Util.prefix + ChatColor.GREEN + args[1].toString() + " " + Configs.getConfigs().getLang().getString("messages.succes-created"));
						return false;
					}
				} else {
					sender.sendMessage(MsgUtil.NOPERM.getMessage());
					return false;
				}
			}
				
			if (args[0].equalsIgnoreCase("remove")) {
				if (sender.hasPermission("AttractionStatus.remove")) {
					if (args.length < 2) {
						sender.sendMessage(Util.prefix + ChatColor.RED + Configs.getConfigs().getLang().getString("errors.wrong-command") +" /AttractionStatus remove <Attractie Name>");
						return false;
					} else {
						attractieDataFile = new File(plugin.getDataFolder() + File.separator + "Attractions" + File.separator, args[1].toString() + ".yml");
						if (!attractieDataFile.exists()) {
							sender.sendMessage(Util.prefix + ChatColor.RED + args[1].toString() + " " + Configs.getConfigs().getLang().getString("errors.dexist"));
							return false;
						} else {
							attractieDataFile.delete();
							sender.sendMessage(Util.prefix + ChatColor.GREEN + args[1].toString() + " " + Configs.getConfigs().getLang().getString("messages.succes-removed"));
							return false;
						}
					}
				} else {
					sender.sendMessage(MsgUtil.NOPERM.getMessage());
					return false;
				}
			}
				
			if (args[0].equalsIgnoreCase("setstatus")) {
				if (sender.hasPermission("AttractionStatus.setstatus")) {
					if (args.length < 3) {
						sender.sendMessage(Util.prefix + ChatColor.RED + Configs.getConfigs().getLang().getString("errors.wrong-command") +" /AttractionStatus setstatus <Attractie Name> <Status>");
						return false;
					} else {
						attractieDataFile = new File(plugin.getDataFolder() + File.separator + "Attractions" + File.separator, args[1].toString() + ".yml");
						if (!attractieDataFile.exists()) {
							sender.sendMessage(Util.prefix + ChatColor.RED + args[1].toString() + " " + Configs.getConfigs().getLang().getString("errors.dexist"));
							return false;
						} else {
							attractieDataConfig = YamlConfiguration.loadConfiguration(attractieDataFile);
							attractieDataConfig.set("Status", args[2].toString());
							sender.sendMessage(Util.prefix + ChatColor.GREEN + args[1].toString() + "'s " + Configs.getConfigs().getLang().getString("messages.status-changed") + " " + args[2].toString());
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
				if (sender.hasPermission("AttractionStatus.addzone")) {
					if (args.length < 3) {
						sender.sendMessage(Util.prefix + ChatColor.RED + Configs.getConfigs().getLang().getString("errors.wrong-command") +" /AttractionStatus addzone <Zone Name> <Item ID>");
					} else {
						File zoneDataFile = new File(plugin.getDataFolder() + File.separator + "Zones" + File.separator, args[1].toString() + ".yml");
						if (!zoneDataFile.exists()) {
							try {
								zoneDataFile.createNewFile();
								Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + Configs.getConfigs().getLang().getString("messages.zone.zonedata-creating") + " " + args[1].toString());
							} catch (IOException e) {
								e.printStackTrace();
							}
						} else {
							sender.sendMessage(Util.prefix + ChatColor.RED + args[1].toString() + " " + Configs.getConfigs().getLang().getString("messages.exists"));
							return false;
						}
						YamlConfiguration zoneDataConfig = YamlConfiguration.loadConfiguration(zoneDataFile);
						Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + Configs.getConfigs().getLang().getString("messages.zone.zonedata-loading") + " " + args[1].toString());
						if (!zoneDataConfig.contains("Name")) {
							zoneDataConfig.set("Name", args[1].toString());
							Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + Configs.getConfigs().getLang().getString("messages.variables.name") + " " + args[1].toString());
						}
						
						if (!zoneDataConfig.contains("Icon")) {
							zoneDataConfig.set("Icon", args[2].toString());
							Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + Configs.getConfigs().getLang().getString("messages.variables.icon") + " " + args[1].toString());
						}
						/*if (!zoneDataConfig.contains("Coordinates.X")) {
							zoneDataConfig.set("Coordinates.X", loc.getX());
							if (!zoneDataConfig.contains("Coordinates.Y")) {
								zoneDataConfig.set("Coordinates.Y", loc.getY());
								if (!zoneDataConfig.contains("Coordinates.Z")) {
									zoneDataConfig.set("Coordinates.Z", loc.getZ());
									Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + " Coordinates variabel toevoegen aan " + args[1].toString());
								}
							}
						}*/
						if (!zoneDataConfig.contains("Coordinates")) {
							zoneDataConfig.set("Coordinates", loc.getX() + "," + loc.getY() + "," + loc.getZ());
							Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + Configs.getConfigs().getLang().getString("messages.variables.cords") + " " + args[1].toString());
						}
						if (!zoneDataConfig.contains("Attractions")) {
							zoneDataConfig.set("Attractions", "No attraction found");
							Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + Configs.getConfigs().getLang().getString("messages.variables.attraction") + " " + args[1].toString());
						}
						util.saveFile(zoneDataConfig, zoneDataFile);
						if (!zoneDataConfig.contains("Owner")) {
							zoneDataConfig.set("Owner", player.getName().toString());
							Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + Configs.getConfigs().getLang().getString("messages.variables.owner") + " " + args[1].toString());
						}
						util.saveFile(zoneDataConfig, zoneDataFile);
						Bukkit.getConsoleSender().sendMessage(Util.prefix + ChatColor.GOLD + Configs.getConfigs().getLang().getString("messages.zone.zonedata-saving") + " " + args[1].toString());
						sender.sendMessage(Util.prefix + ChatColor.GREEN + args[1].toString() + " " + Configs.getConfigs().getLang().getString("messages.succes-created"));
						return false;
					}
				} else {
					sender.sendMessage(MsgUtil.NOPERM.getMessage());
				}
			}
			
			if (args[0].equalsIgnoreCase("setzone")) {
				if (sender.hasPermission("AttractionStatus.setzone")) {
					if (args.length < 3) {
						sender.sendMessage(Util.prefix + ChatColor.RED + Configs.getConfigs().getLang().getString("errors.wrong-command") + " /AttractionStatus setzone <Attractie Name> <Zone Name>");
						return false;
					} else {
						attractieDataFile = new File(plugin.getDataFolder() + File.separator + "Attractions" + File.separator, args[1].toString() + ".yml");
						File zoneDataFile = new File(plugin.getDataFolder() + File.separator + "Zones" + File.separator, args[2].toString() + ".yml");
						if (!attractieDataFile.exists()) {
							sender.sendMessage(Util.prefix + ChatColor.RED + "Attraction " + args[1].toString() + " " + Configs.getConfigs().getLang().getString("errors.dexist"));
							return false;
						} else {
							if (!zoneDataFile.exists()) {
								sender.sendMessage(Util.prefix + ChatColor.RED + "Zone " + args[2].toString() + " " + Configs.getConfigs().getLang().getString("errors.dexist"));
								return false;
						} else {
								attractieDataConfig = YamlConfiguration.loadConfiguration(attractieDataFile);
								attractieDataConfig.set("Zone", args[2].toString());
								util.saveFile(attractieDataConfig, attractieDataFile);
							
								YamlConfiguration zoneDataConfig = YamlConfiguration.loadConfiguration(zoneDataFile);
								//zoneDataConfig.set("Attracties." + args[2].toString() + ".Coordinates", attractieDataConfig.get("Coordinates.X") + "," + attractieDataConfig.get("Coordinates.Y") + "," + attractieDataConfig.get("Coordinates.Z"));
								zoneDataConfig.set("Attractions." + args[1].toString() + ".Coordinates", attractieDataConfig.getString("Coordinates"));
								util.saveFile(zoneDataConfig, zoneDataFile);
							
								sender.sendMessage(Util.prefix + ChatColor.GREEN + args[1].toString() + "'s " + Configs.getConfigs().getLang().getString("messages.zone-changed") + " " + args[2].toString());
								return false;
							}
						}
					}
				} else {
					sender.sendMessage(MsgUtil.NOPERM.getMessage());
					return false;
				}
			}
			
			if (args[0].equalsIgnoreCase("removezone")) {
				if (sender.hasPermission("AttractionStatus.removezone")) {
					if (args.length < 2) {
						sender.sendMessage(Util.prefix + ChatColor.RED + Configs.getConfigs().getLang().getString("errors.wrong-command") + " /AttractionStatus removezone <Zone Name>");
						return false;
					} else {
						File zoneDataFile = new File(plugin.getDataFolder() + File.separator + "Zones" + File.separator, args[1].toString() + ".yml");
						if (!zoneDataFile.exists()) {
							sender.sendMessage(Util.prefix + ChatColor.RED + args[1].toString() + " " + Configs.getConfigs().getLang().getString("errors.dexist"));
							return false;
						} else {
							zoneDataFile.delete();
							//util.saveFile(attractieDataConfig, attractieDataFile);
							sender.sendMessage(Util.prefix + ChatColor.GREEN + args[1].toString() + " " + Configs.getConfigs().getLang().getString("messages.succes-removed"));
							return false;
						}
					}
				} else {
					sender.sendMessage(MsgUtil.NOPERM.getMessage());
					return false;
				}
			}
			
			if (args[0].equalsIgnoreCase("tp")) {
				if (sender.hasPermission("AttractionStatus.tp")) {
					if (args.length < 2) {
						sender.sendMessage(Util.prefix + ChatColor.RED + Configs.getConfigs().getLang().getString("errors.wrong-command") + " /AttractionStatus tp <Attractie Name>");
						return false;
					} else {
						attractieDataFile = new File(plugin.getDataFolder() + File.separator + "Attractions" + File.separator, args[1].toString() + ".yml");
						if (!attractieDataFile.exists()) {
							sender.sendMessage(Util.prefix + ChatColor.RED + args[1].toString() + " " + Configs.getConfigs().getLang().getString("errors.dexist"));
							return false;
						} else {
							attractieDataConfig = YamlConfiguration.loadConfiguration(attractieDataFile);
							//player.teleport(new Location(player.getWorld(), attractieDataConfig.getDouble("Coordinates"), attractieDataConfig.getDouble("Coordinates.Y"), attractieDataConfig.getDouble("Coordinates.Z")));
							player.teleport(parseLoc(player, attractieDataConfig.getString("Coordinates")));
							//parseLoc(player, attractieDataConfig.getString("Coordinates"));
							sender.sendMessage(Util.prefix + ChatColor.YELLOW + Configs.getConfigs().getLang().getString("messages.succes-tp") +" " + args[1].toString() + "!");
							return false;
						}
					}
				}
			}
			
			File aFolder = new File(plugin.getDataFolder() + File.separator + "Attractions" + File.separator);
			File[] aList = aFolder.listFiles();
			
			if (args[0].equalsIgnoreCase("list")) {
				if (aList.length != 0) {
					sender.sendMessage(ChatColor.YELLOW + "-==========- " + ChatColor.GOLD + "Attraction List" + ChatColor.YELLOW + " -==========-");
					for (int i = 0; i < aList.length; i++) {
						if (aList[i].isFile()) {
							YamlConfiguration aConfig = YamlConfiguration.loadConfiguration(aList[i]);
							sender.sendMessage(/*ChatColor.YELLOW + "" + (i++ < aList[i].length()) + ". " +*/ ChatColor.GOLD + aList[i].getName().replace(".yml", "") + " " + ChatColor.GRAY + "- is standing in Zone: " + aConfig.getString("Zone"));
						}
					}
					sender.sendMessage(ChatColor.YELLOW + "-===================================-");
					return true;
				} else {
					sender.sendMessage(ChatColor.YELLOW + "-==========- " + ChatColor.GOLD + "Attraction List" + ChatColor.YELLOW + " -==========-");
					sender.sendMessage(ChatColor.GOLD + Configs.getConfigs().color(Configs.getConfigs().getLang().getString("commands.as-msg1")));
					sender.sendMessage(ChatColor.GRAY + Configs.getConfigs().color(Configs.getConfigs().getLang().getString("commands.as-msg2")));
					sender.sendMessage(ChatColor.YELLOW + "-===================================-");
					return false;
				}
			}
			
			if (args[0].equalsIgnoreCase("reload")) {
				
			}
		}
		return false;
		}
	}
}
