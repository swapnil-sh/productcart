package com.order.service.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.order.entity.User;
import com.order.model.DisplayCart;
import com.order.model.OrderResponse;
import com.order.repository.CartRepository;
import com.order.repository.OrderRepository;
import com.order.repository.UserRepository;
import com.order.service.CartFeignService;
import com.order.service.OrderService;
import com.order.service.ProductFeignService;
import com.order.entity.*;

@Service
public class OrderServiceImplement implements OrderService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImplement.class);

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductFeignService productFeignService;
	
	@Autowired
	private CartFeignService cartFeignService;

	@Override
	public OrderResponse checkout(int userId) {
		
		//user fetched
		User user = userRepository.getById(userId);
		
		//given user's cart is fetched
		List<Cart> cartList = cartRepository.findByUser(user);
		List<Products> productList = new ArrayList<>();
		long totalAmount = 0L;
		for (Cart cart : cartList) {
			totalAmount += (cart.getQuantity()*cart.getProducts().get(0).getPrice());
			productList.add(cart.getProducts().get(0));
		}
		LOGGER.info("Total bill amount : {}",totalAmount);
		
		Order order = new Order();
		order.setTotalAmount(totalAmount);
		order.setUsers(user);
		order.setProducts(productList);
		orderRepository.save(order);
		
		//reducing the count of items in database after successful purchase
		cartList.stream().forEach(cart -> productFeignService.reduceCount(cart.getProducts().get(0).getId(),cart.getQuantity()));
		LOGGER.info("Product availability changed in db");
				
		ResponseEntity<DisplayCart> displayCart = cartFeignService.displayCart(userId);
		List<Cart> x = displayCart.getBody().getCartList();		
		
		OrderResponse orderResponse = new OrderResponse();
		
		orderResponse.setId(order.getId());
		orderResponse.setTotalAmount(totalAmount);
		orderResponse.setUserId(user.getId());
		orderResponse.setDate(new Date());
		orderResponse.setProductsPurchased(x.stream().map(cart->cart.getProducts().get(0).getProductName()).toList());
		
		//deleting the cart for this user after successful purchase
		cartRepository.deleteAllInBatch(cartList);
		LOGGER.info("Cart is deleted after successful order");
		
		return orderResponse;
		
		
	}

}
