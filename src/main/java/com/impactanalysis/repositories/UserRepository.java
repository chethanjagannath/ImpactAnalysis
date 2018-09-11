package com.impactanalysis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.impactanalysis.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
		
	@Transactional(readOnly = true)
	List<UserEntity> findByUserName(String userName);

}