package com.impactanalysis.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.impactanalysis.clients.GitClient;
import com.impactanalysis.dto.GitRequestDTO;
import com.impactanalysis.dto.GitResponseDTO;
import com.impactanalysis.dto.ImpactDTO;
import com.impactanalysis.entities.MappingEntity;
import com.impactanalysis.pojo.File;
import com.impactanalysis.repositories.MappingRespository;
import com.impactanalysis.utilities.CommonUtility;

@Service
public class ImpactService {
	
	@Autowired
	private MappingRespository mappingRespository;
	
	@Autowired
	private GitClient gitClient;
	
	@Autowired
	private CommonUtility commonUtility;
	
	@Autowired
	private DeploymentService deploymentService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public ImpactDTO fetchImpactedTestSuites(GitRequestDTO gitRequestDTO, boolean fullInfo) {
		GitResponseDTO gitResponseDTO = gitClient.getCommitDetailsBetweenCommitIds(gitRequestDTO);
		Set<String> impactedFilesList = new HashSet<>();
		if(!ObjectUtils.isEmpty(gitResponseDTO)) {
			//impactedFilesList = gitResponseDTO.getFiles().stream().map(f -> f.getFilename()).collect(Collectors.toSet());
			for(File file:gitResponseDTO.getFiles()) {
				impactedFilesList.add(file.getFilename());
			}
		}
		
		logger.info("ImpactedFilesList:" + impactedFilesList);
		List<MappingEntity> mappingEntities = mappingRespository.findByFileNamesIn(impactedFilesList);
		logger.info("MappingEntities:" + mappingEntities);
		
		Set<String> testSuiteList = new HashSet<>();
		Map<String, Set<String>> apiTestSuitesMappingList = new HashMap<>();
		for(MappingEntity mappingEntity:mappingEntities) {
			testSuiteList.addAll(mappingEntity.getTestSuiteNames());
			apiTestSuitesMappingList.put(mappingEntity.getApiId() + " " + mappingEntity.getApiName(), mappingEntity.getTestSuiteNames());
		}
		ImpactDTO impactDTO = new ImpactDTO();
		impactDTO.setTestSuiteList(testSuiteList);
		if(fullInfo) {
			impactDTO.setApiTestSuitesMappingList(apiTestSuitesMappingList);
		}
		
		return impactDTO;
	}

	public Set<String> fetchImpactedTestSuites(String repositoryName,
			String repositoryOwner, String branchName) {
		// TODO Auto-generated method stub
		return null;
	}
}