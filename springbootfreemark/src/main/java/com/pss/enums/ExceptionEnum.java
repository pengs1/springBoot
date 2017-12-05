package com.pss.enums;

public enum ExceptionEnum {
	
	NULL_BODY("The Template Directive Body Is Null");
	
	
	private String name;
	
	ExceptionEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	};
}
