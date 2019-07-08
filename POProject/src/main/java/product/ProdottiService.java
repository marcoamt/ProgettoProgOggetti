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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import parseJSON.DownloadCSV;

@Component
public class ProdottiService {

	public static List<Prodotti> products = new ArrayList<>();
	public static List<Metadati> meta = new ArrayList<>();
	public static List<String> filtro;

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

	/*public Item getProduct(String field) {

		List<Prodotti> prodotti = getAllProducts();
		List<Double> prezzi=new ArrayList<>();
		double avg=0, min=0, max=0, std=0, sum=0;
		int count = 0;
		
		for(Prodotti p: prodotti) {
			sum+=p.getMarketPrice();
			count++;
			prezzi.add(p.getMarketPrice());
		}
		avg=sum/count;
		max =max(prezzi);
		min =min(prezzi);
		std = std(prezzi, avg);
		
		return new Item(field, avg, min, max, std, sum, count);
	}*/

	public static double max(List<Double> p) {
		
		Double mx=p.get(0);
		for(int i=1;i<p.size();i++) {
		if(p.get(i)>mx)
		mx=p.get(i);

		}
		return mx;
		
	}
	

	public static double min(List<Double> p) {
		
		Double mn=p.get(0);
		for(int i=1;i<p.size();i++) {
		if(p.get(i)<mn)
		mn=p.get(i);

		}
		return mn;
		
	}
	
	public static double std(List<Double> p, double avg) {
		double somm=0;
		for(Double pr:p) {
			somm+=Math.pow((pr-avg),2);
		}
		
		return Math.sqrt(somm/p.size());
	}

	
	public List<Prodotti> getProductByCodeFiltro(String filter) {
		//JSONObject obj = filter;
		filtro=new ArrayList<>();
		JSONObject obj;
		try {
			obj = (JSONObject) JSONValue.parseWithException(filter);
			printJsonObject(obj);
		} catch (ParseException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		} 

		List<Prodotti> prodotti = getAllProducts();

		List<Prodotti> prova = new ArrayList<>();
		switch(filtro.get(0)){
		case "prezzo":
			for(Prodotti p: prodotti) {
				if(opPrezzo(p.getMarketPrice(),filtro.get(1),filtro.get(2)))
					prova.add(p);
			}
			break;
			
		case "country":
			for(Prodotti p: prodotti) {
				if(opString(p.getCountry(),filtro.get(1),filtro.get(2)))
					prova.add(p);
			}
			break;
			
		case "desc":
			for(Prodotti p: prodotti) {
				if(opString(p.getDesc(),filtro.get(1),filtro.get(2)))
					prova.add(p);
			}
			break;
		}
		
			
			
		return prova;
		
	}
	
	public boolean opPrezzo(double price, String op,String dati) {
		
		//List<Prodotti> prova = new ArrayList<>();
		
		switch(op) {
			case "$bt":
				String d=dati.replaceAll("[^.-?0-9]+", " ");
				String[] n=d.split(" ");
				System.out.println(n[1]);
				System.out.println(n[2]);
					if(price<=Double.parseDouble(n[2]) && price>=Double.parseDouble(n[1]))
							return true;
			break;
			
			case "$gt":
				if(price>Double.parseDouble(dati))
					return true;
				break;
		}
		return false;
	}
	
	public boolean opString(String s, String op,String dati) {
		
		//List<Prodotti> prova = new ArrayList<>();
		
		switch(op) {
			case "$in":
				if(dati.startsWith("[")) {
					String d=dati.replaceAll("[^.-?A-Z]+", " ");
					String[] n=d.split(" ");
					//System.out.println(n[1]);
					//System.out.println(n[2]);
					for(int i=1; i<n.length; i++) {
						if(s.equals(n[i]))
								return true;
					}
				}
				else {
					if(s.equals(dati))
						return true;
				}
			break;
			
			case "$not":
				if(!s.equals(dati))
					return true;
		}
		return false;
	}
	
	//get keys and values form filter string
	public void printJsonObject(JSONObject jsonObj) {
	    for (Object key : jsonObj.keySet()) {
	        //based on you key types
	        String keyStr = (String)key;
	        Object keyvalue = jsonObj.get(keyStr);

	        //Print key and value
	        System.out.println("key: "+ keyStr + " value: " + keyvalue);
	        filtro.add(keyStr);

	        //for nested objects iteration if required
	        if (keyvalue instanceof JSONObject) {
	        	//System.out.println("doppio");
	            printJsonObject((JSONObject)keyvalue);
	        }else {
	        	//System.out.println("uno");
	        	filtro.add(keyvalue.toString());
	        }

	    }	        
	}
	
	
	public List<Conteggio> getCountElement(String field, String filter) {
		
		List<Prodotti> prodotti;
		List<Conteggio> prova = new ArrayList<>();
		List<String> list = new ArrayList<>();
		
		if(filter!=null) {
			prodotti=getProductByCodeFiltro( filter);
		}else {
			prodotti = getAllProducts();
		}
		
			switch(field){
				case "desc":
					
					for(Prodotti p: prodotti){
						list.add(p.getDesc());
					}
					Set<String> distinct = new HashSet<>(list);		
					for(String s:distinct){
						prova.add(new Conteggio(s,Collections.frequency(list, s)));
						
					}
					
				break;
				
				case "country":
					for(Prodotti p: prodotti){
						list.add(p.getCountry());
					}
					Set<String> distinct2 = new HashSet<>(list);		
					for(String s:distinct2){
						prova.add(new Conteggio(s,Collections.frequency(list, s)));
						
					}
				break;
			}
		
		return prova;
	}
	
	
	public Item getStatsFiltro(String filter, String field) {
		List<Prodotti> prodotti;
		List<Double> prezzi=new ArrayList<>();
		double avg=0, min=0, max=0, std=0, sum=0;
		int count = 0;
		
		if(filter!=null) {
			prodotti=getProductByCodeFiltro( filter);
		}else {
			prodotti = getAllProducts();
		}

		for(Prodotti p: prodotti) {
			sum+=p.getMarketPrice();
			count++;
			prezzi.add(p.getMarketPrice());
		}
		//System.out.print(getProductByCodeFiltro( filter).size());
		avg=sum/count;
		max =max(prezzi);
		min =min(prezzi);
		std = std(prezzi, avg);
		
		return new Item(field, avg, min, max, std, sum, count);
	}


	
	
	
}
