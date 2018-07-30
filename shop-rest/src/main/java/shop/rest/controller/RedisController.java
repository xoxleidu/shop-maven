package shop.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.common.util.LayerResult;
import shop.rest.service.RedisService;

@Controller
@RequestMapping("/redis")
public class RedisController {

	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/sync/{key}")
	@ResponseBody
	public LayerResult sync(@PathVariable String key){
		LayerResult result = redisService.syncCatgory(key);
		return result;
	}
}
