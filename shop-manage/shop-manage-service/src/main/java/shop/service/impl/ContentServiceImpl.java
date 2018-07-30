package shop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import shop.common.pojo.LayuiData;
import shop.common.util.LayerResult;
import shop.mapper.TbContentMapper;
import shop.pojo.TbContent;
import shop.pojo.TbContentExample;
import shop.pojo.TbContentExample.Criteria;
import shop.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper tbContentMapper;
	
	@Override
	public LayuiData queryListByid(Integer limit, Integer page, Long categoryId) {
		
		TbContentExample example = new TbContentExample();
		Criteria create = example.createCriteria();
		create.andCategoryIdEqualTo(categoryId);
		
		PageHelper.startPage(page,limit);
		List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
		
		//创建一个返回值对象
		LayuiData myPage = new LayuiData();
		myPage.setData(list);
		
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		myPage.setCount(pageInfo.getTotal());

		return myPage;
	}

	@Override
	public LayerResult addContent(TbContent tbContent) {
		//补全pojo内容
		tbContent.setCreated(new Date());
		tbContent.setUpdated(new Date());
		tbContentMapper.insert(tbContent);
		return LayerResult.ok();
	}

}
