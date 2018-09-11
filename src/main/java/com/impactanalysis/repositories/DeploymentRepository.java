package com.impactanalysis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.impactanalysis.entities.DeploymentEntity;

@Repository
public interface DeploymentRepository extends JpaRepository<DeploymentEntity, Long> {

	@Transactional(readOnly = true)
	List<DeploymentEntity> findAllByOrderByCreatedDateDesc();
	
	@Transactional(readOnly = true)
	DeploymentEntity findFirstByRepositoryNameAndRepositoryOwnerIdAndBranchNameOrderByDeploymentIdDesc(
			String repositoryName, String repositoryOwnerId, String branchName);
}
