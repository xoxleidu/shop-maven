package shop.search.service;

import shop.search.pojo.SearchResults;

public interface SearchService {

	SearchResults search(String queryString, int page, int rows)throws Exception;
}
