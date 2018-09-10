package com.impactanalysis.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.impactanalysis.dto.MappingRequestDTO;
import com.impactanalysis.entities.MappingEntity;
import com.impactanalysis.enums.Operation;
import com.impactanalysis.exceptions.EntityNotFoundException;
import com.impactanalysis.exceptions.ValidationException;
import com.impactanalysis.pojo.Requestor;
import com.impactanalysis.processors.MappingProcessor;
import com.impactanalysis.repositories.MappingRespository;
import com.impactanalysis.utilities.CommonUtility;

@Service
public class MappingService {
	
	@Autowired
	private MappingProcessor mappingProcessor;
	
	@Autowired
	private MappingRespository mappingRespository;
	
	@Autowired
	private CommonUtility commonUtility;

	public List<MappingEntity> createAPI(MappingRequestDTO mappingRequest) {
		mappingProcessor.validateRequest(mappingRequest, Operation.CREATE);
		return mappingRespository.saveAll(mappingRequest.getMappingEntity());
	}

	public List<MappingEntity> updateAPI(MappingRequestDTO mappingRequest, boolean fullUpdate) {
		mappingProcessor.validateRequest(mappingRequest, Operation.UPDATE);
		if (!ObjectUtils.isEmpty(mappingRequest.getMappingEntity())) {
			if(!fullUpdate) {
				List<MappingEntity> mappingEntities = new ArrayList<>();
				for (MappingEntity mappingEntity:mappingRequest.getMappingEntity()) {
					// To add additional details from input (Files & TestSuites)
					if(!ObjectUtils.isEmpty(mappingEntity)&& !ObjectUtils.isEmpty(mappingEntity.getApiId())) {
						MappingEntity mappingEntityDB = getAPIById(mappingEntity.getApiId());
						// To add additional file names
						if(!ObjectUtils.isEmpty(mappingEntity.getFileNames()))
							mappingEntityDB.getFileNames().addAll(mappingEntity.getFileNames());
						// To add additional test suites names
						if(!ObjectUtils.isEmpty(mappingEntity.getTestSuiteNames()))
							mappingEntityDB.getTestSuiteNames().addAll(mappingEntity.getTestSuiteNames());
						mappingEntities.add(mappingRespository.save(mappingEntityDB));
					}
				}
				return mappingEntities;
			} else {
				// To update whatever received as input
				return mappingRespository.saveAll(mappingRequest.getMappingEntity());
			}
		}
		throw new ValidationException("No MappingEntity present in the request to update");
	}

	public void deleteAPI(Requestor requestor,Integer apiId) {
		commonUtility.validateRequestor(requestor);
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