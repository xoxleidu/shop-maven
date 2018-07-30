package shop.service.impl;


import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import shop.common.pojo.LayuiUploadImages;
import shop.common.util.ExceptionUtil;
import shop.common.util.FtpUtil;
import shop.common.util.IDUtils;
import shop.service.PictureService;

@Service
public class PictureServiceImpl implements PictureService {

	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	@Value("${FTP_ALPHA_PATH}")
	private String FTP_ALPHA_PATH;
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
	@Value("${IMAGE_ALPHA_URL}")
	private String IMAGE_ALPHA_URL;
	
	@Override
	public LayuiUploadImages upload(MultipartFile uploadFile) {
		
		LayuiUploadImages luli = new LayuiUploadImages();
		
		//判断上传图片是否为空
		if (null == uploadFile || uploadFile.isEmpty()) {
			luli.setMsg("上传图片为空");
			return luli;
		}
		//取文件扩展名
		String originalFilename = uploadFile.getOriginalFilename();
		String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
		//生成新文件名
		//可以使用uuid生成新文件名。
		//UUID.randomUUID()
		//可以是时间+随机数生成文件名
		String imageName = IDUtils.genImageName();
		//把图片上传到ftp服务器（图片服务器）
		//需要把ftp的参数配置到配置文件中
		//文件在服务器的存放路径，应该使用日期分隔的目录结构
		DateTime dateTime = new DateTime();
		String filePath = dateTime.toString("/yyyy/MM/dd");
		String src = IMAGE_BASE_URL + filePath + "/" + imageName + ext;
		try {
			FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, 
					FTP_BASE_PATH, filePath, imageName + ext, uploadFile.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
			luli.setMsg(ExceptionUtil.getStackTrace(e));
			return luli;
		}
		
		luli.setCode(0);
		luli.setMsg("上传成功");
			
		//List<LayuiUploadImagesData> dataList = new ArrayList<>();
		//LayuiUploadImagesData lulid = new LayuiUploadImagesData();
		//lulid.setSrc(IMAGE_BASE_URL + filePath + "/" + imageName + ext);
		//dataList.add(lulid);
			
		Map<String,String> mapdata = new HashMap<String, String>();
		mapdata.put("src", src);
			
		luli.setData(mapdata);
		
		return luli;

	}

	

}
