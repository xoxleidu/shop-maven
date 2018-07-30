package shop.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shop.common.util.LayerResult;
import shop.portal.service.ProductsService;

@Controller
public class ProductsController {

	@Autowired
	private ProductsService productsService;
	
	@RequestMapping("/products/{cid}")
	public String getProducts(@PathVariable Long cid,
			@RequestParam(defaultValue="1")Integer page, 
			Model model){
		LayerResult products = productsService.getProducts(cid);
		model.addAttribute("itemList", products.getData());
		return "products";
	}
	
}
