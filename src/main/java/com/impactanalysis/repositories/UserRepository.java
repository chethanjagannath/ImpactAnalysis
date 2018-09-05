package com.impactanalysis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.impactanalysis.entities.UserEntity;

@Repository
@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
public interface UserRepository extends JpaRepository<UserEntity, Long>{
		
	@Transactional(readOnly = true, timeout=100000)
	List<UserEntity> findByUserName(String userName);

}