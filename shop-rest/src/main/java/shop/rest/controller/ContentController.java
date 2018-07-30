package shop.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.common.util.ExceptionUtil;
import shop.common.util.LayerResult;
import shop.pojo.TbContent;
import shop.rest.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/list/{categoryId}")
	@ResponseBody
	public LayerResult getContentJson(@PathVariable Long categoryId){
		try {
			List<TbContent> list = contentService.getContentList(categoryId);
			return LayerResult.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			return LayerResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	
}
