package com.impactanalysis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.impactanalysis.entities.MappingEntity;

@Repository
@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
public interface MappingRespository extends JpaRepository<MappingEntity,Integer>{
	
	@Transactional(readOnly = true, timeout=100000)
	List<MappingEntity> findByApiName(String apiName);
}

