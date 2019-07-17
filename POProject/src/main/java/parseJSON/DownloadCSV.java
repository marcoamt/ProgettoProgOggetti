package parseJSON;

import java.io.BufferedReader;
import java.io.File;
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

public class DownloadCSV {
	
	private String url;
	
	/**
	 * constructor method
	 * @param url is the link to the JSON file where ther's the link to download the file .csv
	 */
	public DownloadCSV(String url) {
		this.url=url;
	}
	
	/**
	 * method to find the .csv file and download it using @link down
	 * this method writes on console if the file exists or no
	 * @return path of the file saved on local
	 */
	public String scarica() {
    	String path="market-prices-dairy-products_en.csv";
		try {

			URLConnection openConnection = new URL(url).openConnection();
			openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			InputStream in = openConnection.getInputStream();
			
			 String data = "";
			 String line = "";
			 try {
			   InputStreamReader inR = new InputStreamReader( in );
			   BufferedReader buf = new BufferedReader( inR );
			  
			   while ( ( line = buf.readLine() ) != null ) {
				   data+= line;
				   System.out.println( line );
			   }
			 } finally {
			   in.close();
			 }
			JSONObject obj = (JSONObject) JSONValue.parseWithException(data); 
			JSONObject objI = (JSONObject) (obj.get("result"));
			JSONArray objA = (JSONArray) (objI.get("resources"));
			
			for(Object o: objA){
			    if ( o instanceof JSONObject ) {
			        JSONObject o1 = (JSONObject)o; 
			        String format = (String)o1.get("format");
			        String urlD = (String)o1.get("url");
			        System.out.println(format + " | " + urlD);
			        if(format.endsWith("/CSV")) {
			    		File f= new File(path);
			    		if(!f.exists()) { //if the file exists don't download it
			        	down(urlD, "market-prices-dairy-products_en.csv");
			        	System.out.println( "Download effettuato" );
			    		}
			    		else {
			    			System.out.println( "File esistente" );
			    		}
			        }
			    }
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}	catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}
	
	/**
	 * 
	 * @param url is the url of file
	 * @param fileName is the name of file
	 * @throws Exception
	 */
	
	public static void down(String url, String fileName) throws Exception {
	    try (InputStream in = URI.create(url).toURL().openStream()) {
	        Files.copy(in, Paths.get(fileName));
	    }
	}
	
}
