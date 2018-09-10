package com.impactanalysis.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.impactanalysis.clients.GitClient;
import com.impactanalysis.dto.GitRequestDTO;
import com.impactanalysis.dto.GitResponseDTO;
import com.impactanalysis.dto.ImpactDTO;
import com.impactanalysis.entities.DeploymentEntity;
import com.impactanalysis.entities.MappingEntity;
import com.impactanalysis.exceptions.EntityNotFoundException;
import com.impactanalysis.pojo.File;
import com.impactanalysis.repositories.DeploymentRepository;
import com.impactanalysis.repositories.MappingRespository;

@Service
public class ImpactService {
	
	@Autowired
	private MappingRespository mappingRespository;
	
	@Autowired
	private GitClient gitClient;
	
	@Autowired
	private DeploymentRepository deploymentRepository;

	public ImpactDTO fetchImpactedTestSuites(GitRequestDTO gitRequestDTO, boolean fullInfo) {
		GitResponseDTO gitResponseDTO = gitClient.getCommitDetailsBetweenCommitIds(gitRequestDTO);
		Set<String> impactedFilesList = new HashSet<>();
		if(!ObjectUtils.isEmpty(gitResponseDTO)) {
			//impactedFilesList = gitResponseDTO.getFiles().stream().map(f -> f.getFilename()).collect(Collectors.toSet());
			for(File file:gitResponseDTO.getFiles()) {
				impactedFilesList.add(file.getFilename());
			}
		}
		List<MappingEntity> mappingEntities = mappingRespository.findByFileNamesIn(impactedFilesList);
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
			String repositoryOwnerId, String branchName) {
		
		// Fetch last commit id from database
		DeploymentEntity deploymentEntity = deploymentRepository.findFirstByRepositoryNameAndRepositoryOwnerIdAndBranchNameOrderByDeploymentIdDesc(repositoryName, repositoryOwnerId, branchName);
		if(ObjectUtils.isEmpty(deploymentEntity))
			throw new EntityNotFoundException(String.format("Last Deployment Details for repositoryName=%s, repositoryOwnerId=%s, branchName=%s is not present in DB", repositoryName, repositoryOwnerId, branchName));
		String startCommitId = deploymentEntity.getCommitId();
		
		// Fetch recent commit details from GitHub Repository Branch
		String endCommitId = gitClient.getLatestCommitIdOfBranch(new GitRequestDTO(repositoryName, repositoryOwnerId, branchName));
		if (StringUtils.isBlank(endCommitId))
		      throw new EntityNotFoundException(String.format("Last Commit Details for repositoryName=%s, repositoryOwnerId=%s, branchName=%s is not fetched from GitHub Repository Branch", repositoryName, repositoryOwnerId, branchName));
		
		ImpactDTO impactDTO = fetchImpactedTestSuites(new GitRequestDTO(repositoryName, repositoryOwnerId, branchName, startCommitId, endCommitId), false);
		return impactDTO.getTestSuiteList();
	}
}