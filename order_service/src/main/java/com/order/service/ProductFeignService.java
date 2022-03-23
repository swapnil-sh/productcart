package com.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.order.entity.Products;

@FeignClient(name = "product-service/productservice/products")
public interface ProductFeignService {
	
	@PutMapping("/reducecount")
	public Products reduceCount(@RequestParam int productId,@RequestParam int quantity);
	
}
