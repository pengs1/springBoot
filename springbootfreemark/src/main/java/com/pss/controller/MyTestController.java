package com.pss.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/index")
public class MyTestController {
	
	private static final Logger LOGGER  = LoggerFactory.getLogger(MyTestController.class);
	
	@GetMapping(value="/hello/{id}")
	public String index1(@PathVariable("id") Integer myId) {
		LOGGER.info("test");
		return "Hello World1! "+ myId;
	}
	
	@GetMapping(value="/{id}/hello")
	public String index2(@PathVariable("id") Integer myId) {
		LOGGER.info("test");
		return "Hello World2! "+ myId;
	}
	
	@GetMapping(value={"/hello", "/ooo"})
	public String index3(@RequestParam(value="id", required=false, defaultValue="0") Integer myId) {
		LOGGER.info("test");
		return "Hello World3! "+ myId;
	}
	
	@RequestMapping(value="/getMap")
	public Map<String, String> getMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "value1");
		map.put("2", "value2");
		map.put("3", "value3");
		return map;
		
	}
}
