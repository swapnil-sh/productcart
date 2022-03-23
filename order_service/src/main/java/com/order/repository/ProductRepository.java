package com.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.entity.Products;


@Repository
public interface ProductRepository extends JpaRepository<Products, Integer>{

}
