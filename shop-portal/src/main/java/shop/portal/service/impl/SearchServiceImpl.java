package shop.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import shop.common.util.HttpClientUtil;
import shop.common.util.LayerResult;
import shop.portal.service.SearchService;
import shop.search.pojo.SearchResults;

@Service
public class SearchServiceImpl implements SearchService {

	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;
	
	@Override
	public SearchResults search(String queryString, int page) {

		//查询参数
		Map<String, String> param = new HashMap<String, String>();
		param.put("q", queryString);
		param.put("page", page + "");
		
		try {
			String doGet = HttpClientUtil.doGet(SEARCH_BASE_URL,param);
			LayerResult formatToPojo = LayerResult.formatToPojo(doGet, SearchResults.class);
			if (formatToPojo.getStatus() == 200) {
				SearchResults data = (SearchResults) formatToPojo.getData();
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


}
