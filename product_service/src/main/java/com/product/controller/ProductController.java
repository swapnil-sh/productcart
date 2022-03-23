package com.product.controller;

import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.model.Products;
import com.product.repository.ProductRepository;
import com.product.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/")
	public List<Products> getAllProducts()
	{
		LOGGER.info("getAllProducts api is called");
		return productService.getAllProducts();
	}
	
	@GetMapping("/{productId}")
	public Products getProductById(@PathVariable int productId)
	{
		LOGGER.info("getproduct by id api is called");
		return productService.getProductById(productId);
	}
	
	@GetMapping("/sort")
	public List<Products> getSortedProducts()
	{
		LOGGER.info("sort api is called");
		return productService.getSortedProducts();
	}
	
	@GetMapping("/category/{category}")
	public List<Products> getProductsByCategory(@PathVariable String category)
	{
		LOGGER.info("filter by category api is called");
		return productService.getProductsByCategory(category);
	}
	
	//Internally called by ORDER-SERVICE
	@PutMapping("/reducecount")
	public Products reduceCount(@RequestParam int productId,@RequestParam int quantity)
	{
		LOGGER.info("reducecount api is called");
		Products product = productRepository.getById(productId);
		product.setQuantityAvailable(product.getQuantityAvailable() - quantity);
		productRepository.save(product);
		return product;
	}
	
	//Internally called by CART-SERVICE
	@GetMapping("/quantity/{productId}")
	public int getQuantity(@PathVariable int productId)
	{
		JSONObject object = new JSONObject();
		
		LOGGER.info("fetch quantity of product api is called");
		int quantity =  productRepository.getById(productId).getQuantityAvailable();
		object.put("quantity", quantity);
		LOGGER.info("JSON created : {}", object);
		return quantity;
	}
	
}
