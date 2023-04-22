package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ProductDAO;
import com.entity.Product;

@Service
public class ProductService{
	
	@Autowired
	private ProductDAO productDAO;
	
	

	public List<Product> getProducts() {
		List<Product> products = productDAO.findAll();
		return products;
	}

}
