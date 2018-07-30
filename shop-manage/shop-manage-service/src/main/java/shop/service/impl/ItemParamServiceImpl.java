package shop.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import shop.common.pojo.LayuiData;
import shop.common.util.LayerResult;
import shop.mapper.TbItemParamMapper;
import shop.pojo.TbItemParam;
import shop.pojo.TbItemParamExample;
import shop.pojo.TbItemParamExample.Criteria;
import shop.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper tbItemParamMapper;
	
	@Override
	public LayuiData getParamList(Integer page, Integer limit) {

		TbItemParamExample tbItemParamExample = new TbItemParamExample();
		PageHelper.startPage(page, limit);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);
		
		LayuiData myPage = new LayuiData();
		myPage.setData(list);
		
		PageInfo<TbItemParam> pageInfo = new PageInfo<TbItemParam>(list);
		myPage.setCount(pageInfo.getTotal());
		return myPage;
		
		
	}

	@Override
	public LayerResult getParamByCid(Long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		//判断是否查询到结果
		if (list != null && list.size() > 0) {
			return LayerResult.ok(list.get(0));
		}
		
		return LayerResult.ok();

	}

	@Override
	public LayerResult addParam(TbItemParam tbItemParam) {

		tbItemParam.setCreated(new Date());
		tbItemParam.setUpdated(new Date());
		
		tbItemParamMapper.insert(tbItemParam);
		
		return LayerResult.ok();

	}

}
