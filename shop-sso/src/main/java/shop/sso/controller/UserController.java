package shop.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.common.util.ExceptionUtil;
import shop.common.util.LayerResult;
import shop.pojo.TbUser;
import shop.sso.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object checkData (@PathVariable String param,@PathVariable Integer type,String callback){
		
		LayerResult result = null;
		if (type != 1 && type != 2 && type != 3) {
			result = LayerResult.build(400, "类型错误");
		}
		if (result != null) {
			if (callback != null) {
				MappingJacksonValue value = new MappingJacksonValue(result);
				value.setJsonpFunction(callback);
				return value;
			} else {
				return result;
			}
		}
		
		try {
			result = userService.checkData(param, type);
		} catch (Exception e) {
			e.printStackTrace();
			return LayerResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		if (callback != null) {
			MappingJacksonValue value = new MappingJacksonValue(result);
			value.setJsonpFunction(callback);
			return value;
		} else {
			return result;
		}
		
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public LayerResult createUser(TbUser user){
		try {
			LayerResult result = userService.createUser(user);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return LayerResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public LayerResult userLogin(String username,String password,
			HttpServletRequest request,HttpServletResponse response){
		try {
			LayerResult result = userService.userLogin(username, password,request,response);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return LayerResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	@RequestMapping("/token/{token}")
	@ResponseBody
	public Object getUserByToken(@PathVariable String token,String callback){
		LayerResult result = null;
		try {
			result = userService.getUserByToken(token);
		} catch (Exception e) {
			e.printStackTrace();
			result = LayerResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		if (StringUtils.isBlank(callback)) {
			return result;
		}else {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
	}
	
}
