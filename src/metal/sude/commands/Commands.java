package metal.sude.commands;

/* Bukkit */
import org.bukkit.util.config.Configuration;

/* Sude */
import metal.sude.commands.*;
import metal.sude.Sude;
import metal.sude.io.Talker;
import metal.sude.io.DefaultConfigWriter;

/* Java */
import java.util.List;
import java.util.ArrayList;


/**
 * Front-end to the list of commands
 * @author Metalmeerkat
 */
public class Commands {
	
	/** Long prefix name of all commands */
	public static String longPrefix = Sude.name;
	
	/** Short prefix name of all commands */
	public static String shortPrefix = "sd";
	
	/** List of commands */
	private static List<SudeCommand> cmds;
	
	
	/**
	 * Sets up list of commands
	 */
	public static void initialize(){
		/* Creates array list */
		cmds = new ArrayList<SudeCommand>();
		
		/* Load up plugin configuration data */
		Configuration config = Sude.getConfig();
		
		String name = config.getString("name");
		
		if (name == null) {
			/* Oops, no configuration file. Remake it */
			Talker.msgLog("Config not found, regenerating");
			
			DefaultConfigWriter.write();
		}
		
		/* Add commands */
		cmds.add(new CmdCommand());
		cmds.add(new CmdBuying());
		cmds.add(new CmdSelling());
		cmds.add(new CmdPlayerBuying());
		cmds.add(new CmdPlayerSelling());
		cmds.add(new CmdAddBuying());
		cmds.add(new CmdAddSelling());
		cmds.add(new CmdClearBuying());
		cmds.add(new CmdClearSelling());
		cmds.add(new CmdSudo());
		
		
		/* Add data for each command */
		for (int i = 0; i < cmds.size(); i++){
			/* Load up permission data */
			SudeCommand cmd = cmds.get(i);
			
			/* Path to command in config file */
			String cmdPath = "commands." + cmd.getFullName();
			
			/* Get permission data */
			String perm = config.getString(cmdPath +".permission");
			cmd.setPermission(perm);
			
			/* Get help data */
			String usage = config.getString(cmdPath +".usage");
			String desc = config.getString(cmdPath +".description");
			String help = "Permission: " + perm + "\n" +
				      "Usage: " + usage + "\n" + 
				      "Description: " + desc;
			cmd.setHelp(help);
				      
		}
		
		
	}
	
	
	/**
	 * Gets the long prefix name
	 * @return Long prefix name
	 */
	public static String getLongPrefix(){
		return longPrefix;
	}
	
	
	/**
	 * Gets the short prefix name
	 * @return Short prefix name
	 */
	 public static String getShortPrefix(){
	 	return shortPrefix;
	 }
	 
	 
	 /**
	  * Looks up a command object with a command name
	  * @param cmdName Name of command, short or long
	  * @return Command object
	  */
	 public static SudeCommand lookup(String cmdName){
	 	for (int i = 0; i < cmds.size(); i++){
	 		SudeCommand cmd = cmds.get(i);
	 		if (cmd.getLongName().equalsIgnoreCase(cmdName) ||
	 		    cmd.getShortName().equalsIgnoreCase(cmdName)){
	 		    	
	 		    	/* Found a match */
	 		    	return cmd;
	 		}
	 	}
	 	
	 	/* Found nothing */
	 	return null;
	 }
	 
	 
	 /**
	  * Returns an array of the commands
	  * @return Array of commands
	  */
	 public static List<SudeCommand> getCommands(){
	 	return cmds;
	 }
}