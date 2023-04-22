package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.User;
import com.entity.UserOrders;

@Repository
public interface UserOrdersDAO extends JpaRepository<UserOrders,Integer>{
	
	List<UserOrders>findBy(User user);

}
