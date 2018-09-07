package com.impactanalysis.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.impactanalysis.entities.DeploymentEntity;

@Repository
@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
public interface DeploymentRepository extends JpaRepository<DeploymentEntity,Long>{
	
	@Transactional(readOnly = true, timeout=100000)
	List<DeploymentEntity> findAllByOrderByDeploymentDateDesc();
	
	@Transactional(readOnly = true, timeout=100000)
	DeploymentEntity findTop1ByOrderByDeploymentDateDesc();
}