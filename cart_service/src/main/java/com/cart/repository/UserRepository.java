package com.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cart.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
