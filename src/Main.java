
public class Main {

	public static void main(String args[]) {
		SteamUser user = new SteamUser("iwanthotdog");
		Inventory inventory = user.getInventory();
		int occurrences = inventory.numOfItem("Tomislav");
		System.out.println(occurrences);
		int o = inventory.numOfItem("heavy", "Tomislav");
		System.out.println(o);
		
	}
	
}
