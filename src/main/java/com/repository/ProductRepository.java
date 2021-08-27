package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{

}
