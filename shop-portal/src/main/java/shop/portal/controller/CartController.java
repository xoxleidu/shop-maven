package shop.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shop.portal.service.CartService;
import shop.search.pojo.CartItem;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@RequestMapping("/add/{itemId}")
	public String addCartItem(@PathVariable Long itemId,
			@RequestParam(defaultValue="1") Integer num,
			HttpServletRequest request,HttpServletResponse response){
		cartService.addCartItem(itemId, num, request, response);
		return "redirect:/cart/csuccess.html";
	}
	
	@RequestMapping("csuccess")
	public String showSuccess(){
		return "cartsuccess";
	}
	
	@RequestMapping("/cart")
	public String getItemListByCookie(HttpServletRequest request,Model model){
		List<CartItem> list = cartService.getItemListByCookie(request);
		model.addAttribute("cartList", list);
		return "cart";
	}
	
	@RequestMapping("/update/{itemId}")
	public String updateCartItem(@PathVariable Long itemId,
			@RequestParam(defaultValue="1") Integer num,
			HttpServletRequest request,HttpServletResponse response){
		cartService.updateCartItem(itemId, num, request, response);
		return "redirect:/cart/cart.html";
	}
	
	@RequestMapping("/delete/{itemId}")
	public String delCartItem (@PathVariable Long itemId,
			HttpServletRequest request,HttpServletResponse response){
		cartService.delCartItem(itemId, request, response);
		return "redirect:/cart/cart.html";
	}
	
}
