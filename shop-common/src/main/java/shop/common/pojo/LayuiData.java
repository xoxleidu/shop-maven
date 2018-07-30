package shop.common.pojo;

import java.util.List;

/**
 * 
 * @author xox
 *	code: 0, //状态码，0代表成功，其它失败
	msg: "", //状态信息，一般可为空
	count: 1000, //数据总量
	data: [] //数据，字段是任意的。
 */
public class LayuiData {

	private int code;
	private String msg;
	
	@Override
	public String toString() {
		return "LayuiData [data=" + data + "]";
	}

	private long count;
	
	private List<?> data;

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

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}
	
	

}
