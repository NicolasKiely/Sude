package metal.sude.commands;

/* Bukkit */
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;

/* Sude */
import metal.sude.commands.SudeCommand;
import metal.sude.commands.Commands;
import metal.sude.permissions.PermissionManager;
import metal.sude.io.Talker;



/**
 * Handles taking in command strings and determining if they are valid. If so,
 * the command string is handled off to a command object to be executed.
 * @author Metalmeerkat
 */
public class CommandParser{
	/**
	 * Returns whether or not the command needs help.
	 * Commands need help if they have the argument '?' or
	 * are malformed
	 */
	public static boolean needsHelp(){
		return true;
	}
	
	/**
	 * Returns about string for plugin
	 * @return Plugin About string
	 */
	public static String SudeAbout(){
		return "Supply/Demand (Sude) plugin by MetalMeerkat. Type /<s"+
			"ude-command> ? for help on that command. Type /sude-c"+
			"md for list of commands.";
	}
	
	/**
	 * Entry function for the parser
	 * @return Whether or not the command is valid
	 */
	public static boolean process(CommandSender sender, String cmdName,
				      String [] args){
		
		/* Break up command string */
		String[] cmdNameBuff = cmdName.split("-");
		
		
		
		if (cmdNameBuff.length == 0){
			/* Improper command */
			return false;
		}
		
		if (!cmdNameBuff[0].equalsIgnoreCase(Commands.longPrefix) &&
		    !cmdNameBuff[0].equalsIgnoreCase(Commands.shortPrefix)){
			/* Not a Sude command */
			return false;
		}
		
		if (cmdNameBuff.length == 1) {
			/* Sude About command */
			sender.sendMessage(SudeAbout());
			return true;
		}
		
		/* Reconstruct command name without prefix */
		String commandName = cmdNameBuff[1];
		for (int i = 2; i < cmdNameBuff.length; i+= 1){
			commandName += "-" + cmdNameBuff[i];
		}
		
		
		/* Get the sude-command object */
		SudeCommand cmd = Commands.lookup(commandName);
		
		/* Make sure command exists */
		if (cmd == null) {
			return false;
		}
		
		/* Does the command need help? */
		if ((args.length > 0 && args[0].equals("?")) ||
		    cmd.needsHelp(args)){
		    	
		    	/* Display help and exit */
		    	/*sender.sendMessage(cmd.getHelp());*/
		    	Talker.render(sender, cmd.getHelp());
		    	return true;
		}
		
		/* Look up permissions */
		if (!PermissionManager.getDriver().hasPermission(
				sender, cmd.getPermission())){
			
			sender.sendMessage("Not enough permission for "+ cmd.getFullName());
			return true;
		}
		
		/* Attempt to execute command */
		String msg = cmd.canExecute(sender, args);
		if (msg == null){
			cmd.call(sender, args);
		} else {
			sender.sendMessage(msg);
		}
		
		return true;
		
	}
}