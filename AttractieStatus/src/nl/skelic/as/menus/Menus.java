package nl.skelic.as.menus;

import java.io.File;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import nl.skelic.as.Core;

public class Menus {
	
	private static Core plugin;
	
	public  Menus(Core plugin) {
		this.plugin = plugin;
	}
	
	static File aFolder = new File(plugin.getDataFolder() + File.separator + "Attracties" + File.separator);
	static File[] aList = aFolder.listFiles();
	
	public static void attractiesMenu(Player player) {
		Inventory inv = Bukkit.createInventory(null, 27, "§lAttracties");
		
		ItemStack closeIcon = new ItemStack(Material.BARRIER, 1);
		ItemMeta closeMeta = closeIcon.getItemMeta();
		closeMeta.setDisplayName("§c§lCLOSE");
		closeIcon.setItemMeta(closeMeta);
		
		ItemStack borderIcon = new ItemStack(Material.STAINED_GLASS_PANE, 1,(byte) 15);
		ItemMeta borderMeta = borderIcon.getItemMeta();
		borderMeta.setDisplayName("");
		borderIcon.setItemMeta(borderMeta);
		
		inv.setItem(21, closeIcon);
		
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
		}
		
		for (int iii = 0; iii < 9; iii++){
			inv.setItem(iii, borderIcon);
			inv.setItem(iii + 18, borderIcon);
			return;
        }*/
		
		/*BukkitRunnable runnable = new BukkitRunnable() {
			@Override
	        public void run() {
				for(int i = 0; i < inv.getContents().length; i++) {
					ItemStack is = inv.getItem(i);
					if(is == null || is.getType() == Material.AIR || is.getType() == Material.STAINED_GLASS_PANE) {
						ItemStack attractieIcon = new ItemStack(Material.SUGAR, 1);
	       				ItemMeta attractieMeta = attractieIcon.getItemMeta();
	       				attractieMeta.setDisplayName("§c§l" + aList[i].getName());
	       				//ArrayList<String> cokeLore = new ArrayList<String>();
	       				//cokeLore.add("§2€5.000§l§7/100g");
	       				//cokeMeta.setLore(cokeLore);
	       				attractieIcon.setItemMeta(attractieMeta);
	       				
	       				inv.setItem(i, attractieIcon);
	                   	}
	               	}
	           	}
	       };
	     
	       Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, runnable, 0l, 10l);*/
		
	       player.openInventory(inv);
	}
	
	public static void zonesMenu(Player player) {
		Inventory inv = Bukkit.createInventory(null, 26, "§lZones");
	}
}
