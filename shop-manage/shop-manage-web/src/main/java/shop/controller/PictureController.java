package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import shop.common.pojo.LayuiUploadImages;
import shop.common.util.JsonUtils;
import shop.service.PictureService;

@Controller
public class PictureController {

	@Autowired
	private PictureService pictureService;
	
	@RequestMapping("/upload")
	@ResponseBody
	public String upload(MultipartFile file){
		LayuiUploadImages result = pictureService.upload(file);
		String json = JsonUtils.objectToJson(result);
		return json;

	}
	
	
}
