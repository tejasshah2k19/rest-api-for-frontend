package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.entity.CartEntity;
import com.entity.UserEntity;
import com.model.ResponseEntity;
import com.repository.CartRepository;
import com.repository.UserRepository;

@RestController
public class CartController {
	@Autowired
	CartRepository cartRepo;

	@Autowired
	UserRepository userRepo;

	@GetMapping("addtocart/{token}/{productId}")
	public ResponseEntity<CartEntity> addToCart(@PathVariable("token") String token,
			@PathVariable("productId") int productId) {

		CartEntity cart = new CartEntity();

		cart.setProductId(productId);
		UserEntity user = userRepo.findByAuthToken(token).get(0);
		cart.setUserId(user.getUserId());

		cartRepo.save(cart);
		ResponseEntity<CartEntity> resp = new ResponseEntity<>();
		resp.setData(cart);
		resp.setMsg("item added into cart");
		resp.setStatus(200);
		return resp;
	}

	@GetMapping("/carts/{token}")
	public ResponseEntity<List<CartEntity>> viewCart(@PathVariable("token") String token) {

		UserEntity user = userRepo.findByAuthToken(token).get(0);

		List<CartEntity> carts = cartRepo.findByUserId(user.getUserId());

		// firebase

		ResponseEntity<List<CartEntity>> resp = new ResponseEntity<>();
		resp.setMsg("cart retervied");
		resp.setStatus(200);
		resp.setData(carts);
		return resp;
	}

}
