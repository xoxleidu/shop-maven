package shop.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mapper.TbContentMapper;
import shop.pojo.TbContent;
import shop.pojo.TbContentExample;
import shop.pojo.TbContentExample.Criteria;
import shop.rest.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper tbContentMapper;
	
	@Override
	public List<TbContent> getContentList(long categoryId) {
		TbContentExample example = new TbContentExample();
		Criteria create = example.createCriteria();
		create.andCategoryIdEqualTo(categoryId);
		
		List<TbContent> list = tbContentMapper.selectByExample(example);
		
		return list;
	}

}
