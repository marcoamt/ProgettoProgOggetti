package service;

import java.util.List;

import conteggio.Conteggio;
import item.Item;
import metadati.Metadati;
import product.Prodotti;

/**
 * 
 * Interface that contains all the main methods used by the service when is called by the controller
 *
 */
public interface InterfaceService {

	public abstract List<Prodotti> getAllProducts();
	
	public abstract List<Metadati> getAllMeta();
	
	public abstract List<Prodotti> getProductByCode(int productCode);
		
	public abstract List<Conteggio> getCountElement(String field, String filter);
	
	public abstract List<Prodotti> getProductByCodeFiltro(String filter);

	public abstract Item getStatsFiltro(String filter, String field);

	
}
