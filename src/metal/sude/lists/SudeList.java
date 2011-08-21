package metal.sude.lists;

/* Java */
import java.util.ArrayList;
import java.util.HashSet;


/**
 * Supply/Demand List
 * Manages a list of entrees in the form of:
 * ----- owner, item, price per bundle, bundle size, max number of bundles
 *
 * In hindsight, may have been a much better idea to implement with a database
 *
 * @author Metalmeerkat
 */
public class SudeList{
	
	/** List of entries */
	private ArrayList<Entry> entrees;
	
	/** Initializes list */
	public SudeList(){
		entrees = new ArrayList<Entry>();
	}
	
	/**
	 * Dumps all of the entries into a string array
	 * @return The data
	 */
	public String[] dumpData(){
		/* Format all of the entries. All of them */
		return formatListOfEntries(entrees);
	}
	
	
	/**
	 * Adds a new entry to the list, if it owner-item pair doesn't exist,
	 * otherwise updates it
	 */
	public void addEntry(String newOwner, String newItem, int newPrice,
			int newSize, int newMax){
	
		boolean ownerHasItem = false;
		
		/* Check to see if owner already has item */
		int i; for (i = 0; i < entrees.size(); i++){
			Entry entry = entrees.get(i);
			
			if (entry.owner.equalsIgnoreCase(newOwner) &&
					entry.item.equalsIgnoreCase(newItem)){
				
				/* Found owner-item pair, break off */
				ownerHasItem = true;
				break;
			}
		}
		
		/* Create new entry object */
		Entry temp = new Entry(newOwner, newItem, newPrice, newSize, newMax);
		
		if (ownerHasItem == true) {
			/* Update entry */
			entrees.set(i, temp);
		} else {
			/* Add new entry */
			entrees.add(temp);
		}
	}
	
	/**
	 * Gets the list of items owned by all players
	 * @return The list of items as strings
	 */
	public String[] getItems(){
		return getItems("#all");
	}
	
	
	/**
	 * Gets the list of the items owned by a player, or all players with #all
	 * @param owner Owner of item, or #all to indicate all players
	 * @return The list of items as strings
	 */
	public String[] getItems(String owner){
		/* Use a hash set so that duplicates are not added */
		HashSet<String> items = new HashSet<String>();
		
		for (int i = 0; i < entrees.size(); i++){
			Entry entry = entrees.get(i);
			
			if (entry.owner.equalsIgnoreCase(owner) ||
					owner.equalsIgnoreCase("#all")){
				/* Matches player lookup, so add item */
				items.add(entry.item);
			}
		}
		
		/* Convert to array */
		ArrayList<String> itemList = new ArrayList<String>(items);
		
		String[] itemArray = new String[itemList.size()];
		for (int i = 0; i < itemList.size(); i++){
			itemArray[i] = itemList.get(i);
		}
		
		return itemArray;
	}
	
	
	/**
	 * Removes an item owned by a player, or all if item=="#all"
	 * @param owner Owner of item
	 * @param item Item to be deleted
	 */
	public void removeItem(String owner, String item){
		for (int i = 0; i < entrees.size();){
			Entry entry = entrees.get(i);
			
			if (entry.owner.equalsIgnoreCase(owner)) {
				if (item.equalsIgnoreCase("#all")){
					/* Remove all player items */
					entrees.remove(i);
				} else if (item.equalsIgnoreCase(entry.item)){
					/* Remove this item */
					entrees.remove(i);
				} else {
					/* Ignore this entry, look ahead */
					i++;
				}
			}
		}
	}
	
	/**
	 * Gets the list of entrees containing item in ascending order
	 * @param item Name of the item
	 * @param ascending
	 * @return List of entrees in ascending order
	 */
	public String[] listItem(String item, boolean ascending){
		/* List of entrees */
		ArrayList<Entry> entryList = new ArrayList<Entry>();
		
		/* Search through all the entrees */
		for (int i = 0; i < entrees.size(); i++){
			Entry entry = entrees.get(i);
			
			/* Assert valid price ratio, in case a bad entry got in */
			if (entry.ratio() <= 0) {
				continue;
			}
			
			if (entry.item.equalsIgnoreCase(item)){
				/* Found new item entry, add it in order */
				
				/* Flag indicating whether or not entry has been added yet */
				boolean added = false;
				
				if (entryList.size() == 0){
					/* Empty list, just add away and continue */
					entryList.add(entry);
					continue;
				}
				
				/* Add in to list */
				for (int j = 0; j < entryList.size(); j++){
					if (ascending == true){
						/* Check ascending order */
						if (entry.ratio() < entryList.get(j).ratio()){
							/* Add element here */
							entryList.add(j, entry);
							added = true;
							break;
						}
						
					} else {
						/* Check descending order */
						if (entry.ratio() > entryList.get(j).ratio()){
							/* Add element here */
							entryList.add(j, entry);
							added = true;
							break;		
						}
					}	
				}
				
				if (added == false){
					/* Add to end of list */
					entryList.add(entry);
				}
			}
		}
		
		/* Now that the list of entrees has been compiled, format the output*/
		return formatListOfEntries(entryList);
	}
	
	
	/**
	 * Takes a List of entrees and formats them into strings
	 * Return format: <item name>, <owner>, <price per bundle>, <bundle size>,
	 * <max num> 
	 * @param entryList List of entrees
	 * @return Array of strings, one string for each entry
	 */
	private String[] formatListOfEntries(ArrayList<Entry> entryList){
		String[] output = new String[entryList.size()];
		
		for (int i = 0; i < entryList.size(); i++){
			/* For each field */
			Entry entry = entryList.get(i);
			
			output[i] = entry.item +", "+ entry.owner +", "+ entry.price +", "+
						entry.bundleSize;
			
			if (entry.maxNum > 0) {
				output[i] += ", " + entry.maxNum;
			}
		}
		
		return output;
	}
	
	
	/**
	 * Manages an entry for the list
	 * @author MetalMeerkat
	 */
	protected static class Entry{
		/** Default max number of bundles */
		public static int maxNumDefault = -1;
		
		/** Default bundle size */
		public static int sizeDefault = 1;
		
		/** Name of the owner */
		public String owner;
		
		/** Name of the item */
		public String item;
		
		/** Price per bundle */
		public int price;
		
		/** Size of a bundle */
		public int bundleSize;
		
		/** Maximum number of bundles */
		public int maxNum;
		
		/**
		 * Returns the price-per-bundle / bundle-size ratio (ie price per item)
		 * @return The price per individual item
		 */
		public float ratio(){
			if (bundleSize <= 0){
				return -1;
			} else {
				return ((float) price) / bundleSize;
			}
		}
		
		/** Full constructor of an entry */
		public Entry(String newOwner, String newItem, int newPrice,
				int newSize, int newMaxNum){
			
			owner = newOwner;
			item = newItem;
			price = newPrice;
			bundleSize = newSize;
			maxNum = newMaxNum;
		}
	}
}