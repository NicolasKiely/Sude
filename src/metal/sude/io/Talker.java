package metal.sude.io;

/* Bukkit */
import org.bukkit.command.CommandSender;

/* Java */
import java.util.logging.Logger;

/**
 * Handles communication between players and logs
 * @author Metalmeerkat
 */
public class Talker{
	/** Hooks up to minecraft logger */
	private static Logger log = Logger.getLogger("Minecraft");
	
	/** Sends a message to the log */
	public static void msgLog(String msg){
		log.info("[sude] " + msg);
	}
	
	/** Sends a message to a command-sender, delimited by newlines */
	public static void render(CommandSender sender, String msg){
			/* Break up according to newlines */
			String[] msgs = msg.split("\n");
			for (String eachMsg : msgs){
				sender.sendMessage(eachMsg);
			}
	}
	
	/**
	 * Sends a message to a command-sender
	 * @param sender The message receiver
	 * @param msgs Messages
	 * @param del Delimiter for each message
	 */
	public static void render(CommandSender sender, String[] msgs, String del){
		String msg;
		
		/* Make into a newline-delimitted string */
		if (msgs.length >= 1) {
			msg = msgs[0];
		} else {
			return;
		}
		
		for (int i = 1; i < msgs.length; i++){
			msg += del + msgs[i];
		}
		
		render(sender, msg);
	}
}