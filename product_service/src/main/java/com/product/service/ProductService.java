package com.product.service;

import java.util.List;

import com.product.model.Products;

public interface ProductService {

	List<Products> getAllProducts();
	Products getProductById(int productId);
	List<Products> getSortedProducts();
	List<Products> getProductsByCategory(String category);

}
