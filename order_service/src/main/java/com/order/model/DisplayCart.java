package com.order.model;

import java.util.List;

import com.order.entity.Cart;

public class DisplayCart {

	private List<Cart> cartList;

	public List<Cart> getCartList() {
		return cartList;
	}

	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList;
	}

	public DisplayCart(List<Cart> cartList) {
		super();
		this.cartList = cartList;
	}

	public DisplayCart() {
		
	}
	
	
}
