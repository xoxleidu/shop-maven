package shop.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.common.util.LayerResult;
import shop.mapper.TbItemMapper;
import shop.pojo.TbItem;
import shop.pojo.TbItemExample;
import shop.pojo.TbItemExample.Criteria;
import shop.rest.service.ProductsService;

@Service
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	private TbItemMapper tbItemMapper;
	
	@Override
	public LayerResult getProducts(long cid) {
		TbItemExample example = new TbItemExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andCidEqualTo(cid);
		
		List<TbItem> list = tbItemMapper.selectByExample(example);
		
		return LayerResult.ok(list);
	}

}
