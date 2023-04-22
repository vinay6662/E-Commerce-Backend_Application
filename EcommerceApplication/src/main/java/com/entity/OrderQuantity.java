package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderQuantity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int quantity;

	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id_")
	private Product product;

	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name = "order_id")
	private UserOrders order;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public UserOrders getOrder() {
		return order;
	}

	public void setOrder(UserOrders order) {
		this.order = order;
	}

	public OrderQuantity(int id, int quantity, Product product, UserOrders order) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.product = product;
		this.order = order;
	}

	public OrderQuantity() {

	}

	@Override
	public String toString() {
		return "OrderQuantity [id=" + id + ", quantity=" + quantity + ", product=" + product + ", order=" + order + "]";
	}



}
