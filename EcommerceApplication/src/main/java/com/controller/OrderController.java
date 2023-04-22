package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;
import com.entity.UserOrders;
import com.service.UserOrdersService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private UserOrdersService userOrdersService;
	
	@GetMapping
	public List<UserOrders>getOrders(@AuthenticationPrincipal User user){
		List<UserOrders> userOrders = userOrdersService.getUserOrders(user);
		return userOrders;
		
	}
	
	

}
