package com.cart.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.cart.entity.Cart;
import com.cart.entity.*;
import com.cart.model.AddItemRequest;
import com.cart.repository.CartRepository;
import com.cart.repository.UserRepository;
import com.cart.service.*;

@Service
public class CartServiceImplement implements CartService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImplement.class);

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FeignService feignService;

	@Override
	public String addItem(AddItemRequest requestBody) {
		try {
		
			Products product = feignService.getProduct(requestBody.getProductId());
			int quantityAvailable = feignService.getQuantity(requestBody.getProductId());

			LOGGER.info("Quantity available : {}",quantityAvailable);

			if (requestBody.getQuantity() > quantityAvailable)
				return "Insufficient quantity available !!!";

			User user = userRepository.getById(requestBody.getUserId());
			Cart cart = cartRepository.findByProductsAndUser(product,user);
			if (cart != null) {
				cart.setQuantity(cart.getQuantity() + requestBody.getQuantity());
				cartRepository.save(cart);
				LOGGER.info("Cart was already present and updated now");
			} else {

				Cart newCart = new Cart();
				newCart.getProducts().add(product);
				newCart.setUser(user);
				newCart.setQuantity(requestBody.getQuantity());
				cartRepository.save(newCart);
				LOGGER.info("New cart is created");
			}
			
			
		} catch (RestClientException e) {

			return e.getMessage();
		}

		return "Product is added to the cart";
	}

	@Override
	public String deleteItem(AddItemRequest requestBody) {

		LOGGER.info("deleteItem() called for productId : {}",requestBody.getProductId());
		//Products product = restTemplate.getForObject(applicationProperties.getProductServiceUrl()+"/"+requestBody.getProductId(), Products.class);
		Products product = feignService.getProduct(requestBody.getProductId());
		
		User user = userRepository.getById(requestBody.getUserId());
		Cart cart = cartRepository.findByProductsAndUser(product, user);
		cart.setQuantity(cart.getQuantity() - requestBody.getQuantity());
		int count = cart.getQuantity();
		LOGGER.info("UPDATE :  cartID = {} , quantity={}",cart.getId(),cart.getQuantity());
		if(count <= 0)
		{
			cartRepository.delete(cart);
		}
		else
		{
			cartRepository.save(cart);
		}

		LOGGER.info("item was removed from cart");
		return "Given item was removed fron the cart";
	}

	@Override
	public List<Cart> getCart(int userId) {
		
		LOGGER.info("inside getCart()");
		User user = userRepository.getById(userId);
		List<Cart> cartList = cartRepository.findByUser(user);
		return cartList.stream().map(cart -> {
				Cart c = new Cart();
				c.setId(cart.getId());
				c.setProducts(cart.getProducts());
				c.setQuantity(cart.getQuantity());
				//c.setUser(user);
				return c;
			}).collect(Collectors.toList());
	}

}
