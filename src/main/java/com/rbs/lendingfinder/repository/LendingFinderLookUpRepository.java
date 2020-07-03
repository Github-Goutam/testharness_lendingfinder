package com.rbs.lendingfinder.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rbs.lendingfinder.entity.LendingFinderLookUpEntity;



@Repository
public interface LendingFinderLookUpRepository extends JpaRepository<LendingFinderLookUpEntity, Integer>{
	
	Optional<List<LendingFinderLookUpEntity>> findByPurposeIdAndCurrentAcctFlagAndoverdraftFlag(Integer purpose,Character currAccountFlag,Character overDraftFlag);
	//@Query( value="select max(THS_LENF_LOOKUP_ID) from THS_LENF_LOOKUP_REF", nativeQuery=true)
	
	//Integer findMaxLookUpID();
}
