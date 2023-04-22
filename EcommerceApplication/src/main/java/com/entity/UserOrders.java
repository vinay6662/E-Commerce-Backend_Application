package com.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class UserOrders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "customer_id")
	private User user;

	@ManyToOne(optional = false)
	@JoinColumn(name = "address_id")
	private Address address;

	@OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<OrderQuantity> quantities = new ArrayList();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<OrderQuantity> getQuantities() {
		return quantities;
	}

	public void setQuantities(List<OrderQuantity> quantities) {
		this.quantities = quantities;
	}

	public UserOrders(int id, User user, Address address, List<OrderQuantity> quantities) {
		super();
		this.id = id;
		this.user = user;
		this.address = address;
		this.quantities = quantities;
	}

	public UserOrders() {

	}

	@Override
	public String toString() {
		return "UserOrders [id=" + id + ", user=" + user + ", address=" + address + ", quantities=" + quantities + "]";
	}



}
