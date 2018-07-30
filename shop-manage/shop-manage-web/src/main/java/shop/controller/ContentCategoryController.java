package shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.common.pojo.ZTreeNode;
import shop.pojo.TbContentCategory;
import shop.service.ContentCategoryService;

@Controller
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/item/category/list")
	@ResponseBody
	public List<ZTreeNode> getContentCatList(@RequestParam(value="id", defaultValue="0")Long parentID){
		List<ZTreeNode> list = contentCategoryService.getCategoryList(parentID);
		return list;
		
	}
	
	@RequestMapping("/item/category/add")
	@ResponseBody
	public TbContentCategory addContentCat(Long parentId, String name){
		TbContentCategory list = contentCategoryService.insertContentCategory(parentId, name);
		return list;
	}
}
