package shop.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import shop.common.util.HttpClientUtil;
import shop.common.util.JsonUtils;
import shop.common.util.LayerResult;
import shop.portal.service.OrderService;
import shop.search.pojo.Orders;

@Service
public class OrderServiceImpl implements OrderService {

	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	@Value("${ORDER_CAEATE_URL}")
	private String ORDER_CAEATE_URL;
	
	@Override
	public String createOrder(Orders orders) {
		String doPost = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CAEATE_URL,JsonUtils.objectToJson(orders));
		LayerResult result = LayerResult.format(doPost);
		if (result.getStatus() == 200) {
			Object orderId = result.getData();
			return orderId.toString();
		}
		return "";
	}

}
