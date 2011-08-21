package metal.sude.io;

/* Bukkit */
import org.bukkit.util.config.Configuration;

/* Sude */
import metal.sude.Sude;


/**
 * Writes default data to plugin configuration file
 * @author Metalmeerkat
 */
public class DefaultConfigWriter{
	/** Writes the default data to the configuration file */
	public static void write(){
		/* Get configuration file */
		Configuration config = Sude.getConfig();
		
		/* Set plugin information  */
		config.setProperty("name", "Sude");
		config.setProperty("main", "metal.sude.Sude");
		config.setProperty("version", "0.1");
		
		/* Set usage */
		config.setProperty("commands.sude-buying.usage",
				   "/sd-b [item]");
		config.setProperty("commands.sude-selling.usage",
				   "/sd-s [item]");
		config.setProperty("commands.sude-player-buying.usage",
				   "/sd-pb [player]");
		config.setProperty("commands.sude-player-selling.usage",
				   "/sd-ps [player]");
		config.setProperty("commands.sude-add-buying.usage",
				   "/sd-ab <item> <price> [bundle] [max num]");
		config.setProperty("commands.sude-add-selling.usage",
				   "/sd-as <item> <price> [bundle] [max num]");
		config.setProperty("commands.sude-clear-buying.usage",
				   "/sd-cb [item]");
		config.setProperty("commands.sude-clear-selling.usage",
				   "/sd-cs [item]");
		config.setProperty("commands.sude-sudo.usage",
				   "/sd-u [player]");
		
		/* Set permissions */
		config.setProperty("commands.sude-buying.permission",
				   "sude.list");
		config.setProperty("commands.sude-selling.permission",
				   "sude.list");
		config.setProperty("commands.sude-player-buying.permission",
				   "sude.list");
		config.setProperty("commands.sude-player-selling.permission",
				   "sude.list");
		config.setProperty("commands.sude-add-buying.permission",
				   "sude.edit");
		config.setProperty("commands.sude-add-selling.permission",
				   "sude.edit");
		config.setProperty("commands.sude-clear-buying.permission",
				   "sude.edit");
		config.setProperty("commands.sude-clear-selling.permission",
				   "sude.edit");
		config.setProperty("commands.sude-sudo.permission",
				   "sude.admin");
		
		/* Set description */
		config.setProperty("commands.sude-buying.description",
				   "If item is given, lists buyers of item, so"+
				   "rted with highest bid first. If no item gi"+
				   "ven, displays list of buying items.");
		config.setProperty("commands.sude-selling.description",
				   "If item is given, lists sellers of item, s"+
				   "orted from lowest price first. If no item "+
				   "given, displays list of buying items.");
		config.setProperty("commands.sude-player-buying.description",
				   "Lists what a player is buying. If player i"+
				   "s omitted, shows what you are buying");
		config.setProperty("commands.sude-player-selling.description",
				   "Lists what a player is selling. If player "+
				   "is omitted, shows what you are selling");
		config.setProperty("commands.sude-add-buying.description",
				   "Adds you to list of buyers of item. Price "+
				   "is bid for a bundle (Default = 1) of item."+
				   " Max num refers to maximum items willing t"+
				   "o buy, -1 indicating no preference");
		config.setProperty("commands.sude-add-selling.description",
				   "Adds you to list of sellers of item. Price"+
				   " is bid for a bundle (Default = 1) of item"+
				   ". Max num refers to maximum items willing "+
				   "to sell, -1 indicating no preference");
		config.setProperty("commands.sude-clear-buying.description",
				   "Clears your buying entry for item. If no i"+
				   "tem specified, clears all your entries");
		config.setProperty("commands.sude-clear-selling.description",
				   "Clears your selling entry for item. If no "+
				   "item specified, clears all your entries");
		config.setProperty("commands.sude-sudo.description",
				   "The plugin treats you as another player. I"+
				   "f player not specified, returns who you're"+
				   " sudo'd as. Use '/sd-u #' to revoke back t"+
				   "o your original status.");
		
		/* Save new configuration */
		config.save();
	}
}