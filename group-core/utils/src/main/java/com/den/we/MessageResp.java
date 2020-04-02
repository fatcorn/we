package com.den.we;

import com.alibaba.fastjson.JSONObject;

public class MessageResp<T> {
	private int code;
	private String message;
	private T data;

	public MessageResp(int code , String msg){
		this.code = code;
		this.message = msg;
	}
	public MessageResp(int code , String msg, T object){
		this.code = code;
		this.message = msg;
		this.data = object;
	}
	public MessageResp() {}

	public static MessageResp success(){
		return new MessageResp(0,"SUCCESS");
	}
	public static MessageResp success(String msg){
		return new MessageResp(0,msg);
	}
	public static MessageResp success(String msg, Object data){
		return new MessageResp(0,msg,data);
	}

	/**
	 * 成功并返回数据类型
	 * @param data 返回的数据
	 * @param <T> 返回的类型
	 * @return
	 */
	public static <T> MessageResp<T> success4Data(T data){
		return new MessageResp(0,"SUCCESS",data);
	}

	public static MessageResp error(int code, String msg){
		return new MessageResp(code,msg);
	}
	public static MessageResp error(String msg){
		return new MessageResp(500,msg);
	}

	public static MessageResp error(MessageCode messageCode, String msg){
		return new MessageResp(messageCode.getCode(), msg);
	}
	public static MessageResp error(MessageCode messageCode){
		return new MessageResp(messageCode.getCode(), messageCode.name());
	}

	public boolean isSuccess(){
		if(code == 0){
			return true;
		} else {
			return false;
		}
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString(){
		return JSONObject.toJSONString(this);
		//return "{\"code\":"+code+",\"message\":\""+message+"\"}";
	}
	public T getData() {
		return this.data;
	}
	public void setData(T data) {
		this.data = data;
	}

	public static MessageResp getSuccessInstance(String message , Object data){
		MessageResp result = success(message);
		result.setData(data);
		return result ;
	}
}
