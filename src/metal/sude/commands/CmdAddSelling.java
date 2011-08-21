package metal.sude.commands;

/* Bukkit */
import org.bukkit.command.CommandSender;

/* Sude */
import metal.sude.Sude;
import metal.sude.commands.Commands;
import metal.sude.commands.SudeCommand;
import metal.sude.lists.Aliases;


/**
 * Add selling command
 * @author Metalmeerkat
 */
public class CmdAddSelling extends SudeCommand{
	
	/**
	 * Construct command
	 */
	public CmdAddSelling(){
		super("add-selling","as");
	}
	
	/**
	 * Returns whether or not the command needs help based on given
	 * arguments. Should be true if arguments are improper or
	 * arguments start of with "?"
	 * @param args Arguments to command
	 * @return Whether or not the command should display the help command
	 */
	public boolean needsHelp(String[] args){
		/* Permitted argument count: 2, 3, or 4*/
		if (args.length < 2 || args.length > 4) {
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
		int priceTest;
		int bundleTest;
		
		
		/* Make sure numerical arguments are, in fact, numerical */
		if (args.length >= 2) {
			/* args are: item, price */
			try {
				Integer dummy = new Integer(args[1]);
				priceTest = dummy.intValue();
				
			} catch (NumberFormatException blah){
				return "Price argument needs to be an integer";
			}
			
			if (priceTest <= 0){
				return "Price must be greater than zero";
			}
		} else {
			return "Too few arguments";
		}
		
		if (args.length >= 3) {
			/* Next argument: bundle size */
			try {
				Integer dummy = new Integer(args[2]);
				bundleTest = dummy.intValue();
				
			} catch (NumberFormatException moreBlah){
				return "Bundle argument needs to be an integer";
			}
			
			if (bundleTest <= 0) {
				return "Bundle argument must be greater than zero";
			}
		}
		
		if (args.length >= 4) {
			/* Last argument: max number */
			try {
				Integer dummy = new Integer(args[3]);
				
			} catch (NumberFormatException twentypercentmoreblah) {
				return "Max num argument needs to be an integer";
			}
		}
		
		if (args.length >= 5) {
			return "Too many arguments";
		}
		
		/* Nothing went horibly wrong */
		return null;
	}
	
	/**
	 * Executes command with given arguments.
	 * @param originalSender The original sender of the commnand
	 * @param alias Who the command acts for
	 * @return True if succesful, false if there was an error
	 */
	public boolean execute(CommandSender originalSender,
			String alias, String[] args){
		
		String item;
		String owner;
		int price;
		int bundle;
		int maxNum;
		
		/* Get player name */
		owner = alias;
		
		/* Get full arguments to add the entry */
		item = args[0];
		price = Integer.parseInt(args[1]);
		
		if (args.length >= 3) {
			bundle = Integer.parseInt(args[2]);
		} else {
			bundle = 1;
		}
		
		if (args.length == 4) {
			maxNum = Integer.parseInt(args[3]);
		} else {
			maxNum = -1;
		}
		
		/* Add entry */
		Sude.sellList.addEntry(owner, item, price, bundle, maxNum);
		
		return true;
	}
}