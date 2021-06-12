package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	@PostMapping("/saveuser")
	public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity user) {

		System.out.println(user.getEmail());
		System.out.println(user.getFirstName());
		System.out.println(user.getPassword());

		user.setRoleId(RoleBean.Role.USER.getRoleId());

		userRepository.save(user);

		ResponseEntity<UserEntity> response = new ResponseEntity<>();
		response.setData(user);
		response.setMsg("user successfully save");
		response.setStatus(200);

		return response;
	}

 
}
