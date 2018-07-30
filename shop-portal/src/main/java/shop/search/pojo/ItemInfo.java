package shop.search.pojo;

import shop.pojo.TbItem;

public class ItemInfo extends TbItem {

	//详情页图片分割
	public String[] getImages() {
		String image = getImage();
		if (image != null) {
			String[] images = image.split(",");
			return images;
		}
		return null;
	}
	
}
