package shop.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import shop.common.util.HttpClientUtil;
import shop.common.util.JsonUtils;
import shop.common.util.LayerResult;
import shop.pojo.TbContent;
import shop.portal.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_INDEX_AD_URL}")
	private String REST_INDEX_AD_URL;
	
	@Value("${INDEX_AD_WIDTH}")
	private Integer INDEX_AD_WIDTH;
	@Value("${INDEX_AD_HEIGHT}")
	private Integer INDEX_AD_HEIGHT;
	@Value("${INDEX_AD_B_WIDTH}")
	private Integer INDEX_AD_B_WIDTH;
	@Value("${INDEX_AD_B_HEIGHT}")
	private Integer INDEX_AD_B_HEIGHT;
	
	
	@Override
	public String getContentList() {
		String result = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
		
		try {
			LayerResult layerResult = LayerResult.formatToList(result, TbContent.class);
			
			List<TbContent> list = (List<TbContent>) layerResult.getData();
			List<Map> resultList = new ArrayList<Map>();
			
			for (TbContent tbContent : list) {
				Map map = new HashMap();
				map.put("src", tbContent.getPic());
				map.put("width", INDEX_AD_WIDTH);
				map.put("height", INDEX_AD_HEIGHT);
				map.put("srcB", tbContent.getPic2());
				map.put("widthB", INDEX_AD_B_WIDTH);
				map.put("heightB", INDEX_AD_B_HEIGHT);
				map.put("href", tbContent.getUrl());
				map.put("alt", tbContent.getTitle());
				resultList.add(map);
			}
			
			return JsonUtils.objectToJson(resultList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
