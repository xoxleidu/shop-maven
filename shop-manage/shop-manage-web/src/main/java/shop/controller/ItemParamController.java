package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.common.pojo.LayuiData;
import shop.common.util.LayerResult;
import shop.pojo.TbItemParam;
import shop.service.ItemParamService;

@Controller
@RequestMapping("/param")
public class ItemParamController {
	
	@Autowired
	private ItemParamService paramService;

	@RequestMapping("/allList/json")
	@ResponseBody
	public LayuiData allList(@RequestParam(value="limit",defaultValue="5")Integer limit,@RequestParam(value="page",defaultValue="1")Integer page){
		LayuiData list = paramService.getParamList(page,limit);
		return list;
	}
	
	@RequestMapping("/itemcatid/{itemCatId}")
	@ResponseBody
	public LayerResult getItemParamByCid(@PathVariable Long itemCatId) {
		LayerResult result = paramService.getParamByCid(itemCatId);
		return result;
	}
	
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public LayerResult addParam(@PathVariable Long cid,String paramData){

		TbItemParam tbItemParam = new TbItemParam();
		tbItemParam.setItemCatId(cid);
		tbItemParam.setParamData(paramData);
		LayerResult result = paramService.addParam(tbItemParam);
		
		return result;
	}

	
}
