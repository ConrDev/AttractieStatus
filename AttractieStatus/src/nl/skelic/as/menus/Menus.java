package nl.skelic.as.menus;

import java.io.File;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import nl.skelic.as.Core;
import nl.skelic.as.config.Configs;

public class Menus {
	
	private static Core plugin;
	private static FileConfiguration attractieDataConfig;
	private static YamlConfiguration attractieConfig;
	private static File attractieDataFile;
	
	public  Menus(Core plugin) {
		plugin = plugin;
	}
	
	public static void attEditMenu(Player player) {
		Inventory inv = Bukkit.createInventory(null, Configs.getConfigs().getConfig().getInt("menu.size"), "§lZones");
		
		ItemStack borderIcon = new ItemStack(Material.STAINED_GLASS_PANE, 1,(byte) 15);
		ItemMeta borderMeta = borderIcon.getItemMeta();
		borderMeta.setDisplayName("");
		borderIcon.setItemMeta(borderMeta);
		
		ItemStack addIcon = new ItemStack(Material.BANNER, 1,(byte) 10);
		ItemMeta addMeta = addIcon.getItemMeta();
		addMeta.setDisplayName("§a§lADD");
		addIcon.setItemMeta(addMeta);
		
		ItemStack removeIcon = new ItemStack(Material.BANNER, 1,(byte) 1);
		ItemMeta removeMeta = removeIcon.getItemMeta();
		removeMeta.setDisplayName("§c§lREMOVE");
		removeIcon.setItemMeta(removeMeta);
		
		inv.setItem(Configs.getConfigs().getConfig().getInt("menu.size") - 1, addIcon);
		inv.setItem(Configs.getConfigs().getConfig().getInt("menu.size") - Configs.getConfigs().getConfig().getInt("menu.size") + 8, removeIcon);
		
		for(int i = 0; i < inv.getContents().length; i++) {
			ItemStack is = inv.getItem(i);
	        if((i >= 0 && i <= 8) || (i >= Configs.getConfigs().getConfig().getInt("menu.size") - 8 && i <= Configs.getConfigs().getConfig().getInt("menu.size") - 1) || (i % 9 == 0) || ((i-8) % 9 == 0)) {
	        	if (is == null || is.getType() == Material.AIR) {
	        	inv.setItem(i, borderIcon);
	        /*} else {
	            return;*/
	        	}
	        }
	    }
		
		player.openInventory(inv);
	}
	
	public static void attractiesMenu(Player player) {
		Inventory inv = Bukkit.createInventory(null, Configs.getConfigs().getConfig().getInt("menu.size"), "§lZones");
		
		ItemStack closeIcon = new ItemStack(Material.BARRIER, 1);
		ItemMeta closeMeta = closeIcon.getItemMeta();
		closeMeta.setDisplayName("§c§lCLOSE");
		closeIcon.setItemMeta(closeMeta);
		
		ItemStack borderIcon = new ItemStack(Material.STAINED_GLASS_PANE, 1,(byte) 15);
		ItemMeta borderMeta = borderIcon.getItemMeta();
		borderMeta.setDisplayName("");
		borderIcon.setItemMeta(borderMeta);
		
		
		/*for (int i = 0; i < aList.length; i++) {
			if (aList[i].isFile()) {
				ItemStack attractieIcon = new ItemStack(Material.SUGAR, 1);
				ItemMeta attractieMeta = attractieIcon.getItemMeta();
				attractieMeta.setDisplayName("§c§l" + aList[i].getName());
				//ArrayList<String> cokeLore = new ArrayList<String>();
				//cokeLore.add("§2€5.000§l§7/100g");
				//cokeMeta.setLore(cokeLore);
				attractieIcon.setItemMeta(attractieMeta);
				
				for (int ii = 11; ii < 7; ii++){
					inv.setItem(ii + 1, attractieIcon);
		        }
				return;
			}
			return;
		}*/
		
	    /*for (int i = 0; i < inv.getContents().length; i++) {
	        if ((i >= 0 && i <= 8) || (i >= 19 && i <= 27) || (i % 9 == 0) || ((i-8) % 9 == 0)) {
	            inv.setItem(i, borderIcon);
	        }
	    }*/
		
		for(int i = 0; i < inv.getContents().length; i++) {
	        if((i >= 0 && i <= 8) || (i >= Configs.getConfigs().getConfig().getInt("menu.size") - 8 && i <= Configs.getConfigs().getConfig().getInt("menu.size") - 1) || (i % 9 == 0) || ((i-8) % 9 == 0))
	            inv.setItem(i, borderIcon);
	    }
        
	   	player.openInventory(inv);
	}
	
	public static void zonesMenu(Player player) {
		Inventory inv = Bukkit.createInventory(null, 26, "§lZones");
	}
}
