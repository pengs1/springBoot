package com.pss.enums;

public enum ResultEnum {
	
	UNKNOW_ERROR(-1, "unkonw error"),
	
	SUCCESS(1, "Success"),
	
	LOW_AGE(100, "Age is smaller than 30!"),
	
	MIDDLE_AGE(200, "Age is smaller than 50, larger than 30!"),
	
	HIGH_AGE(300, "Age is larger than 50!");
	
	
	private Integer code;
	
	private String message;
	
	ResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
