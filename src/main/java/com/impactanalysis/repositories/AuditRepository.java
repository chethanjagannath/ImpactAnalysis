package com.impactanalysis.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.impactanalysis.entities.AuditEntity;

@Repository
@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
public interface AuditRepository extends JpaRepository<AuditEntity,Long>{
	
}
