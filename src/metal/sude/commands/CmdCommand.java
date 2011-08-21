package metal.sude.commands;

/* Bukkit */
import org.bukkit.command.CommandSender;

/* Sude */
import metal.sude.commands.Commands;
import metal.sude.commands.SudeCommand;

/* Java */
import java.util.List;
import java.util.ArrayList;


/**
 * List-commands command
 * @author Metalmeerkat
 */
public class CmdCommand extends SudeCommand{
	
	/**
	 * Construct command
	 */
	public CmdCommand(){
		super("command","cmd");
	}
	
	/**
	 * Returns whether or not the command needs help based on given
	 * arguments. Should be true if arguments are improper or
	 * arguments start of with "?"
	 * @param args Arguments to command
	 * @return Whether or not the command should display the help command
	 */
	public boolean needsHelp(String[] args){
		/* Always return list of commands, since this is a help
		 * function anyways */
		return false;
	}
	
	/**
	 * Returns null string if command is executable given the arguments
	 * Returns error message if unable to execute command
	 * @param args Arguments for command
	 * @return Error message, or null string if fine
	 */
	public String canExecute(CommandSender sender, String[] args){
		/* Go ahead and execute */
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
		
		List<SudeCommand> cmdList;
		
		/* Get a list of the commands */
		cmdList = Commands.getCommands();
		
		/* Get the names of the commands */
		String listString = "/" + Commands.longPrefix;
		for (int i = 0; i < cmdList.size(); i++){
			listString += ", /" + cmdList.get(i).getFullName();
		}
		
		/* Send message */
		originalSender.sendMessage(listString);
		
		return true;
	}
}