package shop.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import shop.common.util.HttpClientUtil;
import shop.common.util.LayerResult;
import shop.pojo.TbItem;
import shop.portal.service.ProductsService;

@Service
public class ProductsServiceImpl implements ProductsService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_PRODUCTS_URL}")
	private String REST_PRODUCTS_URL;
	
	@Override
	public LayerResult getProducts(long cid) {
		String doGet = HttpClientUtil.doGet(REST_BASE_URL + REST_PRODUCTS_URL + cid);
		LayerResult formatToList = LayerResult.formatToList(doGet, TbItem.class);
		if (formatToList.getStatus() == 200) {
			List<TbItem> list = (List<TbItem>) formatToList.getData();
			return LayerResult.ok(list);
		}
		return null;
	}
}
