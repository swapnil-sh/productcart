package com.product.service.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.model.Products;
import com.product.repository.ProductRepository;
import com.product.service.ProductService;

@Service
public class ProductServiceImplement implements ProductService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImplement.class);

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Products> getAllProducts()
	{
		List<Products> productList = productRepository.findAll();
		LOGGER.info("Product List : {}",productList);
		return productList;
	}

	@Override
	public Products getProductById(int productId) {
		
		Products product = productRepository.getById(productId);
		LOGGER.info("Product : {}",product);
		return product;
	}

	@Override
	public List<Products> getSortedProducts() {

		LOGGER.info("inside sortProduct()");
		List<Products> productList = productRepository.findAll();
		
		return productList.stream().sorted((Products p1, Products p2) -> {
			
			if(p1.getPrice() < p2.getPrice())
				return -1;
			else
				return 1;
		}).toList();
		
	}

	@Override
	public List<Products> getProductsByCategory(String category) {
		
		LOGGER.info("inside getProductsByCategory");
		return productRepository.findByCategory(category);
	}
	
}
