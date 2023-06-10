package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.User;
import com.entity.UserOrders;

public interface UserOrdersDAO extends JpaRepository<UserOrders, Long>{
	
	List<UserOrders>findByUser(User user);

}
