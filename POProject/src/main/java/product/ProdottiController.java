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
	
	@GetMapping("/metadata")
	public List<Metadati> getAllMeta() {
		return prodottiService.getAllMeta();
	}
	
	@GetMapping("/prodotti")
	public List<Prodotti> prodottiFilterPrice(){
		
			return prodottiService.getAllProducts();
	
		
	}
	
	//estrae prodotti con codice = filter
	@GetMapping("/prodotti/{filter}")
	public List<Prodotti> getPByCode(@PathVariable int filter){
		return prodottiService.getProductByCode(filter);
	}
	
	/*@GetMapping("/prodotti")
	public List<String> getProductByFilter(@RequestParam(value="filter", required=true) String filter){
		return prodottiService.getProdByFilter(filter);
	}*/
	
	
	//estrae il conteggio di ogni tipo di prodotto 
	@GetMapping("/statstring")
	public List<Conteggio> getCountElem(@RequestParam(value = "filter", required=false)String filter,@RequestParam String field){
		
			return prodottiService.getCountElement(field,filter);		
	}
	
	@PostMapping(path="/prodotti", consumes= {"application/JSON"})
	public List<Prodotti> getPByCode(@RequestBody String filter){
		return prodottiService.getProductByCodeFiltro(filter);
	}
	
	@GetMapping("/stats")
	public Item getStats(@RequestParam(value = "filter", required=false)String filter,@RequestParam String field)  {
		
			return prodottiService.getStatsFiltro(filter, field);
	}
	
	
	
}
