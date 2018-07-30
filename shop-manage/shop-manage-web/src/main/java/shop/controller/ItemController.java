package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.common.pojo.LayuiData;
import shop.common.util.HttpClientUtil;
import shop.pojo.TbItem;
import shop.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	@Value("${ITEM_SOLR_UPDATA}")
	private String ITEM_SOLR_UPDATA;
	
	
	
	@RequestMapping("/find/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId){
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	
	/*@RequestMapping("/item/list")
	public String getItemList(Integer page,Integer rows,Model model){
		MyPageHelper list = itemService.getItemList(page, rows);
		model.addAttribute("list", list);
		model.addAttribute("pageNumber",page);
		return "/item/list";
	}*/
	
	
	@RequestMapping("/allList/json")
	@ResponseBody
	public LayuiData allList2(@RequestParam(value="limit",defaultValue="5")Integer limit,@RequestParam(value="page",defaultValue="1")Integer page){
		LayuiData list = itemService.getItemList(page,limit);
		return list;
	}
	
	@RequestMapping("/additem")
	@ResponseBody
	public String addItem(TbItem tbItem, String desc,String itemParams) throws Exception {
		
		
		itemService.addItem(tbItem, desc, itemParams);
		//更新solr数据库...时间长
		//HttpClientUtil.doGet(ITEM_SOLR_UPDATA);//转义出错
		HttpClientUtil.doGet("http://localhost:8083/search/manager/importall");

		return null;
	}
	
	
}
