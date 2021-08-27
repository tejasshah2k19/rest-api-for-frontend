package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.ProductEntity;
import com.model.ResponseEntity;
import com.repository.ProductRepository;

@RestController
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@PostMapping("/products")
	public ResponseEntity<ProductEntity> addProduct(@RequestBody ProductEntity product) {

		ResponseEntity<ProductEntity> res = new ResponseEntity<>();
		productRepository.save(product);
		res.setData(product);
		res.setMsg("product added");
		res.setStatus(200);

		return res;
	}

	@GetMapping("/products")
	public ResponseEntity<List<ProductEntity>> getAllProducts() {
		ResponseEntity<List<ProductEntity>> res = new ResponseEntity<>();
		res.setData(productRepository.findAll());
		res.setMsg("product retrieved");
		res.setStatus(200);
		return res;
	}

	@GetMapping("/products/{productId}")
	public ResponseEntity<ProductEntity> getProductById(@PathVariable("productId") int productId) {

		ResponseEntity<ProductEntity> res = new ResponseEntity<>();
		res.setData(productRepository.getById(productId));
		res.setMsg("Product retrieved");
		res.setStatus(200);
		return res;
	}

	@DeleteMapping("/products/{productId}")
	public ResponseEntity<ProductEntity> removeProductById(@PathVariable("productId") int productId) {

		ResponseEntity<ProductEntity> res = new ResponseEntity<>();

		res.setData(productRepository.getById(productId));
		res.setMsg("Product retrieved");
		res.setStatus(200);

		productRepository.deleteById(productId);
		return res;
	}
	
	@PutMapping("/products")
	public ResponseEntity<ProductEntity> updateProduct(@RequestBody ProductEntity product) {

		ResponseEntity<ProductEntity> res = new ResponseEntity<>();
		
		ProductEntity updateProduct = productRepository.getById(product.getProductId());
		updateProduct.setName(product.getName());
		updateProduct.setPrice(updateProduct.getPrice());
		updateProduct.setQty(updateProduct.getQty());
		
		productRepository.save(updateProduct);
		res.setData(product);
		res.setMsg("product updated");
		res.setStatus(200);

		return res;
	}
	
	
	
}

