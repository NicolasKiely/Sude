package metal.sude.permissions;

/* Bukkit */
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;


/**
 * Represents a working permissions driver
 * @author MetalMeerkat
 */
public abstract class PermissionDriver{
	
	/**
	 * Initializes the permission driver
	 * @return True if successfully loaded, false otherwise
	 */
	public abstract boolean initialize();
	
	/**
	 * Checks to see if a player has a permission
	 * @param player The player
	 * @param perm The requested permission
	 * @return Whether or not the player has the permission
	 */
	public abstract boolean hasPermission(Player player, String perm);
	
	
	/**
	 * Checks to see if the command sender has a permission. Console is
	 * always assumed to have proper permission
	 * @param cmdSdr The Command Sender
	 * @param perm The permission
	 * @return Whether or not the player has the permission
	 */
	public boolean hasPermission(CommandSender cmdSdr, String perm){
		if (cmdSdr instanceof ConsoleCommandSender) {
			/* Obey thy console */
			return true;
		} else if (cmdSdr instanceof Player) {
			/* Look up permission for the player */
			return hasPermission((Player) cmdSdr, perm);
		} else {
			/* ??? */
			return false;
		}
	}
}