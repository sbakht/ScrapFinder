import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SteamUser {

	private String username;
	
	public SteamUser(String username) {
		this.username = username;
	}
	
	public Inventory getInventory() {
		URL url;
		Hashtable<String, String> items = new Hashtable<String, String>();
		String itemClass = null;
		String item = null;
		
		try {
			// get URL content
			url = new URL("http://www.tf2items.com/id/" + username);
			URLConnection conn = url.openConnection();
 
			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(
                               new InputStreamReader(conn.getInputStream()));
			
			String inputLine;
			
			Pattern p = Pattern.compile("item[0-9]+");
			Pattern stopSearch = Pattern.compile("pageControls");
			
			while ((inputLine = br.readLine()) != null) {

				
				Matcher stop = stopSearch.matcher(inputLine);
				if(stop.find()) {
					break;
				}
				
				Matcher charClass = Pattern.compile("ubc_(.*)\"\\s").matcher(inputLine);
				Matcher multiCharClass = Pattern.compile("ubc_(.*)\\subc_").matcher(inputLine);
				//temporarily ignore items used by multiple classes
				if(charClass.find() && !multiCharClass.find()) {
//					System.out.println(charClass.group(1));
					itemClass = charClass.group(1);
				}
				
				Matcher m = p.matcher(inputLine);
				if(m.find()) {
					Matcher alt = Pattern.compile("alt=\"(.*)\"").matcher(inputLine);
					if(alt.find() && !alt.group(1).contains("jewel")) {
//						System.out.println(alt.group(1));
//						items.add(alt.group(1));
						item = alt.group(1);
						items.put(itemClass, item);
						System.out.println(itemClass);
						System.out.println(item);
					}
				}
			}
 
 
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Inventory inventory = new Inventory(items);
		return inventory;
	}
	
}
