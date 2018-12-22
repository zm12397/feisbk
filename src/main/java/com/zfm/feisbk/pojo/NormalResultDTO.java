package com.zfm.feisbk.pojo;

import java.io.Serializable;

/**
 *
 */
public class NormalResultDTO  implements Serializable{
	/**
	 * 0000：成功
	 * 9999：错误
	 */
	private String code;
	/**
	 * 成功信息或错误信息
	 */
	private String message;
	/**
	 * 成功信息携带的数据集
	 */
	private Object data;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public NormalResultDTO(String code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}
	public NormalResultDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "NormalResultDTO [code=" + code + ", message=" + message + ", data=" + data + "]";
	}
	
}
