package shop.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import shop.common.util.ExceptionUtil;
import shop.common.util.LayerResult;
import shop.rest.dao.JedisClient;
import shop.rest.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private JedisClient jedisClient;
	@Value("${INDEX_CATEGORY_REDIS_KEY}")
	private String INDEX_CATEGORY_REDIS_KEY;
	
	@Override
	public LayerResult syncCatgory(String key) {
		try {
			jedisClient.hdel(INDEX_CATEGORY_REDIS_KEY, key);
		} catch (Exception e) {
			e.printStackTrace();
			return LayerResult.build(500, ExceptionUtil.getStackTrace(e));
			
		}
		return LayerResult.ok();
	}

	@Override
	public LayerResult syncContent(long contentCid) {
		// TODO Auto-generated method stub
		return null;
	}

}
