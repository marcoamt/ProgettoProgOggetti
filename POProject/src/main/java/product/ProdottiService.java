package product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import parseJSON.DownloadCSV;

@Component
public class ProdottiService {

	public static List<Prodotti> products = new ArrayList<>();
	public static List<Metadati> meta = new ArrayList<>();

	static {
		DownloadCSV d=new DownloadCSV("http://data.europa.eu/euodp/data/api/3/action/package_show?id=eu-prices-for-selected-dairy-products");
		
		String path=d.scarica();
		try{
			FileReader fr=new FileReader(path);
			BufferedReader br= new BufferedReader(fr);
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
				   }else {
					   data=line.split(","); 
					   int cont=0;
					   for(Field a : Prodotti.class.getDeclaredFields())//per ogni attributo della classe assoccio il suo nome nel dataset e il suo tipo
					   {
						   meta.add(new Metadati(a.getName(),data[cont],a.getType().getSimpleName())); //aggiungo i metadata di ogni attributo in un array list
						   cont++;
					   }
				   }
				   i++;
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
	

	public List<Prodotti> getAllProducts() {
		return products;
	}


	public List<Prodotti> getPByCode(){
		return products;
	}


	public List<Metadati> getAllMeta(){
		return meta;
	}


	public List<Prodotti> getProdByPrice(String productCode, String PMIN, String PMAX) {

		List<Prodotti> prodotti = getProductByCode(Integer.parseInt(productCode));
		List<Prodotti> prova = new ArrayList<>();
		
		for(Prodotti p: prodotti){
				if(p.getMarketPrice() >= Double.parseDouble(PMIN) && p.getMarketPrice() <= Double.parseDouble(PMAX)){
					prova.add(p);
				}
		}
		return prova;
	}
	
	public List<Prodotti> getProductByCode(int productCode){
		List<Prodotti> prodotti = getAllProducts();
		List<Prodotti> prova = new ArrayList<>();
		for(Prodotti p: prodotti){
			if(p.getProductCode() == productCode){
				prova.add(p);
			}
		}
		return prova;
	}

	
	
	
	
	
}
