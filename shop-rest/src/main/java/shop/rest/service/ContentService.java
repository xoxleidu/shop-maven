package shop.rest.service;

import java.util.List;

import shop.pojo.TbContent;

public interface ContentService {

	List<TbContent> getContentList(long categoryId);
}
