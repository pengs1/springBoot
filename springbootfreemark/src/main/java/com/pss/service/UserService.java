package com.pss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.pss.dao.IUserDao;
import com.pss.dao.UserRepository;
import com.pss.domain.Page;
import com.pss.domain.User;
import com.pss.enums.ResultEnum;
import com.pss.exception.TDException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private IUserDao userDao;
	
	/**
	 * This method is used to throw difference exception according age
	 * 
	 * @param id the id for patient
	 * @throws Exception
	 */
	public void getUserInformation(Integer id) throws Exception{
		User user = userRepository.findOne(id);
		if(user.getAge()<=30) {
			throw new TDException(ResultEnum.LOW_AGE);
		} else if(user.getAge()>30 && user.getAge()<=50) {
			throw new TDException(ResultEnum.MIDDLE_AGE);
		} else {
			throw new TDException(ResultEnum.HIGH_AGE);
		}
	}
	
	/**
	 * Find user list for paging. 
	 * 
	 * @param pageNumber the number of current page
	 * @param pageSize the page size
	 * @return page object contians user list
	 */
	public Page<List<User>> getUsers(int pageNumber,int pageSize){
		PageRequest request = this.buildPageRequest(pageNumber,pageSize);
		Page<List<User>> sourceCodes= (Page<List<User>>) this.userDao.findAll(request);
		return sourceCodes;
	}
	
	/**
	 * This method is used to create a pagerequest instance
	 * 
	 * @param pageNumber
	 * @param pagzSize
	 * @return the instance of PageRequest
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize) {
		return new PageRequest(pageNumber - 1, pagzSize, null);
	}
	
	/**
	 * only for Junit test
	 * 
	 * @param userRepository the instance of UserRepository
	 */
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
}
