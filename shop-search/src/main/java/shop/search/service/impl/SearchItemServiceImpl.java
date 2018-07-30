package shop.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.common.util.ExceptionUtil;
import shop.common.util.LayerResult;
import shop.search.mapper.SearchItemMapper;
import shop.search.pojo.SearchItem;
import shop.search.service.SearchItemService;

@Service
public class SearchItemServiceImpl implements SearchItemService {

	@Autowired
	private SearchItemMapper searchItemMapper;
	@Autowired
	private HttpSolrClient httpSolrClient;
	
	@Override
	public LayerResult importAllItems() {
		
		try {
			
			List<SearchItem> list = searchItemMapper.getItemList();
			
			for (SearchItem searchItem : list) {
				
				SolrInputDocument document = new SolrInputDocument();
				
				document.setField("id", searchItem.getId());
				document.setField("item_title", searchItem.getTitle());
				document.setField("item_sell_point", searchItem.getSell_point());
				document.setField("item_price", searchItem.getPrice());
				document.setField("item_image", searchItem.getImage());
				document.setField("item_category_name", searchItem.getCategory_name());
				document.setField("item_desc", searchItem.getItem_des());
				
				//solrServer.add(document);
				httpSolrClient.add(document);

			}
			
			//solrServer.commit();
			httpSolrClient.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			return LayerResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		return LayerResult.ok();
	}

}
