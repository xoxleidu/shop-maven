package shop.service;

import shop.common.pojo.LayuiData;
import shop.pojo.TbItem;

public interface ItemService {

	TbItem getItemById(Long itemId);
	
	LayuiData getItemList(int page,int limit);

	void addItem(TbItem tbItem, String desc,String itemParams) throws Exception;
}
