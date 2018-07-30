package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.common.pojo.LayuiData;
import shop.common.util.LayerResult;
import shop.pojo.TbContent;
import shop.service.ContentService;

@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/content/listbyid")
	@ResponseBody
	public LayuiData queryListById(@RequestParam(value="limit",defaultValue="5")Integer limit,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="id",defaultValue="0")Long categoryId){
		//System.out.println(categoryId);
		LayuiData list = contentService.queryListByid(limit,page,categoryId);
		return list;
	}
	
	@RequestMapping("/content/addcontent")
	@ResponseBody
	public LayerResult addContent(TbContent tbContent){
		LayerResult result = contentService.addContent(tbContent);
		return result;
	}
}
