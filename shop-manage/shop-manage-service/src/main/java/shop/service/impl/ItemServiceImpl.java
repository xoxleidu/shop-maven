package shop.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import shop.common.pojo.LayuiData;
import shop.common.util.IDUtils;
import shop.common.util.LayerResult;
import shop.mapper.TbItemDescMapper;
import shop.mapper.TbItemMapper;
import shop.mapper.TbItemParamItemMapper;
import shop.pojo.TbItem;
import shop.pojo.TbItemDesc;
import shop.pojo.TbItemExample;
import shop.pojo.TbItemExample.Criteria;
import shop.pojo.TbItemParamItem;
import shop.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
	
	@Override
	public TbItem getItemById(Long itemId) {

		TbItemExample tbItemExample = new TbItemExample();
		Criteria createCriteria = tbItemExample.createCriteria();
		createCriteria.andIdEqualTo(itemId);
		
		List<TbItem> list = tbItemMapper.selectByExample(tbItemExample);
		
		if (list != null && list.size() > 0) {
			TbItem item = list.get(0);
			return item;
		}

		return null;
	}
	
	@Override
	public void addItem(TbItem tbItem, String desc,String itemParams) throws Exception{
		
			//生成商品id
			//可以使用redis的自增长key，在没有redis之前使用时间+随机数策略生成
			Long itemId = IDUtils.genItemId();
			//补全不完整的字段
			tbItem.setId(itemId);
			
			Date date = new Date();
			tbItem.setCreated(date);
			tbItem.setUpdated(date);
			//把数据插入到商品表
			itemMapper.insert(tbItem);
			
			//添加商品描述信息
			LayerResult result = addItemDesc(itemId, desc);
			if (result.getStatus() != 200) {
				throw new Exception();
			}
			//添加规格参数
			result = addItemParamItem(itemId, itemParams);
			if (result.getStatus() != 200) {
				throw new Exception();
			}

	}
	
	private LayerResult addItemDesc(Long itemId,String desc) {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		return LayerResult.ok();
	}
	
	private LayerResult addItemParamItem(Long itemId, String itemParams) {
		//创建一个pojo
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParams);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		//向表中插入数据
		tbItemParamItemMapper.insert(itemParamItem);
		
		return LayerResult.ok();
		
	}

	@Override
	public LayuiData getItemList(int page,int limit) {

		//查询商品列表
		TbItemExample tbItemExample = new TbItemExample();
		//分页处理
		PageHelper.startPage(page,limit);
		List<TbItem> list = tbItemMapper.selectByExample(tbItemExample);
		//创建一个返回值对象
		LayuiData myPage = new LayuiData();
		myPage.setData(list);
		//取记录总条数
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		myPage.setCount(pageInfo.getTotal());
		
		return myPage;
	}


}
