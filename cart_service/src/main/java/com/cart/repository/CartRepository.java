package com.cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cart.entity.Cart;
import com.cart.entity.Products;
import com.cart.entity.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

	public Cart findByProductsAndUser(Products products,User user);

	public List<Cart> findByUser(User user);
}
