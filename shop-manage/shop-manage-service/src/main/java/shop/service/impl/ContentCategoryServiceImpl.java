package shop.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.common.pojo.ZTreeNode;
import shop.mapper.TbContentCategoryMapper;
import shop.pojo.TbContentCategory;
import shop.pojo.TbContentCategoryExample;
import shop.pojo.TbContentCategoryExample.Criteria;
import shop.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired 
	private TbContentCategoryMapper contentCategoryMapper;
	
	@Override
	public List<ZTreeNode> getCategoryList(long parentId) {
		//根据parentid查询节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<ZTreeNode> resultList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			//创建一个节点
			ZTreeNode node = new ZTreeNode();
			node.setId(tbContentCategory.getId().toString());
			node.setName(tbContentCategory.getName());
			node.setIsParent(tbContentCategory.getIsParent());
			
			resultList.add(node);
		}
		return resultList;

	}

	@Override
	public TbContentCategory insertContentCategory(Long parentId, String name) {
		TbContentCategory tbContentCategory = new TbContentCategory();
		tbContentCategory.setCreated(new Date());
		tbContentCategory.setIsParent(false);
		tbContentCategory.setName(name);
		tbContentCategory.setParentId(parentId);
		tbContentCategory.setSortOrder(1);
		tbContentCategory.setStatus(1);
		tbContentCategory.setUpdated(new Date());
		
		contentCategoryMapper.insert(tbContentCategory);
		
		TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
		
		if (!parentCat.getIsParent()) {
			parentCat.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(parentCat);
		}
		
		return tbContentCategory;
		
		
		/**
		 * mapper文件增加 自增长ID的返回
		 * 
		 * <selectKey keyProperty="id" resultType="long" order="AFTER">
		 * SELECT LAST_INSETR_ID()
		 * </selectKey>
		 * 
		 */
	}

}
