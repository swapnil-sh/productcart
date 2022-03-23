package com.cart.service;

import java.util.List;

import com.cart.entity.Cart;
import com.cart.model.AddItemRequest;

public interface CartService {

	String deleteItem(AddItemRequest requestBody);

	String addItem(AddItemRequest requestBody);

	List<Cart> getCart(int userId);

}
