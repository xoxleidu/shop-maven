package shop.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import shop.common.util.JsonUtils;
import shop.common.util.LayerResult;
import shop.mapper.TbItemDescMapper;
import shop.mapper.TbItemMapper;
import shop.mapper.TbItemParamItemMapper;
import shop.pojo.TbItem;
import shop.pojo.TbItemDesc;
import shop.pojo.TbItemExample;
import shop.pojo.TbItemParamItem;
import shop.pojo.TbItemParamItemExample;
import shop.pojo.TbItemParamItemExample.Criteria;
import shop.rest.dao.JedisClient;
import shop.rest.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	@Value("${REDIS_ITEM_EXPIRE}")
	private Integer REDIS_ITEM_EXPIRE;
	
	@Override
	public LayerResult getItemBaseInfo(long itemId) {
		
		try {
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":base");
			if (!StringUtils.isBlank(json)) {
				TbItem tbItem = JsonUtils.jsonToPojo(json, TbItem.class);
				return LayerResult.ok(tbItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TbItem tbItem = tbItemMapper.selectByPrimaryKey(itemId);
		
		try {
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":base", JsonUtils.objectToJson(tbItem));
			jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":base", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return LayerResult.ok(tbItem);
	}

	@Override
	public LayerResult getItemDesc(long itemId) {
		
		try {
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":desc");
			if (!StringUtils.isBlank(json)) {
				TbItemDesc tbItemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
				return LayerResult.ok(tbItemDesc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);
		
		try {
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":desc", JsonUtils.objectToJson(tbItemDesc));
			jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":desc", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return LayerResult.ok(tbItemDesc);
	}

	@Override
	public LayerResult getItemParam(long itemId) {
		
		try {
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":param");
			if (!StringUtils.isBlank(json)) {
				TbItemParamItem tbItemParamItem = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
				return LayerResult.ok(tbItemParamItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andItemIdEqualTo(itemId);
		
		List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
		
		if (list.size() > 0 && list != null) {
			TbItemParamItem paramItem = list.get(0);
			try {
				jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":param", JsonUtils.objectToJson(paramItem));
				jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":param", REDIS_ITEM_EXPIRE);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return LayerResult.ok(paramItem);
		}
		return LayerResult.build(400, "无此商品规格");
	}

}
