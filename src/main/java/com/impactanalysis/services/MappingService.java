package com.impactanalysis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impactanalysis.entities.MappingEntity;
import com.impactanalysis.repositories.MappingRespository;

@Service
public class MappingService {
	
	@Autowired
	private MappingRespository mappingRespository;

	public MappingEntity createAPI(MappingEntity mappingEntity) {
		return mappingRespository.save(mappingEntity);
	}

	public MappingEntity updateAPI(MappingEntity mappingEntity) {
	
		// To add additional details from input
//		MappingEntity mappingEntityDB = mappingRespository.findById(mapRequest.getApiId()).get();
//		mappingEntityDB.getFileNames().addAll(mappingEntity.getFileNames());
//		mappingEntityDB.getTestSuiteNames().addAll(mappingEntity.getTestSuiteNames());
//		mappingEntityDB.getPathParams().addAll(mappingEntity.getPathParams());
//		mappingEntityDB.getQueryParams().addAll(mappingEntity.getQueryParams());
//		return mappingRespository.save(mappingEntityDB);
		
		// To update whatever received as input
		return mappingRespository.save(mappingEntity);
	}

	public void deleteAPI(Integer apiId) {
		mappingRespository.deleteById(apiId);
	}

	public MappingEntity getAPIById(Integer apiId) {
		return mappingRespository.findById(apiId).get();
	}
	
	public List<MappingEntity> getAPIByName(String apiName) {
		return mappingRespository.findByApiName(apiName);
	}

	public List<MappingEntity> getAllAPI() {
		return mappingRespository.findAll();
	}
}