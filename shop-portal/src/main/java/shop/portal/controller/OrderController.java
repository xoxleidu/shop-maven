package shop.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import shop.common.util.ExceptionUtil;
import shop.pojo.TbUser;
import shop.portal.service.CartService;
import shop.portal.service.OrderService;
import shop.search.pojo.CartItem;
import shop.search.pojo.Orders;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/order-cart")
	public String showOrder(HttpServletRequest request,HttpServletResponse response,Model model){
		List<CartItem> list = cartService.getItemListByCookie(request);
		model.addAttribute("cartList", list);
		return "order-cart";
	}
	
	@RequestMapping("/create")
	public String showCreate(Orders orders,Model model,HttpServletRequest request){
		try {
			//去用户信息并补充完整
			TbUser user = (TbUser) request.getAttribute("user");
			orders.setUserId(user.getId());
			orders.setBuyerNick(user.getUsername());
			
			String orderId = orderService.createOrder(orders);
			model.addAttribute("orderId", orderId);
			model.addAttribute("payment", orders.getPayment());
			model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", ExceptionUtil.getStackTrace(e));
			return "error/exception";
		}
	}
	
	
}
