package product;

import java.util.List;

import org.json.simple.JSONObject;
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
	
	/*@GetMapping("/prodotti")
	public List<Prodotti> prodottiFilterPrice(@RequestParam(value="code", required=false) String productCode,@RequestParam(value="pmin", required=false) String PMIN,@RequestParam(value="pmax", required=false)String PMAX){
		if(productCode ==null){
			return prodottiService.getAllProducts();
		}
		else{
			return prodottiService.getProdByPrice(productCode,PMIN,PMAX);

		}
	}*/
	
	//estrae prodotti con codice = filter
	/*@GetMapping("/prodotti/{filter}")
	public List<Prodotti> getPByCode(@PathVariable int filter){
		return prodottiService.getProductByCode(filter);
	}*/
	
	@GetMapping("/prodotti")
	public List<String> getPByCode(@RequestParam(value="filter", required=true) String filter){
		return prodottiService.getProductByCode2(filter);
	}
	
	@GetMapping("/stats")
	public Item getProd(@RequestParam(value="field", required=true) String field){
		return prodottiService.getProduct(field);
	}
	
	//estrae il conteggio di ogni tipo di prodotto 
	@GetMapping("/product/{field}")
	public List<Conteggio> getCountElem(@PathVariable String field){
		return prodottiService.getCountElement(field);		
	}
	
	
}
