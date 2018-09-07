package com.impactanalysis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impactanalysis.dto.DeploymentRequestDTO;
import com.impactanalysis.entities.DeploymentEntity;
import com.impactanalysis.pojo.Requestor;
import com.impactanalysis.repositories.DeploymentRepository;
import com.impactanalysis.utilities.CommonUtility;

@Service
public class DeploymentService {
	
	@Autowired
	private DeploymentRepository deploymentRepository;
	
	@Autowired
	private CommonUtility commonUtility;

	public DeploymentEntity createDeploymentInfo(DeploymentRequestDTO deploymentRequestDTO) {
		commonUtility.validateRequestor(deploymentRequestDTO.getRequestor());
		return deploymentRepository.save(deploymentRequestDTO.getDeploymentEntity());
	}

	public List<DeploymentEntity> getAllDeploymentsInfo() {
		return deploymentRepository.findAllByOrderByDeploymentDateDesc();
	}
	
	public DeploymentEntity getLatestDeploymentInfo() {
		return deploymentRepository.findTop1ByOrderByDeploymentDateDesc();
	}

	public void deleteDeploymentInfoById(Requestor requestor, Long deploymentId) {
		commonUtility.validateRequestor(requestor);
		deploymentRepository.deleteById(deploymentId);
	}
}