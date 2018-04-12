package nl.skelic.as.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import nl.skelic.as.Core;
import nl.skelic.as.config.Configs;

public class TpUtil {
	
	private Core plugin;
	
	private static TpUtil tpU;
	
	public TpUtil(Core pluginInstance) {
		plugin = pluginInstance;
	}
	
	public Location parseLoc(Player player, String str){
	    String str2loc[]=str.split(",");
	    Location loc = new Location(player.getWorld(),0,0,0);
	    loc.setX(Double.parseDouble(str2loc[0]));
	    loc.setY(Double.parseDouble(str2loc[1]));
	    loc.setZ(Double.parseDouble(str2loc[2]));
	    return loc;
	}
	
	public static TpUtil tp() {
		return tpU;
	}
	
}
