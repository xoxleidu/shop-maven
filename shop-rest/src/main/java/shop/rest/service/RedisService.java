package shop.rest.service;

import shop.common.util.LayerResult;

public interface RedisService {

	LayerResult syncCatgory(String key);
	
	LayerResult syncContent(long contentCid);
}
