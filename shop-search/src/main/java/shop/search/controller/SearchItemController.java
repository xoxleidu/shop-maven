package shop.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.common.util.LayerResult;
import shop.search.service.SearchItemService;

@Controller
@RequestMapping("/manager")
public class SearchItemController {

	@Autowired
	private SearchItemService searchItemService;
	
	@RequestMapping("/importall")
	@ResponseBody
	public LayerResult importAllItems(){
		LayerResult result = searchItemService.importAllItems();
		return result;
	}
}
