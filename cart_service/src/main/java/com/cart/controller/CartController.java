package com.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cart.model.AddItemRequest;
import com.cart.model.DisplayCart;
import com.cart.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartServiceImpl;

	
	// Api to add item in the cart
	@PostMapping("/additem")
	public String addItem(@RequestBody AddItemRequest requestBody) {
		return cartServiceImpl.addItem(requestBody);

	}
	
	// Api to remove item in the cart
	@DeleteMapping("/removeitem")
	public String deleteItem(@RequestBody AddItemRequest requestBody) {
		return cartServiceImpl.deleteItem(requestBody);

	}
	
	// Api to display cart for a user
	@GetMapping("/{userId}")
	public DisplayCart getCart(@PathVariable int userId) {
		
		DisplayCart displayCart = new DisplayCart();
		displayCart.setCartList(cartServiceImpl.getCart(userId));
		return displayCart;
	}

}
