package com.pss.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pss.domain.User;

public interface IUserDao extends JpaRepository<User, Integer> {

}
