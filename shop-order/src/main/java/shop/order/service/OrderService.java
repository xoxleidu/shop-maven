package shop.order.service;

import java.util.List;

import shop.common.util.LayerResult;
import shop.pojo.TbOrder;
import shop.pojo.TbOrderItem;
import shop.pojo.TbOrderShipping;

public interface OrderService {

	LayerResult createOrder(TbOrder tbOrder,List<TbOrderItem> tbOrderItem,TbOrderShipping tbOrderShipping);
	
	
}
