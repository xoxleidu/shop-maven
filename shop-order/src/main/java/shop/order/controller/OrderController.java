package shop.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.common.util.ExceptionUtil;
import shop.common.util.LayerResult;
import shop.order.pojo.Orders;
import shop.order.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public LayerResult createOrder(@RequestBody Orders orders){
		try {
			LayerResult result = orderService.createOrder(orders, orders.getOrderItems(), orders.getOrderShipping());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return LayerResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}
