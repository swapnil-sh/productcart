package com.cart.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cart.entity.Products;

@FeignClient(name = "product-service/productservice/products")
public interface FeignService {

	@GetMapping("/quantity/{productId}")
	public int getQuantity(@PathVariable int productId);

	@GetMapping("/{productId}")
	public Products getProduct(@PathVariable int productId);
	
	
}
