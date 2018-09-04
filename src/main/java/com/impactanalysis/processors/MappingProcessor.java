package com.impactanalysis.processors;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.impactanalysis.dto.MappingRequestDTO;
import com.impactanalysis.entities.MappingEntity;
import com.impactanalysis.exceptions.ValidationException;
import com.impactanalysis.pojo.Requestor;
import com.impactanalysis.repositories.MappingRespository;

@Component
public class MappingProcessor {

	@Autowired
	private MappingRespository mappingRespository;

	public void validateRequest(MappingRequestDTO mappingRequest, boolean isCreateRequest) {

		if (ObjectUtils.isEmpty(mappingRequest)) {
			throw new ValidationException("Request is Empty");
		} else {
			// For CreateAPI and UpdateAPI - Requestor details will be used for Auditing. So, Requestor Name and EmailId is mandatory.
			if (ObjectUtils.isEmpty(mappingRequest.getRequestor())) {
				throw new ValidationException("Requestor details is Empty");
			} else {
				Requestor requestor = mappingRequest.getRequestor();

				if (StringUtils.isBlank(requestor.getName())) {
					throw new ValidationException(
							"Could you please provide your name under Requestor details, for Auditing");
				}
				if (StringUtils.isBlank(requestor.getEmailId())) {
					throw new ValidationException(
							"Could you please provide your emailId under Requestor details, for Auditing");
				}
			}

			// Validation for MappingEntity Details
			if (!ObjectUtils.isEmpty(mappingRequest.getMappingEntity())) {
				MappingEntity mappingEntity = mappingRequest.getMappingEntity();

				// API Id should NOT be passed in CreateAPI request
				if (isCreateRequest && !ObjectUtils.isEmpty(mappingEntity.getApiId())) {
					throw new ValidationException("API Id should NOT be passed in CreateAPI request");
				}

				// API Id is mandatory for UpdateAPI request
				if (!isCreateRequest && ObjectUtils.isEmpty(mappingEntity.getApiId())) {
					throw new ValidationException("API Id should be passed in UpdateAPI request");
				}

				// For UpdateAPI, Throw ValidationException if the API is not present in DB
				if (!isCreateRequest && ObjectUtils.isEmpty(mappingRespository.findById(mappingEntity.getApiId()))) {
					throw new ValidationException("This API is NOT present in DB. Please provide valid API Id");
				}

				// For CreateAPI & UpdateAPI, Throw ValidationException if the API Name is not
				// present in Request
				if (StringUtils.isBlank(mappingEntity.getApiName())) {
					throw new ValidationException("API Name cannot be blank");
				} else if (isCreateRequest) {
					List<MappingEntity> mappingEntities = mappingRespository.findByApiName(mappingEntity.getApiName());
					// Check to avoid duplicate entries of APIs in DB.
					for (MappingEntity mappingEntityFromDB : mappingEntities) {
						if (mappingEntity.getApiName().equalsIgnoreCase(mappingEntityFromDB.getApiName())
								&& mappingEntity.getPathParams().equalsIgnoreCase(mappingEntityFromDB.getPathParams())
								&& mappingEntity.getQueryParams()
										.equalsIgnoreCase(mappingEntityFromDB.getQueryParams())) {
							throw new ValidationException("This API is already present in DB. UpdateAPI service can be used to update this API");
						}
					}
				}
			}
		}
	}
}