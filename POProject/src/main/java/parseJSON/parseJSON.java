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
import java.util.ArrayList;
import java.util.List;

import product.Prodotti;



public class parseJSON {
	public static void main(String[] args) {
		
		//File f= new File("market-prices-dairy-products_en.csv");
		//if(file)
		DownloadCSV d=new DownloadCSV("http://data.europa.eu/euodp/data/api/3/action/package_show?id=eu-prices-for-selected-dairy-products");
		
		
		String path=d.scarica();
		try{
			FileReader fr=new FileReader(path);
			BufferedReader br= new BufferedReader(fr);
			List<Prodotti> products = new ArrayList<>();
			 //String data = "";
			String[] data;
			 String line = "";
			 int i=0;
			 try {
			   while ( ( line = br.readLine() ) != null ) {
				   
				   /*data+= line+"\n";
				   System.out.println( line );*/
				   if(i!=0) {
				   data=line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"); 
				   	products.add(new Prodotti(data[0],data[1],Integer.parseInt(data[2]),data[3],data[4],data[5],data[6],Integer.parseInt(data[7]),Double.parseDouble(data[8])));
				   }
				   i++;
			   }
			 } finally {
				 br.close();
			 }
			System.out.println( "OK" );
			stampa(products);
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
	
	public static void stampa(List<Prodotti> p) {
		for (int i = 0; i < p.size(); i++) 
        { 
            // the data received from arraylist is of Data type 
            // which is custom (int, String, int, long) 
            // based on class Data 
  
            Prodotti data = p.get(i); 
  
            // data variable of type Data has four primitive datatypes 
            // roll -int 
            // name- String 
            // marks- int 
            // phone- long 
            System.out.println(data.getProductCode()+" "+data.getDesc()+" "+data.getMarketPrice());
        } 
	}
}
