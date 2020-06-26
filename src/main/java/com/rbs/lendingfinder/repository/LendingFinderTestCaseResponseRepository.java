package com.rbs.lendingfinder.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rbs.lendingfinder.entity.LendingFinderTestCaseResponseEntity;




@Repository
public interface LendingFinderTestCaseResponseRepository  extends JpaRepository<LendingFinderTestCaseResponseEntity, Integer>{
	
	//Optional<List<LendingFinderTestCaseResponseEntity>> findByTestSetId(Integer testSetId);
	//Page<LendingFinderTestCaseResponseEntity> findByTestSetId(Integer testSetId,Pageable pageRequest);

}
