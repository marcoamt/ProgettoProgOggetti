package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import conteggio.Conteggio;
import item.Item;
import metadati.Metadati;
import parseJSON.DownloadCSV;
import product.Prodotti;

@Service
public class ProdottiService implements InterfaceService, Utility{

	/**
	 * @param products 
	 * @param meta
	 * @param filtro
	 * @param download
	 * @param item
	 * @param prod
	 */
	private static List<Prodotti> products = new ArrayList<>();
	private static List<Metadati> meta = new ArrayList<>();
	private static List<Conteggio> prod = new ArrayList<>();
	private static DownloadCSV download;
	private static List<String> filtro;
	private static Item item;

	/**
	 * constructor that will download the file csv using {@link parseJSON.DownloadCSV}
	 * after that it will save the dataset in a List of {@link product.Prodotti} object
	 */

	public ProdottiService(){
		download=new DownloadCSV("http://data.europa.eu/euodp/data/api/3/action/package_show?id=eu-prices-for-selected-dairy-products");
		
		String path=download.scarica();
		try{
			FileReader fr=new FileReader(path);
			BufferedReader br= new BufferedReader(fr);
			 //String data = "";
			String[] data;
			 String line = "";
			 int i=0;
			 try {
			   while ( ( line = br.readLine() ) != null ) {
	
				   if(i!=0) {
				   data=line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");   
					   if(data.length == meta.size()){
						   products.add(new Prodotti(data[0],data[1],Integer.parseInt(data[2]),data[3],data[4],data[5],data[6],Integer.parseInt(data[7]),Double.parseDouble(data[8])));
					   }else{
						   System.out.println(meta.get(data.length));
					   }
				   }else {
					   data=line.split(","); 
					   int cont=0;
					   List<Field> b =getAllFields(new ArrayList<Field>(), Prodotti.class);
					   for(Field a : b)//per ogni attributo della classe assoccio il suo nome nel dataset e il suo tipo
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
			System.out.println( "Dati salvati" );
		}catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.toString());
			System.exit(1);
		}
	}
	
	/**
	 * this is a recursive method that allows to have all the fields of a class and it's superclass
	 * @return list of all fields in the class passes
	 */
	
	public List<Field> getAllFields(List<Field> fields, Class<?> type) {
	    fields.addAll(Arrays.asList(type.getDeclaredFields()));

	    if (type.getSuperclass() != null) {
	        getAllFields(fields, type.getSuperclass());
	    }

	    return fields;
	}
	

	/**
	 * 
	 * @return a list of all products
	 */
	@Override
	public List<Prodotti> getAllProducts() {
		return products;
	}

	/**
	 * 
	 * @return a list of metadati
	 */
	@Override
	public List<Metadati> getAllMeta(){
		return meta;
	}


	/**
	 * 
	 * @param productCode is the code of product
	 * @return a list of products get by code
	 */
	@Override
	public List<Prodotti> getProductByCode(int productCode){
		List<Prodotti> prodotti = getAllProducts();
		List<Prodotti> prod = new ArrayList<>();
		for(Prodotti p: prodotti){
			if(p.getProductCode() == productCode){
				prod.add(p);
			}
		}
		return prod;
	}

	/**
	 * 
	 * @param p is a list of double element
	 * @return the max of list
	 */
	public double max(List<Double> p) {
		Double mx=p.get(0);
		for(int i=1;i<p.size();i++) {
		if(p.get(i)>mx)
		mx=p.get(i);

		}
		return mx;
	}
	
	/**
	 * 
	 * @param p is a list of double element
	 * @return the min of list
	 */
	public double min(List<Double> p) {
		Double mn=p.get(0);
		for(int i=1;i<p.size();i++) {
			if(p.get(i)<mn)
			mn=p.get(i);
		}
		return mn;
	}
	
	/**
	 * 
	 * @param p is a list of double element
	 * @param avg is the average
	 * @return 
	 */
	public double std(List<Double> p, double avg) {
		double somm=0;
		for(Double pr:p) {
			somm+=Math.pow((pr-avg),2);
		}		
		return Math.sqrt(somm/p.size());
	}

		
	
	/**
	 * get keys and values form filter string
	 * @param jsonObj is the json object extract from the filter
	 */
	public void printJsonObject(JSONObject jsonObj) {
	    for (Object key : jsonObj.keySet()) {
	        //based on you key types
	        String keyStr = (String)key;
	        Object keyvalue = jsonObj.get(keyStr);

	        //Print key and value
	        //System.out.println("key: "+ keyStr + " value: " + keyvalue);
	        filtro.add(keyStr);

	        //for nested objects iteration if required
	        if (keyvalue instanceof JSONObject) {
	            printJsonObject((JSONObject)keyvalue);
	        }else {
	        	
	        	filtro.add(keyvalue.toString());
	        }
	    }	        
	}
	
	/**
	 * This method gets the request's body (filter) and parse it like a JSON
	 * then it controls which field is called and depends on that will call {@link #opString(String s, String op,String dati) opString()} or {@link #opPrezzo(double price, String op,String dati) opPrezzo} or send a bad request
	 * @param filter is the filter
	 * @return for each case a list of product get by filter
	 */
	@Override
	public List<Prodotti> getProductByCodeFiltro(String filter) {
		filtro = new ArrayList<>();
		JSONObject obj;
		try {
			obj = (JSONObject) JSONValue.parseWithException(filter);
			printJsonObject(obj);
		} catch (ParseException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "errore sintassi url");
			
		} 

		List<Prodotti> prodotti = getAllProducts();

		List<Prodotti> prod = new ArrayList<>();
		switch(filtro.get(0)){
		case "prezzo":
			for(Prodotti p: prodotti) {
				if(opPrezzo(p.getMarketPrice(),filtro.get(1),filtro.get(2)))
					prod.add(p);
			}
			break;
			
		case "country":
			for(Prodotti p: prodotti) {
				if(opString(p.getCountry(),filtro.get(1),filtro.get(2)))
					prod.add(p);
			}
			break;
			
		case "desc":
			for(Prodotti p: prodotti) {
				if(opString(p.getDesc(),filtro.get(1),filtro.get(2)))
					prod.add(p);
			}
			break;
			
		default:
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "field " + filtro.get(0) + " not suported");
			
	
		}
		return prod;
	}
	
	/**
	 * 
	 * @param price is the price of element
	 * @param op is the operation
	 * @param dati are the parameters passed on the filter
	 * @return two conditional operator
	 */
	public boolean opPrezzo(double price, String op,String dati) {
		
		switch(op) {
			case "$bt":
				String d=dati.replaceAll("[^.-?0-9]+", " ");
				String[] n=d.split(" ");
				//System.out.println(n[1]);
				//System.out.println(n[2]);
					if(price<=Double.parseDouble(n[2]) && price>=Double.parseDouble(n[1]))
							return true;
			break;
			
			case "$gt":
				if(price>Double.parseDouble(dati))
					return true;
				break;
			
			default:
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "operator " + op + " not suported, only supported $bt, $gt");
				
			
		}
		return false;
	}
	
	/**
	 * 
	 * @param s contains the return of get{Field} method
	 * @param op is the operation
	 * @param dati are the parameters passed on the filter
	 * @return two logical operator
	 */
	public boolean opString(String s, String op,String dati) {
	
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
				break;
			
			default:
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "operator " + op + " not suported, only supported $in, $not");
				
			
		}
		return false;
	}
	
	/**
	 * This method count how many times a word(string fields) is repeated
	 * It uses {@link #getProductByCodeFiltro(String filter) getProductByCodeFiltro} and  {@link #getAllProducts() getAllProducts} depending if there's a filter or no
	 * @param field
	 * @param filter 
	 * @return count element of two field, desc and country
	 */
	@Override
	public List<Conteggio> getCountElement(String field, String filter) {
		List<Prodotti> prodotti;
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
						prod.add(new Conteggio(s,Collections.frequency(list, s)));
						
					}
					
				break;
				
				case "country":
					for(Prodotti p: prodotti){
						list.add(p.getCountry());
					}
					Set<String> distinct2 = new HashSet<>(list);		
					for(String s:distinct2){
						prod.add(new Conteggio(s,Collections.frequency(list, s)));
						
					}
				break;
				
				default:
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "field " + field + " not suported");
					
			}
		
		return prod;
	}

	/**
	 * This method gets the statistics for the field passed on the request
	 * It uses the inherited methods from the {@link Utility}
	 * @param filter
	 * @param field
	 * @return an item with all statistics data
	 */
	@Override
	public Item getStatsFiltro(String filter, String field) {
		List<Prodotti> prodotti;
		List<Double> prezzi=new ArrayList<>();
		double avg=0, min=0, max=0, std=0, sum=0;
		int count = 0;
		if(field.equals("prezzo")) {
			if(filter!=null) {
			
				prodotti=getProductByCodeFiltro( filter);
			}else {
				prodotti = getAllProducts();
			}
		}else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "field " + field + " not suported");
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
		item =new Item(field, avg, min, max, std, sum, count);
		return item;
	}
	
	
	
	
}