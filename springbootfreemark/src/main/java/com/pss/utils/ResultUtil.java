package com.pss.utils;

import com.pss.domain.Result;

public class ResultUtil {
	
	public static <T> Result<T> success(T object) {
		Result<T> result = new Result<T>();
		result.setCode(0);
		result.setMessage("success");
		result.setData(object);
		return result;
	}
	
	public static <T> Result<T> error(String errorMessage) {
		Result<T> result = new Result<T>();
		result.setCode(1);
		result.setMessage(errorMessage);
		return result;
	}
	
	public static <T> Result<T> error(Integer code, String errorMessage) {
		Result<T> result = new Result<T>();
		result.setCode(code);
		result.setMessage(errorMessage);
		return result;
	}
}
