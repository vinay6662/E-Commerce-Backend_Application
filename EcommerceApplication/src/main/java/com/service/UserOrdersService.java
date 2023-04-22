package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserOrdersDAO;
import com.entity.User;
import com.entity.UserOrders;

@Service
public class UserOrdersService{

	@Autowired
	private UserOrdersDAO userOrdersDAO;
	
	
	
	public List<UserOrders> getUserOrders(User user) {
		List<UserOrders> userOrders = userOrdersDAO.findBy(user);
		return userOrders;
	}

}
