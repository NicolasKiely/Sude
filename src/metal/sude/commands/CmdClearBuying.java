package metal.sude.commands;

/* Bukkit */
import org.bukkit.command.CommandSender;

/* Sude */
import metal.sude.commands.Commands;
import metal.sude.commands.SudeCommand;
import metal.sude.Sude;
import metal.sude.lists.Aliases;


/**
 * Clear Buying command
 * @author Metalmeerkat
 */
public class CmdClearBuying extends SudeCommand{
	
	/**
	 * Construct command
	 */
	public CmdClearBuying(){
		super("clear-buying","cb");
	}
	
	/**
	 * Returns whether or not the command needs help based on given
	 * arguments. Should be true if arguments are improper or
	 * arguments start of with "?"
	 * @param args Arguments to command
	 * @return Whether or not the command should display the help command
	 */
	public boolean needsHelp(String[] args){
		/* 1 or no arguments allowed */
		if (args.length == 0 || args.length == 1) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns null string if command is executable given the arguments
	 * Returns error message if unable to execute command
	 * @param args Arguments for command
	 * @return Error message, or null string if fine
	 */
	public String canExecute(CommandSender sender, String[] args){
		if (args.length == 0 || args.length == 1) {
			return null;
		} else {
			return "Too many arguments";
		}
	}
	
	
	/**
	 * Executes command with given arguments.
	 * @param originalSender The original sender of the commnand
	 * @param alias Who the command acts for
	 * @return True if succesful, false if there was an error
	 */
	public boolean execute(CommandSender originalSender,
			String alias, String[] args){
		
		/* Get item to delete */
		String item;
		
		if (args.length == 0) {
			/* Delete all items */
			item = "#all";
		} else {
			item = args[0];
		}
		
		/* Remove the item(s) */
		Sude.buyList.removeItem(alias, item);
		
		return true;
	}
}