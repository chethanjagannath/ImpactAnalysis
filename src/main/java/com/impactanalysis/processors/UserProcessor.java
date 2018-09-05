package com.impactanalysis.processors;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.impactanalysis.entities.UserEntity;
import com.impactanalysis.enums.Operation;
import com.impactanalysis.exceptions.ValidationException;
import com.impactanalysis.repositories.UserRepository;

@Component
public class UserProcessor {

	@Autowired
	private UserRepository userRepository;

	public void validateRequest(UserEntity userEntity, Operation operation) {

		if (ObjectUtils.isEmpty(userEntity)) {
			throw new ValidationException("Request is Empty");
		} else {
			// User Id should NOT be passed in CreateUser request
			if (Operation.CREATE==operation && !ObjectUtils.isEmpty(userEntity.getUserId())) {
				throw new ValidationException("User Id should NOT be passed in CreateUser request");
			}

			// User Id is mandatory for UpdateUser request
			if (Operation.UPDATE==operation && ObjectUtils.isEmpty(userEntity.getUserId())) {
				throw new ValidationException("User Id should be passed in UpdateUser request");
			}

			// For UpdateUser, Throw ValidationException if the User is not present in DB
			if (Operation.UPDATE==operation && ObjectUtils.isEmpty(userRepository.findById(userEntity.getUserId()))) {
				throw new ValidationException("This User is NOT present in DB. Please provide valid User Id");
			}
			
			if (Operation.CREATE==operation && StringUtils.isNotBlank(userEntity.getUserName())) {
				List<UserEntity> userEntities = userRepository.findByUserName(userEntity.getUserName());
				// Check to avoid duplicate entries of APIs in DB.
				for (UserEntity userEntityFromDB : userEntities) {
					if (userEntity.getUserName().equalsIgnoreCase(userEntityFromDB.getUserName())
							&& userEntity.getUserEmailId().equalsIgnoreCase(userEntityFromDB.getUserEmailId())) {
						throw new ValidationException("This User is already present in DB. UpdateUser service can be used to update this User");
					}
				}
			}
		}
	}
}
