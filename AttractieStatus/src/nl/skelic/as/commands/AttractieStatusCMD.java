package nl.skelic.as.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.skelic.as.Core;
import nl.skelic.as.utils.MsgUtil;

public class AttractieStatusCMD implements CommandExecutor {
	
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
		
		if (command.getName().equalsIgnoreCase("attractiestatus")) {
			if (args.length != 0) {
				
				if (args[0].equalsIgnoreCase("help")) {
					/*if (sender.hasPermission("attractiestatus.help")) {
						
					}*/
					sender.sendMessage(ChatColor.GREEN + "-_-_-_-_-_- AttractieStatus -_-_-_-_- ");
					sender.sendMessage(ChatColor.GREEN + "/AttractieStatus help - laat u alle commandos zien van AttractieStatus");
					sender.sendMessage(ChatColor.GREEN + "/AttractieStatus add <Attractie Naam> <Cords(X,Y,Z)> - voeg een Attractie met de status toe aan de lijst");
					sender.sendMessage(ChatColor.GREEN + "/AttractieStatus remove <Attractie Naam> - verwijder een Attractie van de lijst");
					sender.sendMessage(ChatColor.GREEN + "/AttractieStatus setstatus <Attractie Naam> <Status> - verander de Status van de Attractie");
					sender.sendMessage(ChatColor.GREEN + "/AttractieStatus addzone <Zone Naam> <Item ID> - hiermee verandert u de Status van de Attractie");
					sender.sendMessage(ChatColor.GREEN + "/AttractieStatus setzone <Attractie Naam> <Zone Naam> - hiermee verandert u de Status van de Attractie");
					sender.sendMessage(ChatColor.GREEN + "/AttractieStatus list - laat een lijst zien van alle Attracties");
					sender.sendMessage(ChatColor.GREEN + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_- ");
					return false;
				}
				
				if (args[0].equalsIgnoreCase("add")) {
					if (args[1].equals(null)) {
						sender.sendMessage(Core.prefix + ChatColor.RED + "Het command is fout! U moet dit invoeren: /AttractieStatus add <Attractie Naam>");
					} else {
						sender.sendMessage(Core.prefix + ChatColor.RED + "add");
						return false;
					}
				}
				
				if (args[0].equalsIgnoreCase("remove")) {
					if (args[1].equals(null)) {
						sender.sendMessage(Core.prefix + ChatColor.RED + "Het command is fout! U moet dit invoeren: /AttractieStatus remove <Attractie Naam>");
					} else {
						sender.sendMessage(Core.prefix + ChatColor.RED + "remove");
						return false;
					}
				}
				
				if (args[0].equalsIgnoreCase("setstatus")) {
					if (args[1].equals(null)) {
						sender.sendMessage(Core.prefix + ChatColor.RED + "Het command is fout! U moet dit invoeren: /AttractieStatus setstatus <Attractie Naam> <Status>");
					} else {
						sender.sendMessage(Core.prefix + ChatColor.RED + "setstatus");
						return false;
					}
				}
				
				if (args[0].equalsIgnoreCase("addzone")) {
					if (args[1].equals(null)) {
						sender.sendMessage(Core.prefix + ChatColor.RED + "Het command is fout! U moet dit invoeren: /AttractieStatus addzone <Zone Naam> <Item ID>");
					} else {
						sender.sendMessage(Core.prefix + ChatColor.RED + "addzone");
						return false;
					}
				}
				
				if (args[0].equalsIgnoreCase("setzone")) {
					if (args[1].equals(null)) {
						sender.sendMessage(Core.prefix + ChatColor.RED + "Het command is fout! U moet dit invoeren: /AttractieStatus setzone <Attractie Naam> <Zone Naam>");
					} else {
						sender.sendMessage(Core.prefix + ChatColor.RED + "setzone");
						return false;
					}
				}
				
				if (args[0].equalsIgnoreCase("list")) {
					sender.sendMessage(Core.prefix + ChatColor.RED + "list");
					return false;
				}
			}
		} else {
			sender.sendMessage(ChatColor.GREEN + "-_-_-_-_-_- AttractieStatus -_-_-_-_- ");
			sender.sendMessage(ChatColor.GREEN + "/AttractieStatus help - laat u alle commandos zien van AttractieStatus");
			sender.sendMessage(ChatColor.GREEN + "/AttractieStatus add <Attractie Naam> <Cords(X,Y,Z)> - voeg een Attractie met de status toe aan de lijst");
			sender.sendMessage(ChatColor.GREEN + "/AttractieStatus remove <Attractie Naam> - verwijder een Attractie van de lijst");
			sender.sendMessage(ChatColor.GREEN + "/AttractieStatus setstatus <Attractie Naam> <Status> - verander de Status van de Attractie");
			sender.sendMessage(ChatColor.GREEN + "/AttractieStatus addzone <Zone Naam> <Item ID> - hiermee verandert u de Status van de Attractie");
			sender.sendMessage(ChatColor.GREEN + "/AttractieStatus setzone <Attractie Naam> <Zone Naam> - hiermee verandert u de Status van de Attractie");
			sender.sendMessage(ChatColor.GREEN + "/AttractieStatus list - laat een lijst zien van alle Attracties");
			sender.sendMessage(ChatColor.GREEN + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_- ");
			return false;
		}
	return false;
	}
}
