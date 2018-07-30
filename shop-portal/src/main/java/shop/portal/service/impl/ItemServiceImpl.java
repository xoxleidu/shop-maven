package shop.portal.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import shop.common.util.HttpClientUtil;
import shop.common.util.JsonUtils;
import shop.common.util.LayerResult;
import shop.pojo.TbItemDesc;
import shop.pojo.TbItemParamItem;
import shop.portal.service.ItemService;
import shop.search.pojo.ItemInfo;

@Service
public class ItemServiceImpl implements ItemService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;
	@Value("${ITEM_DESC_URL}")
	private String ITEM_DESC_URL;
	@Value("${ITEM_PARAM_URL}")
	private String ITEM_PARAM_URL;
	
	@Override
	public ItemInfo getItemById(Long itemId) {
		try {
			String doGet = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
			if (!StringUtils.isBlank(doGet)) {
				LayerResult itemInfo = LayerResult.formatToPojo(doGet, ItemInfo.class);
				if (itemInfo.getStatus() == 200) {
					ItemInfo item = (ItemInfo) itemInfo.getData();
					return item;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getItemDescById(Long itemId) {
		try {
			String doGet = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_URL + itemId);
			if (!StringUtils.isBlank(doGet)) {
				LayerResult itemDesc = LayerResult.formatToPojo(doGet, TbItemDesc.class);
				if (itemDesc.getStatus() == 200) {
					TbItemDesc tbItemDesc = (TbItemDesc) itemDesc.getData();
					String desc = tbItemDesc.getItemDesc();
					return desc;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getItemParam(Long itemId) {
		try {
			String doGet = HttpClientUtil.doGet(REST_BASE_URL + ITEM_PARAM_URL + itemId);
			if (!StringUtils.isBlank(doGet)) {
				LayerResult itemParam = LayerResult.formatToPojo(doGet, TbItemParamItem.class);
				if (itemParam.getStatus() == 200) {
					TbItemParamItem tbItemParamItem = (TbItemParamItem) itemParam.getData();
					String param = tbItemParamItem.getParamData();
					
					//生成html
					// 把规格参数json数据转换成java对象
					List<Map> jsonList = JsonUtils.jsonToList(param, Map.class);
					StringBuffer sb = new StringBuffer();
					sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
					sb.append("    <tbody>\n");
					for(Map m1:jsonList) {
						sb.append("        <tr>\n");
						sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
						sb.append("        </tr>\n");
						List<Map> list2 = (List<Map>) m1.get("params");
						for(Map m2:list2) {
							sb.append("        <tr>\n");
							sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
							sb.append("            <td>"+m2.get("v")+"</td>\n");
							sb.append("        </tr>\n");
						}
					}
					sb.append("    </tbody>\n");
					sb.append("</table>");
					
					return sb.toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
