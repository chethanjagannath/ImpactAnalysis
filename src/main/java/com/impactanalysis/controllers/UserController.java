package com.impactanalysis.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.impactanalysis.entities.UserEntity;
import com.impactanalysis.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping(value="/User")
@Api(value="User", description="APIs to manage Login Users information")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 401, message = "You are not authorized to view the resource"), @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ApiOperation(value = "Add user to DB", response = UserEntity.class)
	@PostMapping(value="/createUser")
	public UserEntity createAPI(@RequestBody UserEntity userRequest) {
		long startTime = System.currentTimeMillis();
		UserEntity userResponse =  userService.createUser(userRequest);
		logger.info(String.format("API::createUser Request=%s, Response=%s, TimeTaken=%s Milliseconds", userRequest, userResponse, System.currentTimeMillis()-startTime));
		return userResponse;
	}
	
	@ApiOperation(value = "Update user to DB", response = UserEntity.class)
	@PutMapping(value="/updateUser")
	public UserEntity updateAPI(@RequestBody UserEntity userRequest) {
		long startTime = System.currentTimeMillis();
		UserEntity userResponse =  userService.updateAPI(userRequest);
		logger.info(String.format("API::updateUser Request=%s, Response=%s, TimeTaken=%s Milliseconds", userRequest, userResponse, System.currentTimeMillis()-startTime));
		return userResponse;
	}
	
	@ApiOperation(value = "Delete user from DB", response = Void.class)
	@DeleteMapping(value="/deleteUser/{userEmailId}")
	public void deleteAPI(@PathVariable("userEmailId") String userEmailId) {
		long startTime = System.currentTimeMillis();
		userService.deleteAPI(userEmailId);
		logger.info(String.format("API::deleteUser userEmailId=%s, TimeTaken=%s Milliseconds", userEmailId, System.currentTimeMillis()-startTime));
	}
	
	@ApiOperation(value = "Get User from DB by passing User Email Id", response = UserEntity.class)
	@GetMapping(value="/getUserByEmailId/{userEmailId}")
	public UserEntity getUserByEmailId(@PathVariable("userEmailId") String userEmailId) {
		long startTime = System.currentTimeMillis();
		UserEntity userEntity =  userService.getUserByEmailId(userEmailId);
		logger.info(String.format("API::getUserByEmailId emailId=%s, Response=%s, TimeTaken=%s Milliseconds", userEmailId, userEntity, System.currentTimeMillis()-startTime));
		return userEntity;
	}
	
	@ApiOperation(value = "Get Users from DB by passing User Name", response = List.class)
	@GetMapping(value="/getUserByName/{userName}")
	public List<UserEntity> getUsersByName(@PathVariable("userName") String userName) {
		long startTime = System.currentTimeMillis();
		List<UserEntity> userEntities =  userService.getUsersByName(userName);
		logger.info(String.format("API::getUsersByName userName=%s, Response=%s, TimeTaken=%s Milliseconds", userName, userEntities, System.currentTimeMillis()-startTime));
		return userEntities;
	}
	
	@ApiOperation(value = "Get all Users", response = List.class)
	@GetMapping(value="/getAllUsers")
	public List<UserEntity> getAllUsers() {
		long startTime = System.currentTimeMillis();
		List<UserEntity> userEntities = userService.getAllUsers();
		logger.info(String.format("API::getAllUsers Response=%s, TimeTaken=%s Milliseconds", userEntities, System.currentTimeMillis()-startTime));
		return userEntities;
	}
}
