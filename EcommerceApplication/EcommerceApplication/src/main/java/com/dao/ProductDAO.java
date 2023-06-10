package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Long>{

}
