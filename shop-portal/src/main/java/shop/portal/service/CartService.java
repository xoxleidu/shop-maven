package shop.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.common.util.LayerResult;
import shop.search.pojo.CartItem;

public interface CartService {

	LayerResult addCartItem(long itemId,int num,HttpServletRequest request,HttpServletResponse response);
	
	List<CartItem> getItemListByCookie (HttpServletRequest request);
	
	LayerResult updateCartItem(long itemId,int num,HttpServletRequest request,HttpServletResponse response);
	
	LayerResult delCartItem(long itemId,HttpServletRequest request,HttpServletResponse response);
}
