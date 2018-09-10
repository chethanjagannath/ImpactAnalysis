package com.impactanalysis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.impactanalysis.entities.DeploymentEntity;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public interface DeploymentRepository extends JpaRepository<DeploymentEntity, Long> {

	@Transactional(readOnly = true, timeout = 100000)
	List<DeploymentEntity> findAllByOrderByCreatedDateDesc();

//	@Query(value = "SELECT * FROM TBL_DEPLOYMENT WHERE REPOSITORY_NAME = ?1 AND REPOSITORY_OWNER_ID = ?2 AND BRANCH_NAME = ?3 ORDER  BY DEPLOYMENT_ID DESC LIMIT 1;", nativeQuery = true)
//	DeploymentEntity getLatestDeploymentInfo(String repositoryName, String repositoryOwnerId, String branchName);

	@Transactional(readOnly = true, timeout = 100000)
	DeploymentEntity findFirstByRepositoryNameAndRepositoryOwnerIdAndBranchNameOrderByDeploymentIdDesc(
			String repositoryName, String repositoryOwnerId, String branchName);
}
