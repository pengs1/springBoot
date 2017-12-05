package com.pss.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.tomcat.util.bcel.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pss.dao.UserRepository;
import com.pss.domain.Page;
import com.pss.domain.Result;
import com.pss.domain.User;
import com.pss.service.UserService;
import com.pss.utils.ResultUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final Logger LOGGER  = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("queryAll")
	public List<User> queryAll(){
		List<User> list = new ArrayList<User>();
		list = userRepository.findAll();
		return list;
	}
	
	@PostMapping("add")
	public List<User> saveUser() {
		List<User> list = new ArrayList<User>();
		User user1 = new User();
		user1.setName("haha");
		user1.setAge(20);
		user1.setAdress("china");
		user1.setGender("1");
		user1.setStatus(false);
		user1.setCreateTimestamp(new Date());
		user1.setCreateUserId(0L);
		user1.setUpdateTimestamp(new Date());
		user1.setUpdateUserId(0L);
		list.add(user1);
		return userRepository.save(list);
	}
	
	@GetMapping(value="get/{id}")
	public User getUserById(@PathVariable("id") Integer id) {
		LOGGER.info("Start to get User by Id:{}", id);
		return userRepository.findOne(id);
	}
	
	/**
	 * This method is used to update user name by id
	 * 
	 * @param id the user id
	 * @param name the string name of user
	 * 
	 * @return the updated user object
	 */
	@PutMapping(value="update/{id}")
	public User updateUserById(@PathVariable("id") Integer id, @RequestParam("name") String name) {
		User user = userRepository.findOne(id);
		user.setId(id);
		user.setName(name);
		
		return userRepository.save(user);
	}
	
	@GetMapping(value="getUsers")
	public List<User> getUsersByAge(@RequestParam("age") Integer age) {
		return userRepository.findByAge(age);
	}
	
	@GetMapping(value="delete")
	public void deleteUserById(@RequestParam("id") Integer id) {
		LOGGER.info("Start to delete user by id:{}",id);
		userRepository.delete(id);
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping(value="addUser")
	public <T> Result<T> addValidUser(@Valid User user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			LOGGER.info(bindingResult.getFieldError().getDefaultMessage());
			return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
		}
		user.setAdress(user.getAdress());
		user.setAge(user.getAge());
		user.setName(user.getName());
		return  ResultUtil.success((T)userRepository.save(user));
	}
	
	@GetMapping(value="getEx")
	public void getUser(@RequestParam("id") Integer id) throws Exception {
		userService.getUserInformation(id);
	}
	
	
	@RequestMapping(value = "list")
	public String listSourceCode(HttpServletRequest request, HttpServletResponse response, Map<String,Object> map){
		String pageNumberStr=request.getParameter("pageNumber");
		if(pageNumberStr==null ||"".equals(pageNumberStr)){
			pageNumberStr="1";
		}
		int pageNumber = Integer.parseInt(pageNumberStr);
		int pageSize = 5;
		Page<List<User>> sourceCodes = this.userService.getUsers(pageNumber, pageSize);
		
		map.put("userList",sourceCodes.getContents());
		map.put("totalPageNumber",sourceCodes.getTotalPages());
		map.put("pageSize",pageSize);
		return "pagingUsers";
	}
	
	
	/**
	 * Only for Junit test 
	 * 
	 * @param userRepository
	 */
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	/**
	 * Only for Junit test case
	 * 
	 * @param userService
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
