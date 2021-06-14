package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
		}else {
			long random = (long)(Math.random()*1000000000);
			
			userEntity.setAuthToken(random+"");
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

}
