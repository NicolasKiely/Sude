package metal.sude.permissions;

/* Bukkit */
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.Plugin;

/* Permissions */
import com.nijikokun.bukkit.Permissions.Permissions;
import com.nijiko.permissions.PermissionHandler;

/* Sude */
import metal.sude.Sude;
import metal.sude.permissions.PermissionManager;
import metal.sude.io.Talker;


/**
 * Works with a standard permissions plugin
 * @author Metalmeerkat
 */
public class ExternalPermissions extends PermissionDriver{
	
	/** Handler for the permission plugin */
	private PermissionHandler permHdl = null;
	
	/**
	 * Initializes the permission driver
	 */
	public boolean initialize(){
		/* Load permissions plugin */
		Plugin plug = null;
		String name = "Permissions";
		plug = Sude.getSude().getServer().getPluginManager().getPlugin(name);
		
		if (plug == null) {
			/* Failed to load plugin */
			Talker.msgLog("Unable to hook into external permissions");
			return false;
		}
		
		permHdl = ((Permissions) plug).getHandler();
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
		
		return permHdl.has(player, perm);
	}
}