

public class Main {

	public static void main(String args[]) {
		SteamUser user = new SteamUser("iwanthotdog");
		Inventory inventory = user.getInventory();
		String[] charClasses = {"scout","pyro","demoman","soldier","heavy","sniper","spy","medic"};
		for(String character : charClasses) {
			inventory.getScrap(character);
			System.out.println("");
		}
	}
	
}
