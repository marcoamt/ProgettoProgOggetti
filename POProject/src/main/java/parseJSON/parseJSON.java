package parseJSON;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;



public class parseJSON {
	public static void main(String[] args) {
		String url = "https://ec.europa.eu/agriculture/sites/agriculture/files/markets-and-prices/price-monitoring/market-prices-dairy-products_en.csv";
		if(args.length == 1)
			url = args[0];
		try{
			URLConnection openConnection = new URL(url).openConnection();
			openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			InputStream in = openConnection.getInputStream();
			
			 String data = "";
			 String line = "";
			 FileWriter f=new FileWriter("market-prices-dairy-products_en.csv");
			 try {
			   InputStreamReader inR = new InputStreamReader( in );
			   BufferedReader buf = new BufferedReader( inR );
			   while ( ( line = buf.readLine() ) != null ) {
				   
				   data+= line+"\n";
				   System.out.println( line );
				   f.write(line+"\n");
			   }
			 } finally {
				 f.close();
			   in.close();
			 }
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
