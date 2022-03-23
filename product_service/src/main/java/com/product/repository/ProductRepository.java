package com.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.product.model.Products;
@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {
	
	public List<Products> findByCategory(String category);

}
