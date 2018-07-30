package shop.search.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.search.dao.SearchDao;
import shop.search.pojo.SearchItem;
import shop.search.pojo.SearchResults;
import shop.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchDao searchDao;
	
	@Override
	public SearchResults search(String queryString, int page, int rows) throws Exception {

		//创建查询对象
		SolrQuery sQuery = new SolrQuery();
		//设置查询条件
		sQuery.setQuery(queryString);
		//设置分页
		sQuery.setStart((page-1)*rows);
		sQuery.setRows(rows);
		//设置默认搜素域
		sQuery.set("df", "item_keywords");
		//设置高亮显示
		sQuery.setHighlight(true);
		sQuery.addHighlightField("item_title");
		sQuery.setHighlightSimplePre("<em style=\"color:red\">");
		sQuery.setHighlightSimplePost("</em>");
		//执行查询
		SolrDocumentList searchList = searchDao.search(sQuery);
		//查询结果列表
		List<SearchItem> itemList = new ArrayList<>();
		for (SolrDocument solrDocument : searchList) {
			SearchItem sItem = new SearchItem();
			sItem.setId((String) solrDocument.get("id"));
			sItem.setTitle((String) solrDocument.get("h_item_title"));
			sItem.setImage((String) solrDocument.get("item_image"));
			sItem.setPrice((long) solrDocument.get("item_price"));
			sItem.setSell_point((String) solrDocument.get("item_sell_point"));
			sItem.setCategory_name((String) solrDocument.get("item_category_name"));
			//添加的商品列表
			itemList.add(sItem);
		}
		
		//创建查询结果对象
		SearchResults sResults = new SearchResults();
		//取查询结果总数量
		sResults.setRecordCount(searchList.getNumFound());
		sResults.setItemList(itemList);
		
		//计算查询结果总页数
		long recordCount = searchList.getNumFound();
		long pageCount = recordCount / rows;
		if (recordCount % rows > 0) {
			pageCount++;
		}
		sResults.setPageCount(pageCount);
		sResults.setCurPage(page);

		
		return sResults;
	}

}
