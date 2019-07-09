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

@RestController
public class ProdottiController {

	@Autowired
	private ProdottiService prodottiService;
	
	@RequestMapping("/")
    public String homeInit() {
        return "home";
    }
	
	/**
	 * This method prints out a list of all type of metadata
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
	public List<Prodotti> prodottiFilterPrice(){
			return prodottiService.getAllProducts();
	}
	
	/**
	 * This method prints out a list of products with code = filter
	 * @return a list of products with code = filter
	 */
	@GetMapping("/prodotti/{filter}")
	public List<Prodotti> getPByCode(@PathVariable int filter){
		return prodottiService.getProductByCode(filter);
	}
	
	/**
	 * This method prints out a list of count for each type of product
	 * @return a list of count for each type of product
	 */
	@GetMapping("/statstring")
	public List<Conteggio> getCountElem(@RequestParam(value = "filter", required=false)String filter,@RequestParam String field){

			return prodottiService.getCountElement(field,filter);		
	}
	/**
	 * This method prints out a list of products filtered with code
	 * @return a list of products filtered with code
	 */
	@PostMapping(path="/prodotti", consumes= {"application/JSON"})
	public List<Prodotti> getPByCode(@RequestBody String filter){
		return prodottiService.getProductByCodeFiltro(filter);
	}
	
	/**
	 * This method prints out statistics all data
	 * @return statistics all data
	 */
	@GetMapping("/stats")
	public Item getStats(@RequestParam(value = "filter", required=false)String filter,@RequestParam String field)  {
		return prodottiService.getStatsFiltro(filter, field);
	}
	
	
	
}
