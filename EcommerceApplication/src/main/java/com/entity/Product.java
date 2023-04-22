package com.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "product_name")
	private String productName;
	@Column(name = "short_description")
	private String shortDescription;
	@Column(name = "long_description")
	private String longDescription;
	private double price;

	@OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Inventory inventory;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Product(int id, String productName, String shortDescription, String longDescription, double price,
			Inventory inventory) {
		super();
		this.id = id;
		this.productName = productName;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.price = price;
		this.inventory = inventory;
	}

	public Product() {

	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", shortDescription=" + shortDescription
				+ ", longDescription=" + longDescription + ", price=" + price + ", inventory=" + inventory + "]";
	}



}
