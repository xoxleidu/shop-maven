package shop.service;

import shop.common.pojo.LayuiData;
import shop.common.util.LayerResult;
import shop.pojo.TbContent;

public interface ContentService {

	LayuiData queryListByid(Integer limit, Integer page, Long categoryId);

	LayerResult addContent(TbContent tbContent);

}
