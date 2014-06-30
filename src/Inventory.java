import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;


public class Inventory {

	private Hashtable<String, ArrayList<String>> inventory = new Hashtable<String, ArrayList<String>>();
	
	public Inventory(Hashtable<String, ArrayList<String>> inventory) {
		this.inventory = inventory;
	}
	
	public int numOfItem(String item) {
		Collection<ArrayList<String>> values = inventory.values();
		for(ArrayList<String> value : values) {
			if(Collections.frequency(value, item) > 0) {
				return Collections.frequency(value, item);
			}
		}
		return 0;
	}
	
	public int numOfItem(String itemClass, String item) {
		ArrayList<String> items = inventory.get(itemClass);
		return Collections.frequency(items, item);
	}
	
	public Set<String> createScrapFromSameItem(String itemClass) {
		Set<String> scrapable = new HashSet<String>();
		Hashtable<String, Integer> duplicates = getDuplicatesHash(itemClass);
		for(String key : duplicates.keySet()) {
			if(duplicates.get(key) == 3) {
				scrapable.add(key);
			}
		}
		return scrapable;
	}
	
	public Hashtable<String, Integer> getDuplicatesHash(String itemClass) {
		Hashtable<String, Integer> duplicateItemsCount = new Hashtable<String, Integer>();
		ArrayList<String> items = inventory.get(itemClass);
		for(String item : items) {
			if(duplicateItemsCount.get(item) != null) {
				duplicateItemsCount.put(item, duplicateItemsCount.get(item) + 1);
			}else{
				duplicateItemsCount.put(item, 1);
			}
		}
		
		ArrayList<String> keysToRemove = new ArrayList<String>();
		for(String key : duplicateItemsCount.keySet()) {
			if(duplicateItemsCount.get(key) == 1) {
				keysToRemove.add(key);
			}
		}
		
		for(String key : keysToRemove) {
			duplicateItemsCount.remove(key);
		}
		
//		for(String key : duplicateItemsCount.keySet()) {
//			System.out.println(key + ' ' + duplicateItemsCount.get(key));
//		}
		
		return duplicateItemsCount;
	}
	
	public Hashtable<String, Integer> getDuplicatesHash() {
		Hashtable<String, Integer> duplicateItemsCount = new Hashtable<String, Integer>();
		for(String invKey : inventory.keySet()) {
			ArrayList<String> items = inventory.get(invKey);
			for(String item : items) {
				if(duplicateItemsCount.get(item) != null) {
					duplicateItemsCount.put(item, duplicateItemsCount.get(item) + 1);
				}else{
					duplicateItemsCount.put(item, 1);
				}
			}
			
			ArrayList<String> keysToRemove = new ArrayList<String>();
			for(String key : duplicateItemsCount.keySet()) {
				if(duplicateItemsCount.get(key) == 1) {
					keysToRemove.add(key);
				}
			}
			
			for(String key : keysToRemove) {
				duplicateItemsCount.remove(key);
			}
			
			for(String key : duplicateItemsCount.keySet()) {
				System.out.println(key + ' ' + duplicateItemsCount.get(key));
			}
		}
		return duplicateItemsCount;
	}
	
}
