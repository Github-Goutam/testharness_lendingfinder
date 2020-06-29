package com.rbs.lendingfinder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rbs.lendingfinder.entity.LendingFinderLookUpEntity;



@Repository
public interface LendingFinderLookUpRepository extends JpaRepository<LendingFinderLookUpEntity, Integer>{
	
	//Optional<LendingFinderLookUpEntity> findByRiskBandAndTermFactorAndBorrowingAmount(Integer risk,String termRange,String borrowingAmountRange);
	
	//@Query( value="select max(THS_LENF_LOOKUP_ID) from THS_LENF_LOOKUP_REF", nativeQuery=true)
	
	//Integer findMaxLookUpID();
}
