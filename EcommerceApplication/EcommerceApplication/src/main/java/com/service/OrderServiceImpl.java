package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserOrdersDAO;
import com.entity.User;
import com.entity.UserOrders;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private UserOrdersDAO userOrderDAO;
	
	
	@Override
	public List<UserOrders> getOrders(User user) {
		return userOrderDAO.findByUser(user);
	}

}
