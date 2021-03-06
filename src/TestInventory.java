import static org.junit.Assert.*;

import java.util.Hashtable;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;


public class TestInventory {
	
	private SteamUser user;
	private Inventory inventory;
	
	@Before
	public void setUp() throws Exception {
		user = new SteamUser("iwanthotdog");
		inventory = user.getInventory();
	}
	
	@Test
	public void numItems() {
		int numItemsWithClass = inventory.numOfItem("heavy", "Tomislav");
		int numItems = inventory.numOfItem("Tomislav");
		assertEquals("Num items with class defined", 1, numItemsWithClass);
		assertEquals("Num items", 1, numItems);
	}
	
	@Test
	public void TestDuplicateFinderByClass() {
		Hashtable<String, Integer> duplicates = inventory.getDuplicatesHash("heavy");
		int count = duplicates.get("Gloves of Running Urgently");
		assertEquals("Heavy wep duplicate", 3, count);
		assertEquals("Heavy wep duplicate", null, duplicates.get("Vintage Natascha"));
	}
	
	@Test
	public void scrapFromSameItemByClass() {
//		Set<String> scrapable = inventory.createScrapFromSameItem("heavy");
//		assertEquals("Scrap from gloves", true, scrapable.contains("Gloves of Running Urgently"));
	}
	
	@Test
	public void getDuplicatesFromAllClasses() {
		Hashtable<String, Integer> duplicates = inventory.getDuplicatesHash();
		int count = duplicates.get("Gloves of Running Urgently");
		assertEquals("Heavy wep duplicate", 3, count);
	}
	
	@Test
	public void scrapFromSameItemFromAllClasses() {
//		Set<String> scrapable = inventory.createScrapFromSameItem();
//		assertEquals("Scrap from gloves", true, scrapable.contains("Gloves of Running Urgently"));
	}
	
	@Test
	public void scrapFromSameClassFromAllClasses() {
		Hashtable<String, String> scrapable = inventory.createScrapFromSameClass();
		assertEquals("Scrap from gloves", false, scrapable.contains("Gloves of Running Urgently"));
	}
}
