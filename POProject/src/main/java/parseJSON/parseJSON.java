package parseJSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;


public class parseJSON {
	public static void main(String[] args) throws ParseException {
		String url = "https://ec.europa.eu/agriculture/sites/agriculture/files/markets-and-prices/price-monitoring/market-prices-dairy-products_en.csv";
		if(args.length == 1)
			url = args[0];
		try{
			URLConnection openConnection = new URL(url).openConnection();
			openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			InputStream in = openConnection.getInputStream();
			
			 String data = "";
			 String line = "";
			 try {
			   InputStreamReader inR = new InputStreamReader( in );
			   BufferedReader buf = new BufferedReader( inR );
			   while ( ( line = buf.readLine() ) != null ) {
				   
				   data+= line+"\n";
				   System.out.println( line );
			   }
			 } finally {
			   in.close();
			 }
			 Files.copy(in, Paths.get("market-prices-dairy-products_en.csv"));
			 //System.out.println(data);
			/*JSONObject obj = (JSONObject) JSONValue.parseWithException(data); 
			JSONObject objI = (JSONObject) (obj.get("results"));
			JSONArray objA = (JSONArray) (objI.get("resources"));
			
			for(Object o: objA){
			    if ( o instanceof JSONObject ) {
			        JSONObject o1 = (JSONObject)o; 
			        String format = (String)o1.get("format");
			        String urlD = (String)o1.get("url");
			        System.out.println(format + " | " + urlD);
			        if(format.equals("csv")) {
			        	download(urlD, "market-prices-dairy-products_en.csv");
			        }
			    }
			}*/
			System.out.println( "OK" );
		}catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void download(String url, String fileName) throws Exception {
	    try (InputStream in = URI.create(url).toURL().openStream()) {
	        Files.copy(in, Paths.get(fileName));
	    }
	}
}
