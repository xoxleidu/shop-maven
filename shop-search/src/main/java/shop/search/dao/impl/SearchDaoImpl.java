package shop.search.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shop.search.dao.SearchDao;

@Repository
public class SearchDaoImpl implements SearchDao {

	@Autowired
	private HttpSolrClient httpSolrClient;
	
	@Override
	public SolrDocumentList search(SolrQuery query) throws Exception{
		
		QueryResponse qr = httpSolrClient.query(query);
		
		SolrDocumentList results = qr.getResults();
		
		SolrDocumentList newList = new SolrDocumentList();
		
		//取高亮显示
		Map<String, Map<String, List<String>>> highlighting = qr.getHighlighting();
		
		for (SolrDocument solrDocument : results) {
			//取高亮显示的结果
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			String title = "";
			if (list != null && list.size()>0) {
				title = list.get(0);
			} else {
				title = (String) solrDocument.get("item_title");
			}
			solrDocument.setField("h_item_title", title);
			newList.add(solrDocument);
		}
		newList.setNumFound(results.getNumFound());
		
		return newList;
		
	}
}
