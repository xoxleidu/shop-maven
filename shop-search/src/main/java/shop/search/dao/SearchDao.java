package shop.search.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrDocumentList;

public interface SearchDao {

	SolrDocumentList search(SolrQuery query) throws Exception;
}
