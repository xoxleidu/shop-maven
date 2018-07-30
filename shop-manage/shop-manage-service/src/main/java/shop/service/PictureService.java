package shop.service;

import org.springframework.web.multipart.MultipartFile;

import shop.common.pojo.LayuiUploadImages;

public interface PictureService {

	LayuiUploadImages upload(MultipartFile uploadFile);
}
