package shop.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.common.pojo.LayuiTree;
import shop.service.ItemCatService;

@Controller
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
	
	@RequestMapping("/itemCat")
	@ResponseBody
	public List<LayuiTree> catList(@RequestParam(value="id",defaultValue="0")Long parentId){
		List<LayuiTree> list = itemCatService.getItemCatList(parentId);
		return list;
	}
	
	
	
	
}
