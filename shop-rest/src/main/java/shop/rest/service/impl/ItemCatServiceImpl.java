package shop.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import shop.common.util.JsonUtils;
import shop.mapper.TbItemCatMapper;
import shop.pojo.TbItemCat;
import shop.pojo.TbItemCatExample;
import shop.pojo.TbItemCatExample.Criteria;
import shop.rest.dao.JedisClient;
import shop.rest.pojo.CatNode;
import shop.rest.pojo.CatResult;
import shop.rest.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${INDEX_CATEGORY_REDIS_KEY}")
	private String INDEX_CATEGORY_REDIS_KEY;
	@Value("${INDEX_ITEM_CAT_LINE}")
	private Integer INDEX_ITEM_CAT_LINE;
	
	
	@Override
	public CatResult getItemCatList() {
		
		//从缓存中取内容
		try {
			String result = jedisClient.hget(INDEX_CATEGORY_REDIS_KEY, "itemCat");
			if (!StringUtils.isBlank(result)) {
				//把字符串转换成list
				//System.out.println("redis");
				CatResult resultList = JsonUtils.jsonToPojo(result, CatResult.class);
				return resultList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		CatResult catResult = new CatResult();
		catResult.setData(getCatList(0));
		
		//向缓存中添加内容
		try {
			//把list转换成字符串
			String cacheString = JsonUtils.objectToJson(catResult);
			jedisClient.hset(INDEX_CATEGORY_REDIS_KEY, "itemCat", cacheString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("sql");
		return catResult;
		
	}
	
	private List<?> getCatList(long parentId) {
		
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
		
		List resultList = new ArrayList<>();
		
		int cont = 0;
		
		for (TbItemCat tbItemCat : list) {
			
			if (tbItemCat.getIsParent()) {
				
				CatNode catNode = new CatNode();
				
				if (parentId==0) {
					catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				}else {
					catNode.setName(tbItemCat.getName());
				}
				
				catNode.setUrl("/products/"+tbItemCat.getId());
				catNode.setItem(getCatList(tbItemCat.getId()));
				
				resultList.add(catNode);
				
				cont++;
				if (parentId == 0 && cont >= INDEX_ITEM_CAT_LINE) {
					break;
				}
				
			}else {
				resultList.add("/products/"+tbItemCat.getId()+"|" + tbItemCat.getName());
			}
			
		}
		
		return resultList;
	}

}
