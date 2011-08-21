package metal.sude;

/* Java */
import java.io.IOException;

/* Bukkit */
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.util.config.Configuration;

/* Sude */
import metal.sude.permissions.PermissionManager;
import metal.sude.io.Disk;
import metal.sude.io.Talker;
import metal.sude.commands.CommandParser;
import metal.sude.commands.Commands;
import metal.sude.lists.Aliases;
import metal.sude.lists.SudeList;


/**
 * Entry for plugin, handles startup, shutdown, and passes commands to
 * command processor
 * @author MetalMeerkat
 */
public class Sude extends JavaPlugin {
	
	/** Configuration for the plugin */
	private static Configuration pluginConfiguration;
	
	/** Current plugin */
	private static Sude sude;
	
	/** Buy List */
	public static SudeList buyList;
	
	/** Sell List */
	public static SudeList sellList;
	
	/** Plugin name */
	public static String name = "sude";
	
	/**
	 * Called on plugin being disabled
	 */
	public void onDisable(){
		Talker.msgLog("Disabling");
		
		/* Save data */
		try {
			Disk.save(buyList, "buy.txt");
			Disk.save(sellList, "sell.txt");
		} catch (IOException bleh) {
			Talker.msgLog("Error in saving data");
		}
	}
	
	
	/**
	 * Called on plugin being enabled
	 */
	public void onEnable(){
		Talker.msgLog("Enabling");
		sude = this;
		pluginConfiguration = getConfiguration();
		
		/* Initialize plugin data */
		Commands.initialize();
		Aliases.initialize();
		PermissionManager.initialize();
		
		/* Load data */
		try {
			buyList = Disk.load("buy.txt");
			sellList = Disk.load("sell.txt");
		} catch (IOException blah) {
			Talker.msgLog("Cannot load data");
		}
	}
	
	
	/**
	 * Get the configuration the static way
	 */
	public static Configuration getConfig(){
		return pluginConfiguration;
	}
	
	/**
	 * Make the plugin data static
	 */
	public static Sude getSude(){
		return sude;
	}
	
	
	/**
	 * Passes on commands to be proccessed
	 * @param sender Sender of command
	 * @param cmd Command
	 * @param cmdLb Command name
	 * @param args Arguments for command
	 * @return Whether or not command is valid
	 */
	public boolean onCommand(CommandSender sender, Command cmd,
				 String cmdLb, String[] args){
		return CommandParser.process(sender, cmdLb, args);
	}
}