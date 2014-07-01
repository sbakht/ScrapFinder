import java.util.Hashtable;


public class Main {

	public static void main(String args[]) {
		SteamUser user = new SteamUser("iwanthotdog");
		Inventory inventory = user.getInventory();
		Hashtable<String, Integer> duplicates;
		String[] charClasses = {"scout","pyro","demoman","soldier","heavy","sniper","spy","medic"};
		for(String character : charClasses) {
			duplicates = inventory.createScrapFromSameItem(character);
			inventory.createScrapFromSameClass(character,duplicates);
		}
		
	}
	
}
