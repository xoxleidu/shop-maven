package shop.portal.service;

import shop.search.pojo.SearchResults;

public interface SearchService {

	SearchResults search(String queryString, int page);
}
