package com.service;

import java.util.List;

import com.entity.User;
import com.entity.UserOrders;

public interface OrderService {
	
	List<UserOrders>getOrders(User user);

}
