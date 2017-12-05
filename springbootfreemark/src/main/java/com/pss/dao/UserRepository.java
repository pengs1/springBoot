package com.pss.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pss.domain.User;

/**
 * User repository interface
 * 
 * @author songsong.peng
 *
 */
public interface UserRepository extends JpaRepository<User,Integer> {
	
	// Get user list by age
	List<User> findByAge(Integer age); 
}