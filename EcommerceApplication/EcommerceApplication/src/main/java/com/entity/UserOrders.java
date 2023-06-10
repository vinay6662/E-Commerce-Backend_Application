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
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "customer_id")
	private User user;

	@ManyToOne(optional = false)
	@JoinColumn(name = "address_id")
	private Address address;

	@OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<UserOrderQuantity> quantities = new ArrayList();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public List<UserOrderQuantity> getQuantities() {
		return quantities;
	}

	public void setQuantities(List<UserOrderQuantity> quantities) {
		this.quantities = quantities;
	}

	public UserOrders(Long id, User user, Address address, List<UserOrderQuantity> quantities) {
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
