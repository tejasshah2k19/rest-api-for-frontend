package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	public UserEntity  findByEmail(String email);
	public List<UserEntity> findByAuthToken(String authToken);
	public List<UserEntity> findAll();
	
	public UserEntity findByUserId(int userId);

}
