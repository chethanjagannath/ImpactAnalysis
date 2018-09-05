package com.impactanalysis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impactanalysis.entities.UserEntity;
import com.impactanalysis.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public UserEntity createUser(UserEntity userRequest) {
		return userRepository.save(userRequest);
	}

	public UserEntity updateAPI(UserEntity userRequest) {
		return userRepository.save(userRequest);
	}

	public UserEntity getUserByEmailId(String userEmailId) {
		return userRepository.findById(userEmailId).get();
	}

	public void deleteAPI(String userEmailId) {
		userRepository.deleteById(userEmailId);
	}

	public List<UserEntity> getUsersByName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public List<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}

}
