package shop.rest.service;

import shop.common.util.LayerResult;

public interface ItemService {

	LayerResult getItemBaseInfo(long itemId);
	LayerResult getItemDesc(long itemId);
	LayerResult getItemParam(long itemId);
}
