package product;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import conteggio.Conteggio;
import item.Item;
import metadati.Metadati;
import service.ProdottiService;

@RestController
public class ProdottiController {

	@Autowired
	private ProdottiService prodottiService;
	
	@RequestMapping("/")
    public String homeInit() {
        return "home";
    }
	
	/**
	 * This method prints out a list of all type of metadata in the dataset
	 * @return a list of all type of metadata
	 */
	@GetMapping("/metadata")
	public List<Metadati> getAllMeta() {
		return prodottiService.getAllMeta();
	}
	
	/**
	 * This method prints out a list of all products
	 * @return  a list of all products
	 */
	@GetMapping("/prodotti")
	public List<Prodotti> getAllProducts(){
			return prodottiService.getAllProducts();
	}
	
	/**
	 * This method prints out a list of products with productcode = filter
	 * @param filter wrote by the user
	 * @return a list of products with productcode = filter
	 */
	@GetMapping("/prodotti/{filter}")
	public List<Prodotti> getProductByCode(@PathVariable int filter){
		return prodottiService.getProductByCode(filter);
	}
	
	/**
	 * This method prints out a list of fields with their number of elements
	 * @param filter
	 * @param field
	 * @return a list of fields with their number of elements
	 */
	@GetMapping("/statstring")
	public List<Conteggio> getCountElem(@RequestParam(value = "filter", required=false)String filter,@RequestParam String field){

			return prodottiService.getCountElement(field,filter);		
	}
	/**
	 * This method prints out a list of products filtered by logical or conditional operators
	 * @param filter
	 * @return a list of products filtered
	 */
	@PostMapping(path="/prodotti", consumes= {"application/JSON"})
	public List<Prodotti> getProductByCodeFiltro(@RequestBody String filter){
		return prodottiService.getProductByCodeFiltro(filter);
	}
	
	/**
	 * This method prints out statistics of a field filtered or no
	 * @param filter
	 * @param field
	 * @return statistics of a field
	 */
	@GetMapping("/stats")
	public Item getStatsFiltro(@RequestParam(value = "filter", required=false)String filter,@RequestParam(value = "field", required=true) String field)  {
		return prodottiService.getStatsFiltro(filter, field);
	}
	
	
	
}
