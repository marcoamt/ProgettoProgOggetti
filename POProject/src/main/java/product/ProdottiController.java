package product;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public List<Prodotti> prodottiFilterPrice(@RequestParam(value="code", required=false) String productCode,@RequestParam(value="pmin", required=false) String PMIN,@RequestParam(value="pmax", required=false)String PMAX){
		if(productCode ==null){
			return prodottiService.getAllProducts();
		}
		else{
			return prodottiService.getProdByPrice(productCode,PMIN,PMAX);

		}
	}
	
	//estrae prodotti con codice = filter
	@GetMapping("/prodotti/{filter}")
	public List<Prodotti> getPByCode(@PathVariable int filter){
		return prodottiService.getProductByCode(filter);
	}
	
	
}
