package com.pss.exception;

import com.pss.enums.ResultEnum;

public class TDException extends RuntimeException {

	private static final long serialVersionUID = 6631448804523853231L;
	
	private Integer code;
	
	/**
	 * Constructor of TDException.
	 */
	public TDException() {
		super();
	}
	
	public TDException(String message) {
		super(message);
	}
	
	public TDException(ResultEnum resultEnum) {
		super(resultEnum.getMessage());
		this.code = resultEnum.getCode();
	}
	
	public TDException(Integer code, String message) {
		super(message);
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
