
public class Main {

	public static void main(String args[]) {
		SteamUser user = new SteamUser("iwanthotdog");
		Inventory inventory = user.getInventory();
		inventory.createScrapFromSameItem();
		inventory.createScrapFromSameClass();
		
	}
	
}
