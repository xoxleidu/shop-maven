package shop.service;

import java.util.List;

import shop.common.pojo.ZTreeNode;
import shop.pojo.TbContentCategory;

public interface ContentCategoryService {

	List<ZTreeNode> getCategoryList(long parentId);

	TbContentCategory insertContentCategory(Long parentId, String name);
}
