package metal.sude.commands;

/* Bukkit */
import org.bukkit.command.CommandSender;

/* Sude */
import metal.sude.commands.Commands;
import metal.sude.lists.Aliases;


/**
 * Represents a sude command
 * @author Metalmeerkat
 */
public abstract class SudeCommand{
	
	/** Long name of the command */
	private String longName;
	
	/** Short name of the command */
	private String shortName;
	
	/** Permission needed for command */
	private String permission;
	
	/** Description of command */
	private String help;
	
	
	/**
	 * Creates a new command determinable by the long/short name
	 */
	public SudeCommand(String newLongName, String newShortName){
		setLongName(newLongName);
		setShortName(newShortName);
	}
	
	/**
	 * Returns name of command
	 * @return Name of command
	 */
	public String getLongName(){
		return longName;
	}
	
	/**
	 * Returns the full plugin name of a command
	 * @return Full name of command
	 */
	public String getFullName(){
		return Commands.longPrefix + "-" + getLongName();
	}
	
	/**
	 * Sets the long name
	 * @param newName New long name
	 */
	public void setLongName(String newName){
		longName = newName;
	}
	
	/**
	 * Returns short name of command
	 * @return Short name of command
	 */
	public String getShortName(){
		return shortName;
	}
	
	/**
	 * Sets the short name
	 * @param newName New short name
	 */
	public void setShortName(String newName){
		shortName = newName;
	}
	
	/**
	 * Returns permission of command
	 * @return Permission name
	 */
	public String getPermission(){
		return permission;
	}
	
	/**
	 * Sets the permission level
	 * @param newPerm New permission value
	 */
	public void setPermission(String newPerm){
		permission = newPerm;
	}
	
	/**
	 * Returns help message for command
	 * @return Help text
	 */
	public String getHelp(){
		return help;
	}
	
	/**
	 * Sets the command's description
	 * @param newHelp New help description
	 */
	public void setHelp(String newHelp){
		help = newHelp;
	}
	
	/**
	 * Returns whether or not the command needs help based on given
	 * arguments. Should be true if arguments are improper or
	 * arguments start of with "?"
	 * @param args Arguments to command
	 * @return Whether or not the command should display the help command
	 */
	public abstract boolean needsHelp(String[] args);
	
	/**
	 * Returns null string if command is executable given the arguments
	 * Returns error message if unable to execute command
	 * @param args Arguments for command
	 * @return Error message, or null string if fine
	 */
	public abstract String canExecute(CommandSender Sender, String[] args);
	
	/**
	 * Frontend execution of command call
	 */
	public boolean call(CommandSender sender, String[] args){
		/* Lookup alias */
		String alias = Aliases.lookup(sender);
		
		/* Execute backend */
		return execute(sender, alias, args);
	}
	
	/**
	 * Executes command with given arguments. Original sender gets feedback,
	 * but the command executes as if the alias called it.
	 * @param originalSender The original sender of the commnand
	 * @param alias Who the command acts for (original sender's sudo)
	 * @return True if succesful, false if there was an error
	 */
	public abstract boolean execute(CommandSender originalSender,
					String alias, String[] args);
}