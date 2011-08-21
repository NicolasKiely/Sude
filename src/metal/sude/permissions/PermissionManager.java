package metal.sude.permissions;

/* Bukkit */
import org.bukkit.util.config.Configuration;

/* Sude */
import metal.sude.permissions.PermissionDriver;
import metal.sude.permissions.ExternalPermissions;
import metal.sude.permissions.InternalStrict;
import metal.sude.permissions.InternalLax;
import metal.sude.permissions.InternalFree;
import metal.sude.io.Talker;
import metal.sude.Sude;


/**
 * Manages the permissions for the plugin
 * @author MetalMeerkat
 */
public class PermissionManager{
	/** Plugin drive for current permissions */
	private static PermissionDriver driver;
	
	/** Listing permission */
	public static String listPerm = "sude.list";
	/** Editing permission */
	public static String editPerm = "sude.edit";
	/** Admin permission */
	public static String adminPerm = "sude.admin";
	
	
	/**
	 * Get's the permission driver for the server
	 * @return The permission driver
	 */
	public static PermissionDriver getDriver(){
		return driver;
	}
	
	/**
	 * Initializes the plugin data
	 */
	public static void initialize(){
		/* Look up configuration for the driver to use */
		Configuration config = Sude.getConfig();
		
		
		String driverName = config.getString("permission_driver");
		
		if (driverName == null || driverName.equals("")){
			Talker.msgLog("Permissions driver not specified");
			
		} else if (driverName.equalsIgnoreCase("external-permissions")) {
			/* Load the external permissions driver */
			driver = new ExternalPermissions();
			
		} else if (driverName.equalsIgnoreCase("internal-strict")){
			/* Load the internal strict permissions */
			driver = new InternalStrict();
			
		} else if (driverName.equalsIgnoreCase("internal-lax")){
			/* Load the internal lax permissions */
			driver = new InternalLax();
			
		} else if (driverName.equalsIgnoreCase("internal-free")){
			/* Loads the internal free permissions */
			driver = new InternalFree();
			
		} else {
			Talker.msgLog("Permissions driver "+ driverName +" not found");
			
		}
		
		/* Load default driver */
		if (driver == null) {
			driverName = "default";
			driver = new InternalStrict();
		}
		
		
		if (driver.initialize() == false){
			/* Failed to load, use default driver */
			Talker.msgLog("Failed to load "+ driverName +" driver");
			driver = new InternalStrict();
			
		} else {
			Talker.msgLog("Loaded "+ driverName +" permissions driver");
			
		}
			
	}
	
}