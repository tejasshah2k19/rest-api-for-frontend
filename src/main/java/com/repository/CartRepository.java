package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Integer> {

	List<CartEntity> findById(int cartId);
	List<CartEntity> findByUserId(int userId);
	
}
