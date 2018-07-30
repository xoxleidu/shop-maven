package shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/")
	public String showIndex(){
		return "index";
	}
	
	@RequestMapping("/{page}")
	public String showIndexPage(@PathVariable String page) {
		return page;
	}
	
	@RequestMapping("/item/{page}")
	public String showItemPage(@PathVariable String page) {
		return "/item/"+page;
	}


}
