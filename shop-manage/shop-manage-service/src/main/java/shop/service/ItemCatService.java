package shop.service;

import java.util.List;

import shop.common.pojo.LayuiTree;

public interface ItemCatService {

	List<LayuiTree> getItemCatList(long parentId);
	
}
