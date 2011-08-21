package metal.sude.permissions;

/* Bukkit */
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/* Sude */
import metal.sude.permissions.PermissionManager;
import metal.sude.permissions.PermissionDriver;


/**
 * Gives edit/admin powers to ops, list powers to everybody
 * Default driver
 * @author MetalMeerkat
 */
public class InternalStrict extends PermissionDriver{
	
	/**
	 * Initializes the permission driver
	 * @return True if successfully loaded, false otherwise
	 */
	public boolean initialize(){
		/* No internal state to manage, so just return success */
		return true;
	}
	
	/**
	 * Checks to see if a player has a permission
	 * @param player The player
	 * @param perm The requested permission
	 * @return Whether or not the player has the permission
	 */
	public boolean hasPermission(Player player, String perm){
		if (perm == null || perm.equals("")){
			/* Null permission, always allow */
			return true;
		}
		
		if (perm.equalsIgnoreCase(PermissionManager.listPerm)) {
			/* All player have listing permission */
			return true;
			
		} else if (perm.equalsIgnoreCase(PermissionManager.editPerm)){
			/* Only ops have editing permission */
			return player.isOp();
			
		} else if (perm.equalsIgnoreCase(PermissionManager.adminPerm)){
			/* Only ops have admin permission */
			return player.isOp();
		
		} else {
			/* ??? */
			return false;
		}
	}
}