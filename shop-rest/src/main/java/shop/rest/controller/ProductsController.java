package shop.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.common.util.LayerResult;
import shop.rest.service.ProductsService;

@Controller
public class ProductsController {

	@Autowired
	private ProductsService productsService;
	
	@RequestMapping("/products/{cid}")
	@ResponseBody
	public LayerResult getProducts(@PathVariable Long cid){
		LayerResult result = productsService.getProducts(cid);
		return result;
	}
}
