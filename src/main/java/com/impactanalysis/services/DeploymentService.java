package com.impactanalysis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.impactanalysis.entities.DeploymentEntity;
import com.impactanalysis.pojo.Requestor;
import com.impactanalysis.repositories.DeploymentRepository;
import com.impactanalysis.utilities.CommonUtility;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 100000)
public class DeploymentService {

	@Autowired
	private DeploymentRepository deploymentRepository;

	@Autowired
	private CommonUtility commonUtility;

	public DeploymentEntity createDeploymentInfo(DeploymentEntity deploymentRequest) {
		return deploymentRepository.save(deploymentRequest);
	}

	public List<DeploymentEntity> getAllDeploymentsInfo() {
		return deploymentRepository.findAllByOrderByCreatedDateDesc();
	}

	public DeploymentEntity getLatestDeploymentInfo(DeploymentEntity deploymentDetails) {
		return deploymentRepository.findFirstByRepositoryNameAndRepositoryOwnerIdAndBranchNameOrderByDeploymentIdDesc(deploymentDetails.getRepositoryName(),
				deploymentDetails.getRepositoryOwnerId(), deploymentDetails.getBranchName());
	}

	public void deleteDeploymentInfoById(Requestor requestor, Long deploymentId) {
		commonUtility.validateRequestor(requestor);
		deploymentRepository.deleteById(deploymentId);
	}
}