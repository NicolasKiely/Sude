package metal.sude.commands;

/* Bukkit */
import org.bukkit.command.CommandSender;

/* Sude */
import metal.sude.commands.Commands;
import metal.sude.commands.SudeCommand;
import metal.sude.lists.Aliases;
import metal.sude.io.Talker;


/**
 * Sudo command
 * @author Metalmeerkat
 */
public class CmdSudo extends SudeCommand{
	
	/**
	 * Construct command
	 */
	public CmdSudo(){
		super("sudo","u");
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
		
		if (args.length == 0) {
			/* Report back the current alias */
			Talker.render(originalSender, alias);
			
		} else if (args[0].equals("#")){
			/* Remove alias */
			Aliases.reset(originalSender);
			
		} else {
			/* Set alias */
			Aliases.setAlias(originalSender, args[0]);
		}
		
		return true;
	}
}