package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ProductDAO;
import com.entity.Product;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
	private ProductDAO productDAO;
    
    
	@Override
	public List<Product> getProducts() {
		return productDAO.findAll();
	}

}
