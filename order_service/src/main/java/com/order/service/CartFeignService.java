package com.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.order.model.DisplayCart;

@FeignClient(name = "cart-service/cartservice/cart")
public interface CartFeignService {
	
	@GetMapping("/{userId}")
	ResponseEntity<DisplayCart> displayCart(@PathVariable int userId);

}

