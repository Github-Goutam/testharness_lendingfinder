package com.rbs.lendingfinder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rbs.lendingfinder.entity.BusinessAttributeEntity;



@Repository
public interface BusinessAttributeRepository extends JpaRepository<BusinessAttributeEntity, Integer>{
	List<BusinessAttributeEntity> findByAttributeIdIn(List<Integer> attributeIds);

	
}

