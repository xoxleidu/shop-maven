package shop.service;

import shop.common.pojo.LayuiData;
import shop.common.util.LayerResult;
import shop.pojo.TbItemParam;

public interface ItemParamService {

	LayuiData getParamList(Integer page, Integer limit);

	LayerResult getParamByCid(Long itemCatId);
	
	LayerResult addParam(TbItemParam tbItemParam);

}
