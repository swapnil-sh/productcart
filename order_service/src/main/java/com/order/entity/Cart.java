package com.order.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "products_cart",
	joinColumns = {@JoinColumn(name = "cart_id")},
	inverseJoinColumns = {@JoinColumn(name="product_id")})
	private List<Products> products = new ArrayList<>();
	private int quantity;
	
	public Cart(int id, User user, List<Products> products, int quantity) {
		super();
		this.id = id;
		this.user = user;
		this.products = products;
		this.quantity = quantity;
	}
	public Cart() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	public List<Products> getProducts() {
		return products;
	}
	public void setProducts(List<Products> products) {
		this.products = products;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
