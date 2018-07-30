package shop.sso.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import shop.common.util.CookieUtils;
import shop.common.util.JsonUtils;
import shop.common.util.LayerResult;
import shop.mapper.TbUserMapper;
import shop.pojo.TbUser;
import shop.pojo.TbUserExample;
import shop.pojo.TbUserExample.Criteria;
import shop.sso.dao.JedisClient;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TbUserMapper tbUserMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_USER_SESSION}")
	private String REDIS_USER_SESSION;
	@Value("${REDIS_USER_SESSION_TIME}")
	private Integer REDIS_USER_SESSION_TIME;
	
	@Override
	public LayerResult checkData(String content, Integer type) {
		TbUserExample example = new TbUserExample();
		Criteria createCriteria = example.createCriteria();
		
		if (type == 1) {
			createCriteria.andUsernameEqualTo(content);
		} else if (type == 2) {
			createCriteria.andPhoneEqualTo(content);
		} else {
			createCriteria.andEmailEqualTo(content);
		} 
		
		List<TbUser> list = tbUserMapper.selectByExample(example);
		
		if (list == null || list.size() == 0) {
			return LayerResult.ok(true);
		}
		
		return LayerResult.ok(false);
	}

	@Override
	public LayerResult createUser(TbUser user) {
		user.setCreated(new Date());
		user.setUpdated(new Date());
		
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		
		tbUserMapper.insert(user);
		return LayerResult.ok();
	}

	@Override
	public LayerResult userLogin(String username, String password, HttpServletRequest request,
			HttpServletResponse response) {
		TbUserExample example = new TbUserExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andUsernameEqualTo(username);
		List<TbUser> list = tbUserMapper.selectByExample(example);
		
		if (list == null || list.size() == 0) {
			return LayerResult.build(400, "用户名错误");
		}
		
		TbUser user = list.get(0);
		
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
			return LayerResult.build(400, "密码错误");
		}
		
		String token = UUID.randomUUID().toString();
		user.setPassword(null);
		jedisClient.set(REDIS_USER_SESSION + ":" + token, JsonUtils.objectToJson(user));
		jedisClient.expire(REDIS_USER_SESSION + ":" + token, REDIS_USER_SESSION_TIME);
		
		CookieUtils.setCookie(request, response, "TT_TOKEN", token);
		
		return LayerResult.ok(token);
	}

	@Override
	public LayerResult getUserByToken(String token) {
		String string = jedisClient.get(REDIS_USER_SESSION + ":" + token);
		if (StringUtils.isBlank(string)) {
			return LayerResult.build(400, "重新登录");
		}
		jedisClient.expire(REDIS_USER_SESSION + ":" + token, REDIS_USER_SESSION_TIME);
		return LayerResult.ok(JsonUtils.jsonToPojo(string, TbUser.class));
	}

}
