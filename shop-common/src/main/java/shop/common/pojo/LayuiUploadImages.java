package shop.common.pojo;

import java.util.Map;

public class LayuiUploadImages {

	/*{
	  "code": 0
	  ,"msg": ""
	  ,"data": {
	    "src": "http://cdn.layui.com/123.jpg"
	  }
	} */      
	private int code;
	private String msg;
	private Map<String, String> data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<String, String> getData() {
		return data;
	}
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	

	
	
}
