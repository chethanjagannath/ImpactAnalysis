package com.impactanalysis.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.impactanalysis.clients.GitClient;
import com.impactanalysis.dto.GitRequestDTO;
import com.impactanalysis.dto.GitResponseDTO;
import com.impactanalysis.dto.ImpactDTO;
import com.impactanalysis.dto.MappingRequestDTO;
import com.impactanalysis.entities.MappingEntity;
import com.impactanalysis.exceptions.EntityNotFoundException;
import com.impactanalysis.processors.MappingProcessor;
import com.impactanalysis.repositories.MappingRespository;

@Service
public class MappingService {
	
	@Autowired
	private MappingProcessor mappingProcessor;
	
	@Autowired
	private MappingRespository mappingRespository;
	
	@Autowired
	private GitClient gitClient;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public MappingEntity createAPI(MappingRequestDTO mappingRequest) {
		mappingProcessor.validateRequest(mappingRequest, true);
		return mappingRespository.save(mappingRequest.getMappingEntity());
	}

	public MappingEntity updateAPI(MappingRequestDTO mappingRequest) {
		
		mappingProcessor.validateRequest(mappingRequest, false);
		
		// To add additional details from input
//		MappingEntity mappingEntityDB = mappingRespository.findById(mapRequest.getApiId()).get();
//		mappingEntityDB.getFileNames().addAll(mappingEntity.getFileNames());
//		mappingEntityDB.getTestSuiteNames().addAll(mappingEntity.getTestSuiteNames());
//		mappingEntityDB.getPathParams().addAll(mappingEntity.getPathParams());
//		mappingEntityDB.getQueryParams().addAll(mappingEntity.getQueryParams());
//		return mappingRespository.save(mappingEntityDB);
		
		// To update whatever received as input
		return mappingRespository.save(mappingRequest.getMappingEntity());
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

/*	public ImpactDTO fetchImpactedTestSuites(GitRequestDTO gitRequestDTO) {
		GitResponseDTO gitResponseDTO = gitClient.getCommitDetailsBetweenCommitIds(gitRequestDTO);
		Set<String> impactedFilesList = null;
		if(!ObjectUtils.isEmpty(gitResponseDTO)) {
			impactedFilesList = gitResponseDTO.getFiles().stream().map(f -> f.getFilename()).collect(Collectors.toSet());
		}
		
		logger.info("ImpactedFilesList:" + impactedFilesList);
		
		return new ImpactDTO();
	}*/
}