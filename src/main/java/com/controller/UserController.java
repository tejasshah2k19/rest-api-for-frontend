package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.IngredientEntity;
import com.entity.UserEntity;
import com.model.ResponseEntity;
import com.model.RoleBean;
import com.repository.UserRepository;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	UserRepository userRepository;

	@DeleteMapping("/users/{userId}")
	public ResponseEntity<UserEntity> deleteUserById(@PathVariable("userId") int userId) {

		ResponseEntity<UserEntity> res = new ResponseEntity<>();
		boolean isError = false;
		try {
			UserEntity user = userRepository.getById(userId);
			System.out.println(user);
			if (user != null) {
				userRepository.delete(user);
				res.setData(null);
				res.setMsg("user removed");
				res.setStatus(200);
			} else {
				isError = true;
			}

		} catch (Exception e) {

			isError = true;
		}

		if (isError) {
			res.setData(null);
			res.setMsg("Invalid UserId");
			res.setStatus(-1);
		}
		return res;
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable("userId") int userId) {

		ResponseEntity<UserEntity> resp = new ResponseEntity<>();

		UserEntity user = null;

		try {
			user = userRepository.getById(userId);
		} catch (Exception e) {
			System.out.println("invalid id" + e.getMessage());
		}
		if (user == null) {
			resp.setData(null);
			resp.setStatus(-1);
			resp.setMsg("Invalid userId : userNot Found");
		} else {
			resp.setData(user);
			resp.setStatus(200);
			resp.setMsg("user found");
		}
		return resp;
	}

}
