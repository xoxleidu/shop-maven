package shop.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import shop.common.util.CookieUtils;
import shop.common.util.HttpClientUtil;
import shop.common.util.JsonUtils;
import shop.common.util.LayerResult;
import shop.pojo.TbItem;
import shop.portal.service.CartService;
import shop.search.pojo.CartItem;

@Service
public class CartServiceImpl implements CartService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;
	
	@Override
	public LayerResult addCartItem(long itemId, int num,HttpServletRequest request,HttpServletResponse response) {
		
		CartItem cartItem = null;
		
		List<CartItem> list = getItemListByCookie(request);
		
		for (CartItem cartItem2 : list) {
			if (cartItem2.getId() == itemId) {
				cartItem2.setNum(cartItem2.getNum() + num);
				cartItem = cartItem2;
				break;
			}
		}
		
		if (cartItem == null) {
			
			cartItem = new CartItem();
			String doGet = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
			LayerResult result = LayerResult.formatToPojo(doGet, TbItem.class);
			
			if (result.getStatus() == 200) {
				TbItem data = (TbItem) result.getData();
				cartItem.setId(data.getId());
				cartItem.setImage(data.getImage() == null ? "" : data.getImage().split(",")[0]);
				//cartItem.setImage(data.getImage());
				cartItem.setNum(num);
				cartItem.setPrice(data.getPrice());
				cartItem.setTitle(data.getTitle());
			}
			list.add(cartItem);
		}
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(list), true);
		return LayerResult.ok();
		
	}
	
	@Override
	public List<CartItem> getItemListByCookie(HttpServletRequest request) {
		String cookieValue = CookieUtils.getCookieValue(request, "TT_CART", true);
		if (cookieValue == null) {
			return new ArrayList<CartItem>();
		}
		try {
			List<CartItem> list = JsonUtils.jsonToList(cookieValue, CartItem.class);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<CartItem>();
	}

	@Override
	public LayerResult updateCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> list = getItemListByCookie(request);
		for (CartItem cartItem : list) {
			if (cartItem.getId() == itemId) {
				cartItem.setNum(num);
				break;
			}
		}
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(list), true);
		return LayerResult.ok();
	}

	@Override
	public LayerResult delCartItem(long itemId, HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> list = getItemListByCookie(request);
		for (CartItem cartItem : list) {
			if (cartItem.getId() == itemId) {
				list.remove(cartItem);
				break;
			}
		}
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(list), true);
		return LayerResult.ok();
	}

}
