package com.pss.enums;

public enum HttpRequestType {
	
	POST_TYPE("post"),
	
	GET_TYPE("get"),
	
	PUT_TYPE("put"),
	
	DELETE_TYPE("delete");
	
	private String defaultName;
	
	private HttpRequestType(String name) {
		this.defaultName = name;
	}

	/**
	 * Get the value of enum name.
	 * 
	 * @return enum name
	 */
	public String getName() {
		return this.name();
	}

	/**
	 * Get the value of or reference to defaultName.
	 * 
	 * @return the defaultName
	 */
	public String getDefaultName() {
		return this.defaultName;
	}
}
