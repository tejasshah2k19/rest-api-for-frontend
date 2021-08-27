package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.UserEntity;
import com.model.LoginBean;
import com.model.ResponseEntity;
import com.model.RoleBean;
import com.repository.UserRepository;

@RestController
@CrossOrigin
public class SessionController {

	@Autowired
	UserRepository userRepository;

	@PostMapping("/authenticate")
	public ResponseEntity<UserEntity> authenticate(@RequestBody LoginBean login) {

		ResponseEntity<UserEntity> responseEntity = new ResponseEntity<>();

		UserEntity userEntity = userRepository.findByEmail(login.getEmail());

		if (userEntity == null || !userEntity.getPassword().equals(login.getPassword())) {
			responseEntity.setStatus(-1);
			responseEntity.setMsg("Invalid Credentials");
		} else {
			long random = (long) (Math.random() * 1000000000);

			userEntity.setAuthToken(random + "");
			userRepository.save(userEntity);
			responseEntity.setStatus(200);
			responseEntity.setMsg("Login done");
			responseEntity.setData(userEntity);

		}
		return responseEntity;
	}

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

	@GetMapping("/forgetpassword/{email}")
	public ResponseEntity<UserEntity> forgetPassword(@PathVariable("email") String email) {
		UserEntity user = null;
		ResponseEntity<UserEntity> res = new ResponseEntity<>();
		try {
			user = userRepository.findByEmail(email);
		} catch (Exception e) {

		}

		if (user == null) {
			res.setMsg("Invalid Email Address");
			res.setStatus(-1);

		} else {
			//send mail 
			res.setMsg("ResetPasword Link sent to your email");
			res.setStatus(200);
			res.setData(user);
		}
		return res;
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserEntity>> getAllUsers(){
		
		ResponseEntity<List<UserEntity>> res = new ResponseEntity<>();
		res.setData(userRepository.findAll());
		res.setStatus(200);
		res.setMsg("User Retrieved");
		return res;
	}
}
