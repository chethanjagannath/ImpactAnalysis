package com.impactanalysis.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.impactanalysis.entities.MappingEntity;

@Repository
public interface MappingRespository extends JpaRepository<MappingEntity,Integer>{
	
	@Transactional(readOnly = true)
	List<MappingEntity> findByApiName(String apiName);
	
	@Transactional(readOnly = true)
	List<MappingEntity> findByFileNamesIn(Set<String> impactedFilesList);
}