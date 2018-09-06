package com.impactanalysis.services;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.impactanalysis.dto.MappingRequestDTO;
import com.impactanalysis.entities.MappingEntity;
import com.impactanalysis.enums.Operation;
import com.impactanalysis.exceptions.EntityNotFoundException;
import com.impactanalysis.processors.MappingProcessor;
import com.impactanalysis.repositories.MappingRespository;

@Service
public class MappingService {
	
	@Autowired
	private MappingProcessor mappingProcessor;
	
	@Autowired
	private MappingRespository mappingRespository;

	public MappingEntity createAPI(MappingRequestDTO mappingRequest) {
		mappingProcessor.validateRequest(mappingRequest, Operation.CREATE);
		return mappingRespository.save(mappingRequest.getMappingEntity());
	}

	public MappingEntity updateAPI(MappingRequestDTO mappingRequest, boolean fullUpdate) {
		
		mappingProcessor.validateRequest(mappingRequest, Operation.UPDATE);
		
		if(!fullUpdate) {
			// To add additional details from input (Files & TestSuites)
			if(!ObjectUtils.isEmpty(mappingRequest) && !ObjectUtils.isEmpty(mappingRequest.getMappingEntity()) && !ObjectUtils.isEmpty(mappingRequest.getMappingEntity().getApiId())) {
				MappingEntity mappingEntityDB = getAPIById(mappingRequest.getMappingEntity().getApiId());
				// To add additional file names
				if(!ObjectUtils.isEmpty(mappingRequest.getMappingEntity().getFileNames()))
					mappingEntityDB.getFileNames().addAll(mappingRequest.getMappingEntity().getFileNames());
				// To add additional test suites names
				if(!ObjectUtils.isEmpty(mappingRequest.getMappingEntity().getTestSuiteNames()))
					mappingEntityDB.getTestSuiteNames().addAll(mappingRequest.getMappingEntity().getTestSuiteNames());
				return mappingRespository.save(mappingEntityDB);
			} else {
				return null;
			}
		} else {
			// To update whatever received as input
			return mappingRespository.save(mappingRequest.getMappingEntity());
		}
	}

	public void deleteAPI(Integer apiId) {
		mappingRespository.deleteById(apiId);
	}

	public MappingEntity getAPIById(Integer apiId) {
		Optional<MappingEntity> mappingEntity = mappingRespository.findById(apiId);
		if (!mappingEntity.isPresent())
		      throw new EntityNotFoundException("apiId-" + apiId + " Not Found in DB");
		return mappingRespository.findById(apiId).get();
	}
	
	public List<MappingEntity> getAPIByName(String apiName) {
		return mappingRespository.findByApiName(apiName);
	}

	public List<MappingEntity> getAllAPI() {
		return mappingRespository.findAll();
	}
}