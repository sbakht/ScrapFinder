import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SteamUser {

	public static void getInventory(String id) {
		URL url;
		 
		try {
			// get URL content
			url = new URL("http://www.tf2items.com/id/" + id);
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
				
				Matcher m = p.matcher(inputLine);
				if(m.find()) {
					Matcher alt = Pattern.compile("alt=\"(.*)\"").matcher(inputLine);
					if(alt.find()) {
						System.out.println(alt.group(1));
					}
				}
			}
 
 
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		getInventory("iwanthotdog");

 
	}
	
}
