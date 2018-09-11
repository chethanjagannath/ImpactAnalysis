package com.impactanalysis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.impactanalysis.entities.UserEntity;
import com.impactanalysis.enums.Operation;
import com.impactanalysis.processors.UserProcessor;
import com.impactanalysis.repositories.UserRepository;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 100000)
public class UserService {
	
	@Autowired
	private UserProcessor userProcessor;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<UserEntity> createUser(List<UserEntity> userRequest) {
		for(UserEntity userEntity:userRequest) {
			userProcessor.validateRequest(userEntity, Operation.CREATE);
		}
		return userRepository.saveAll(userRequest);
	}

	public UserEntity updateUser(UserEntity userRequest) {
		userProcessor.validateRequest(userRequest, Operation.UPDATE);
		return userRepository.save(userRequest);
	}

	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}
	
	public UserEntity getUserById(Long userId) {
		return userRepository.findById(userId).get();
	}

	public List<UserEntity> getUsersByName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public List<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}
}
