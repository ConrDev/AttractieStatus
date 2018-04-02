package nl.skelic.as.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.skelic.as.Core;
import nl.skelic.as.utils.MsgUtil;

public class AttractieStatusCMD implements CommandExecutor {
	
	@SuppressWarnings("unused")
	private Core plugin;
	
	public AttractieStatusCMD(Core plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(MsgUtil.NOTPLR.getMessage());
			return false;
		}
		
		if (args.length < 1) {
			sender.sendMessage(ChatColor.GREEN + "-========- AttractieStatus -========- ");
			sender.sendMessage(ChatColor.GREEN + "/AttractieStatus help - laat u alle commandos zien van AttractieStatus");
			sender.sendMessage(ChatColor.GREEN + "/AttractieStatus add <Attractie Naam> <Cords(X Y Z)> - voeg een Attractie met de status toe aan de lijst");
			sender.sendMessage(ChatColor.GREEN + "/AttractieStatus remove <Attractie Naam> - verwijder een Attractie van de lijst");
			sender.sendMessage(ChatColor.GREEN + "/AttractieStatus setstatus <Attractie Naam> <Status> - verander de Status van de Attractie");
			sender.sendMessage(ChatColor.GREEN + "/AttractieStatus addzone <Zone Naam> <Item ID> <Cords(X Y Z)> - hiermee verandert u de Status van de Attractie");
			sender.sendMessage(ChatColor.GREEN + "/AttractieStatus setzone <Attractie Naam> <Zone Naam> - hiermee verandert u de Status van de Attractie");
			sender.sendMessage(ChatColor.GREEN + "/AttractieStatus list - laat een lijst zien van alle Attracties");
			sender.sendMessage(ChatColor.GREEN + "-===================================- ");
			return false;
		} else {
			if (args[0].equalsIgnoreCase("help")) {
				sender.sendMessage(ChatColor.GREEN + "-========- AttractieStatus -========- ");
				sender.sendMessage(ChatColor.GREEN + "/AttractieStatus help - laat u alle commandos zien van AttractieStatus");
				sender.sendMessage(ChatColor.GREEN + "/AttractieStatus add <Attractie Naam> <Cords(X Y Z)> - voeg een Attractie met de status toe aan de lijst");
				sender.sendMessage(ChatColor.GREEN + "/AttractieStatus remove <Attractie Naam> - verwijder een Attractie van de lijst");
				sender.sendMessage(ChatColor.GREEN + "/AttractieStatus setstatus <Attractie Naam> <Status> - verander de Status van de Attractie");
				sender.sendMessage(ChatColor.GREEN + "/AttractieStatus addzone <Zone Naam> <Item ID> <Cords(X Y Z)> - hiermee verandert u de Status van de Attractie");
				sender.sendMessage(ChatColor.GREEN + "/AttractieStatus setzone <Attractie Naam> <Zone Naam> - hiermee verandert u de Status van de Attractie");
				sender.sendMessage(ChatColor.GREEN + "/AttractieStatus list - laat een lijst zien van alle Attracties");
				sender.sendMessage(ChatColor.GREEN + "-===================================- ");
				return false;
			}
				
			if (args[0].equalsIgnoreCase("add")) {
				if (sender.hasPermission("attractiestatus.add")) {
					if (args.length < 2) {
						sender.sendMessage(Core.prefix + ChatColor.RED + "Het commando is fout! U moet dit invoeren: /AttractieStatus add <Attractie Naam> <Cords(X Y Z)>");
						return false;
					} else {
						sender.sendMessage(Core.prefix + ChatColor.RED + "add");
						return false;
					}
				} else {
					sender.sendMessage(MsgUtil.NOPERM.toString());
					return false;
				}
			}
				
			if (args[0].equalsIgnoreCase("remove")) {
				if (sender.hasPermission("attractiestatus.remove")) {
					if (args.length > 1) {
						sender.sendMessage(Core.prefix + ChatColor.RED + "remove");
						return false;
					} else {
						sender.sendMessage(Core.prefix + ChatColor.RED + "Het commando is fout! U moet dit invoeren: /AttractieStatus remove <Attractie Naam>");
						return false;
					}
				} else {
					sender.sendMessage(MsgUtil.NOPERM.toString());
					return false;
				}
			}
				
			if (args[0].equalsIgnoreCase("setstatus")) {
				if (sender.hasPermission("attractiestatus.setstatus")) {
					if (args.length < 2) {
						sender.sendMessage(Core.prefix + ChatColor.RED + "Het commando is fout! U moet dit invoeren: /AttractieStatus setstatus <Attractie Naam> <Status>");
						return false;
					} else {
						sender.sendMessage(Core.prefix + ChatColor.RED + "setstatus");
						return false;
					}
				} else {
					sender.sendMessage(MsgUtil.NOPERM.toString());
					return false;
				}
			}
			
			if (args[0].equalsIgnoreCase("addzone")) {
				if (sender.hasPermission("attractiestatus.addzone")) {
					if (args.length < 3) {
						sender.sendMessage(Core.prefix + ChatColor.RED + "Het commando is fout! U moet dit invoeren: /AttractieStatus addzone <Zone Naam> <Item ID> <Cords(X Y Z)>");
					} else {
						sender.sendMessage(Core.prefix + ChatColor.RED + "addzone");
						return false;
					}
				} else {
					sender.sendMessage(MsgUtil.NOPERM.toString());
				}
			}
			
			if (args[0].equalsIgnoreCase("setzone")) {
				if (sender.hasPermission("attractiestatus.setzone")) {
					if (args.length < 2) {
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
			
			if (args[0].equalsIgnoreCase("list")) {
				sender.sendMessage(Core.prefix + ChatColor.RED + "list");
				return false;
			}
		}
		return false;
	}
}
