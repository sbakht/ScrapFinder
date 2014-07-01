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
			if(duplicates.get(key) >= 3) {
				scrapable.add(key);
			}
		}
		return scrapable;
	}
	
	public Set<String> createScrapFromSameItem() {
		Set<String> scrapable = new HashSet<String>();
		Hashtable<String, Integer> duplicates = getDuplicatesHash();
		for(String key : duplicates.keySet()) {
			if(duplicates.get(key) >= 3) {
				duplicates.put(key, duplicates.get(key) - 2);
				System.out.println("Create scrap from " + key);
				scrapable.add(key);
			}
		}
		
		return scrapable;
	}
	
	public Hashtable<String, String> createScrapFromSameClass(String itemClass) {
		Hashtable<String, String> scrapable = new Hashtable<String, String>();
		Hashtable<String, Integer> duplicates = getDuplicatesHash(itemClass);
		int i = 0;
		String ingredient1 = "";
		String ingredient2 = "";
		
		for(String key : duplicates.keySet()) {
			if(i % 2 == 0) {
				ingredient1 = key;
			}else{
				ingredient2 = key;
				scrapable.put(ingredient1, ingredient2);
			}
			i++;
		}
		
		for(String key : scrapable.keySet()) {
			System.out.println(key + " and " + scrapable.get(key));
		}
		
		return scrapable;
	}
	
	public Hashtable<String, String> createScrapFromSameClass() {
		Hashtable<String, String> scrapable = new Hashtable<String, String>();
		for(String invKey : inventory.keySet()) {
			if(!invKey.equals("none") && !invKey.equals("all")) {
				Hashtable<String, Integer> duplicates = getDuplicatesHash(invKey);
				int i = 0;
				String ingredient1 = "";
				String ingredient2 = "";
				
				for(String key : duplicates.keySet()) {
					if(i % 2 == 0) {
						ingredient1 = key;
					}else{
						ingredient2 = key;
						scrapable.put(ingredient1, ingredient2);
					}
					i++;
				}
				
			}
		}
		
		for(String key : scrapable.keySet()) {
			System.out.println(key + " and " + scrapable.get(key));
		}
		
		return scrapable;
	}
	
	public Hashtable<String, Integer> getDuplicatesHash(String itemClass) {
		Hashtable<String, Integer> duplicates = new Hashtable<String, Integer>();
		ArrayList<String> items = inventory.get(itemClass);
		for(String item : items) {
			if(this.numOfItem(itemClass, item) > 1) {
				duplicates.put(item, this.numOfItem(itemClass, item));
			}
		}
	
//		for(String key : duplicates.keySet()) {
//			System.out.println(key + ' ' + duplicates.get(key));
//		}
		
		return duplicates;
	}
	
	public Hashtable<String, Integer> getDuplicatesHash() {
		Hashtable<String, Integer> duplicates = new Hashtable<String, Integer>();
		for(String invKey : inventory.keySet()) {
			if(!invKey.equals("none") && !invKey.equals("all")) {
				ArrayList<String> items = inventory.get(invKey);
				for(String item : items) {
					if(this.numOfItem(item) > 1) {
						duplicates.put(item, this.numOfItem(item));
					}
				}
				
			}
		}
		
		for(String key : duplicates.keySet()) {
			System.out.println(key + ' ' + duplicates.get(key));
		}
		return duplicates;
	}
	
}
