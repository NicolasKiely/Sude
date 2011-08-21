package metal.sude.commands;

/* Bukkit */
import org.bukkit.command.CommandSender;

/* Sude */
import metal.sude.Sude;
import metal.sude.commands.Commands;
import metal.sude.commands.SudeCommand;
import metal.sude.lists.SudeList;
import metal.sude.io.Talker;


/**
 * Buying command
 * @author Metalmeerkat
 */
public class CmdBuying extends SudeCommand{
	
	/**
	 * Construct command
	 */
	public CmdBuying(){
		super("buying","b");
	}
	
	/**
	 * Returns whether or not the command needs help based on given
	 * arguments. Should be true if arguments are improper or
	 * arguments start of with "?"
	 * @param args Arguments to command
	 * @return Whether or not the command should display the help command
	 */
	public boolean needsHelp(String[] args){
		/* Can only have 1 or 0 arguments */
		if (!(args.length == 0 || args.length == 1)){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Returns null string if command is executable given the arguments
	 * Returns error message if unable to execute command
	 * @param args Arguments for command
	 * @return Error message, or null string if fine
	 */
	public String canExecute(CommandSender sender, String[] args){
		if (args.length == 0) {
			/* No arguments, so nothing wrong here */
			return null;
			
		} else if (args.length == 1) {
			/* Parameter is assumed to be an item */
			return null;
			
		} else {
			/* Invalid number of arguments */
			return "Invalid number of arguments";
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

		if (args.length == 0){
			/* Show the list of items being bought */
			String[] itemList = Sude.buyList.getItems();
			
			String itemString = "Items: ";
			for (String item : itemList){
				itemString += " " + item;
			}
			
			Talker.render(originalSender, itemString);
			return true;
		} else if (args.length == 1) {
			/* Show the list of buyers for the item */
			String[] itemList = Sude.buyList.listItem(args[0], true);
			
			String itemString = "Buyers for: " + args[0];
			for (String item : itemList){
				itemString += "\n" + item;
			}
			
			Talker.render(originalSender, itemString);
			return true;
		}
		
		return false;
	}
}