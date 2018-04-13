package nl.skelic.as.events;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import nl.skelic.as.Core;
import nl.skelic.as.config.Configs;
import nl.skelic.as.utils.Util;

public class SignEvent implements Listener {
	
	private Core plugin;
	private Configs cfg;
	public FileConfiguration attractieDataConfig;
	public YamlConfiguration attractieConfig;
	public File attractieDataFile;
	
	public SignEvent(Core plugin) {
		this.plugin = plugin;
		cfg = Configs.getConfigs();
	}
	
	public Location parseLoc(Player player, String str){
	    String str2loc[]=str.split(",");
	    Location loc = new Location(player.getWorld(),0,0,0);
	    loc.setX(Double.parseDouble(str2loc[0]));
	    loc.setY(Double.parseDouble(str2loc[1]));
	    loc.setZ(Double.parseDouble(str2loc[2]));
	    return loc;
	}
	
	public static String color(String message) {
		return message.replace("&", "§");
	}
	
	private static final String prefix = color(Configs.getConfigs().getConfig().get("Prefix")+ "").toString();
	
	@EventHandler
	public void attractieSign(SignChangeEvent sign) {
		attractieDataFile = new File(plugin.getDataFolder() + File.separator + "Attractions" + File.separator, sign.getLine(1).toString() + ".yml");
		attractieDataConfig = YamlConfiguration.loadConfiguration(attractieDataFile);
		Player player = sign.getPlayer();
		if (sign.getLine(0).equalsIgnoreCase("[AS]")) {
			if (attractieDataFile.exists()) {
				sign.setLine(0, prefix);
				sign.setLine(1, sign.getLine(1));
				sign.setLine(2, cfg.color(attractieDataConfig.getString("Status")));
				player.sendMessage(Util.prefix + ChatColor.GREEN + Configs.getConfigs().getLang().getString("messages.asigns.created"));
			} else {
				sign.setLine(0, prefix);
				sign.setLine(1, ChatColor.RED + "No Attraction");
				player.sendMessage(Util.prefix + ChatColor.RED + Configs.getConfigs().getLang().getString("messages.attraction.not-found"));
			}
		}
	}
	
	@EventHandler
	public void tpSign(PlayerInteractEvent PIE) {
		if ((PIE.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			if (PIE.getClickedBlock().getState() instanceof Sign) {
				Player player = PIE.getPlayer();
				Sign sign = (Sign) PIE.getClickedBlock().getState();
				attractieDataFile = new File(plugin.getDataFolder() + File.separator + "Attractions" + File.separator, sign.getLine(1).toString() + ".yml");
				attractieDataConfig = YamlConfiguration.loadConfiguration(attractieDataFile);
			
				if (sign.getLine(0).equalsIgnoreCase(prefix)) {
					if (attractieDataFile.exists()) {
						player.teleport(parseLoc(player, attractieDataConfig.getString("Coordinates")));
						player.sendMessage(Util.prefix + ChatColor.GREEN + Configs.getConfigs().getLang().getString("messages.succes-tp") + " " + sign.getLine(1).toString());
					} else {
						player.sendMessage(Util.prefix + ChatColor.RED + Configs.getConfigs().getLang().getString("messages.attraction.not-found"));
					}
				}
			}
		} else if ((PIE.getAction() == Action.LEFT_CLICK_BLOCK)) {
			if (PIE.getClickedBlock().getState() instanceof Sign) {
				Player player = PIE.getPlayer();
				Sign sign = (Sign) PIE.getClickedBlock().getState();
				
				if (sign.getLine(0).equalsIgnoreCase(prefix)) {
					player.sendMessage(Util.prefix + ChatColor.GREEN + Configs.getConfigs().getLang().getString("messages.asigns.removed"));
				}
			}
		} else {
			return;
		}
	}
	
}
