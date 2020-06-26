package com.rbs.lendingfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rbs.lendingfinder.entity.LendingFinderTestSetEntity;



@Repository
public interface LendingFinderTestSetRepository extends JpaRepository<LendingFinderTestSetEntity, Integer>{

}
