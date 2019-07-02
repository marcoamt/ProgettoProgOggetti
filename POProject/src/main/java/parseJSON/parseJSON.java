package parseJSON;

import java.io.BufferedReader;
import java.io.FileReader;
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
		
		DownloadCSV d=new DownloadCSV("http://data.europa.eu/euodp/data/api/3/action/package_show?id=eu-prices-for-selected-dairy-products");
		
		
		String path=d.scarica();
		try{
			FileReader fr=new FileReader(path);
			BufferedReader br= new BufferedReader(fr);
			
			 String data = "";
			 String line = "";
			 try {
			   while ( ( line = br.readLine() ) != null ) {
				   
				   data+= line+"\n";
				   System.out.println( line );
			   }
			 } finally {
				 br.close();
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
