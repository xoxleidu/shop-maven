package shop.order.service.impl;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import shop.common.util.LayerResult;
import shop.mapper.TbOrderItemMapper;
import shop.mapper.TbOrderMapper;
import shop.mapper.TbOrderShippingMapper;
import shop.order.dao.JedisClient;
import shop.order.service.OrderService;
import shop.pojo.TbOrder;
import shop.pojo.TbOrderItem;
import shop.pojo.TbOrderShipping;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private TbOrderMapper tbOrderMapper;
	@Autowired
	private TbOrderItemMapper tbOrderItemMapper;
	@Autowired
	private TbOrderShippingMapper tbOrderShippingMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${ORDER_GEN_KEY}")
	private String ORDER_GEN_KEY;
	@Value("${ORDER_INIT_ID}")
	private String ORDER_INIT_ID;
	@Value("${ORDER_DETAIL_GEN_KEY}")
	private String ORDER_DETAIL_GEN_KEY;

	
	@Override
	public LayerResult createOrder(TbOrder tbOrder, List<TbOrderItem> tbOrderItem, TbOrderShipping tbOrderShipping) {
		String string = jedisClient.get(ORDER_GEN_KEY);
		if (StringUtils.isBlank(string)) {
			jedisClient.set(ORDER_GEN_KEY, ORDER_INIT_ID);
		}
		long orderId = jedisClient.incr(ORDER_GEN_KEY);
		Date date = new Date();
		
		tbOrder.setOrderId(orderId + "");
		tbOrder.setStatus(1);
		tbOrder.setCreateTime(date);
		tbOrder.setUpdateTime(date);
		tbOrder.setBuyerRate(0);
		tbOrderMapper.insert(tbOrder);
		
		tbOrderShipping.setOrderId(orderId + "");
		tbOrderShipping.setCreated(date);
		tbOrderShipping.setUpdated(date);
		tbOrderShippingMapper.insert(tbOrderShipping);
		
		for (TbOrderItem item : tbOrderItem) {
			long orderDetailId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
			item.setId(orderDetailId + "");
			item.setOrderId(orderId + "");
			tbOrderItemMapper.insert(item);
		}
		
		return LayerResult.ok(orderId);
	}

}
