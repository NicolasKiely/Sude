package metal.sude.io;

/* Java */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/* Sude */
import metal.sude.Sude;
import metal.sude.lists.SudeList;
import metal.sude.io.Talker;


/**
 * Saves/Loads plugin data to disk
 * @author MetalMeerkat
 */
public class Disk{
	
	/**
	 * Saves a list's data to a file in the data directory
	 * @param dataList List of data to save
	 * @param filename Name of file to save data
	 */
	public static void save(SudeList dataList, String fileName)
			throws IOException{
		
		File saveFile = new File(Sude.getSude().getDataFolder(), fileName);
		FileWriter out = null;
		
		try {
			/* Open file */
			out = new FileWriter(saveFile);
			
			String[] dataArray = dataList.dumpData();
			
			/* Write data */
			for (String dataLine : dataArray){
				out.write(dataLine + "\n");
			}
			
		} finally {
			if (out != null) {
				out.close();
			}
		}	
	}
	
	
	/**
	 * Loads a list's data from a file in the data directory
	 * Loaded record should be in form of:
	 * 		item, owner, price, bundle size, [max num]
	 * @param filename Name of file to load data
	 * @return List of data from file
	 */
	public static SudeList load(String fileName) throws IOException{
		File loadFile = new File(Sude.getSude().getDataFolder(), fileName);
		SudeList dataList = new SudeList();
		Scanner in = null;
		
		try {
			/* Prep file */
			in = new Scanner(loadFile);
			in.useDelimiter("\n+");
			
			/* Read the data line by line from the file */
			while (in.hasNext()){
				/* Parse line in to array buffer */
				String lineText = in.next();
				Scanner line = new Scanner(lineText);
				line.useDelimiter(",\\s*");
				
				String[] buffer = new String[5];
				
				int i;
				for (i = 0; ((i < 5) && line.hasNext()); i++) {
					buffer[i] = line.next();
				}
				
				if (i < 4) {
					/* Not enough information on this line, junk it */
					Talker.msgLog("Invalid record in "+fileName+": " +
							lineText);
					continue;
				} else if (i == 4) {
					/* Missing max num, set to default */
					buffer[4] = "-1";
				} else if (i > 5) {
					/* Too many records, junk it */
					Talker.msgLog("Invalid record in "+fileName+": " +
							lineText);
					continue;
				}
				
				/* Now get entry variables from array */
				String owner; String item; int price; int size; int max;
				
				owner = buffer[1];
				item = buffer[0];
				
				/* Attempt to cast to integer */
				try {
					price = (new Integer(buffer[2])).intValue();
					size = (new Integer(buffer[3])).intValue();
					max = (new Integer(buffer[4])).intValue();
				} catch (NumberFormatException eh) {
					Talker.msgLog("Invalid record in "+fileName+": " +
							lineText);
					continue;
				}
				
				/* Now add data to the list */
				dataList.addEntry(owner, item, price, size, max);
				
			}
			
		} finally {
			if (in != null) {
				in.close();
			}
			
			return dataList;
		}
		
		
	}
	
	
}