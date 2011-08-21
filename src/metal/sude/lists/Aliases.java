package metal.sude.lists;

/* Bukkit */
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/* Java */
import java.util.HashMap;

/**
 * Manages the lists of player aliases, a map from players to names
 * @author Metalmeerkat
 */
public class Aliases{
	/** Map of original command sender to alias */
	private static HashMap<CommandSender, String> aliases;
	
	
	/**
	 * Initialize aliases
	 */
	public static void initialize(){
		aliases = new HashMap<CommandSender, String>();
	}
	
	
	/**
	 * Sets alias to self
	 * @param CommandSender Sender to reset
	 */
	public static void reset(CommandSender original){
		setAlias(original, getName(original));
	}
	
	
	/**
	 * Sets the alias for the original sender
	 * @param original Current player
	 * @param alias New alias for player
	 */
	public static void setAlias(CommandSender original, String alias){
		/* Add mapping */
		aliases.put(original, alias);
	}
	
	
	/**
	 * Sets the alias for the original sender
	 * @param original Current player
	 * @param alias New alias for player
	 */
	public static void setAlias(CommandSender original, CommandSender alias){
		/* Add mapping */
		setAlias(original, getName(alias));
	}
	
	
	/**
	 * Looks up the alias for the original sender. Returns original if
	 * mapping to alias does not exist
	 * @param originalSender The original sender
	 * @return The alias
	 */
	public static String lookup(CommandSender originalSender){
		if (!aliases.containsKey(originalSender)){
			/* Mapping does not exist, add it and alias as self */
			reset(originalSender);
		}
		
		return aliases.get(originalSender);
	}
	
	
	/**
	 * Returns the lookup name for a command sender
	 * @param cmdSender The command sender
	 * @return The command sender's name
	 */
	public static String getName(CommandSender cmdSender){
		if (cmdSender instanceof ConsoleCommandSender) {
			return "#Console";
		} else if (cmdSender instanceof Player){
			return ((Player) cmdSender).getDisplayName();
		} else {
			/* Shouldn't ever happen */
			return "#Casper";
		}
	}
}