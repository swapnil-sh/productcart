package com.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cart.entity.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer>{

}
