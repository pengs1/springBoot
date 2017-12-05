package com.pss.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pss.dao.UserRepository;
import com.pss.domain.User;
import com.pss.property.UserProperties;
import com.pss.utils.ContentDirective;
import com.pss.utils.PengsDirective;
import com.pss.utils.SortMethod;
import com.pss.utils.SortUser;

@Controller 
public class FreemarkerPageController {
	
	@Autowired
	private ContentDirective contentDirective;
	
	@Autowired
	private PengsDirective pengsDirective;
	
	@Value("${size}")
	private String cupSize;
	
	@Value("${age}")
	private Integer age;
	
	@Value("${content}")
	private String content;
	
	@Autowired
	private UserProperties userProperties;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/free")
	public String hello(Map<String,Object> map){
		map.put("name", "[Angel -- Bautifull]");
		return "hello";
	}
	
	@RequestMapping("/userlist")
	public String userList(Map<String,Object> map) {
		List<User> userList = userRepository.findAll();
		map.put("user", userList.get(0));
		map.put("size", userProperties.getName());
		return "user_list";
	}
	
	@RequestMapping("/allUsers")
	public String allUserList(Map<String,Object> map) {
		List<User> userList = userRepository.findAll();
		Map<String, String> userMap = new HashMap<String, String>();
		for (User user : userList) {
			userMap.put(user.getId().toString(), user.getName());
		}
		map.put("users", userList);
		map.put("userMap", userMap);
		map.put("content", contentDirective);
		map.put("booleanVar", Boolean.FALSE);
		map.put("dateVar", new Date());
		map.put("nullVar", "having value");
		map.put("sortInt", new SortMethod());
		map.put("sortUser", new SortUser());
		map.put("pengs", pengsDirective);
		return "user";
	}
	
	@GetMapping("comm")
	public String testMacro() {
		return "macroTest";
	}
	
	@GetMapping("media")
	public String testMedia() {
		return "media";
	}
}
