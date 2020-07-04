package com.rbs.lendingfinder.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rbs.lendingfinder.entity.BusinessAttributeEntity;
import com.rbs.lendingfinder.entity.LendingFinderLookUpEntity;
import com.rbs.lendingfinder.entity.LendingFinderTestCaseResponseEntity;
import com.rbs.lendingfinder.repository.BusinessAttributeRepository;

@Component
public class LendingFinderHelper {
	
	@Autowired
	private BusinessAttributeRepository businessAttributeRepository;

	public static <T> void permute(final List<List<T>> lists,
			final Consumer<List<T>> consumer) {
		final int[] index_pos = new int[lists.size()];

		final int last_index = lists.size() - 1;
		final List<T> permuted = new ArrayList<T>(lists.size());

		for (int i = 0; i < lists.size(); ++i) {
			permuted.add(null);
		}

		while (index_pos[last_index] < lists.get(last_index).size()) {
			for (int i = 0; i < lists.size(); ++i) {
				permuted.set(i, lists.get(i).get(index_pos[i]));
			}
			consumer.accept(permuted);

			for (int i = 0; i < lists.size(); ++i) {
				++index_pos[i];
				if (index_pos[i] < lists.get(i).size()) {
					/* stop at first element without overflow */
					break;
				} else if (i < last_index) {
					index_pos[i] = 0;
				}
			}
		}
	}
	
	public Map<Integer,String> findBusinessAttributeDescription(){
		List<BusinessAttributeEntity> businessAttributeEntities = businessAttributeRepository.findAll();
		return businessAttributeEntities.stream().filter(Objects::nonNull)
										         .collect(Collectors.toMap(BusinessAttributeEntity::getAttributeId, BusinessAttributeEntity::getRefDataDesc));
	}
	
	public boolean setLookUpProductFamilyAndProductNameValues(int borrowingAmount,int term,String borrowingAmoutnRange,String termFactorRange,LendingFinderTestCaseResponseEntity lfCaseResponseEntity,LendingFinderLookUpEntity FinderLookUpEntity) {
		String borrowingAmoutnRangeSplit[]=borrowingAmoutnRange.split("&");
		System.out.println("The length is "+borrowingAmoutnRangeSplit.length +"borrowingAmoutnRangeSplit "+borrowingAmoutnRangeSplit[0].trim());
		boolean borrowingAmtAndTermFactorMatch=false;
		if(null!=borrowingAmoutnRangeSplit && !"N/A".equalsIgnoreCase(borrowingAmoutnRangeSplit[0].trim())) {
			if(null!=borrowingAmoutnRangeSplit && borrowingAmoutnRangeSplit.length>1) {
				 if(borrowingAmoutnRangeSplit[0].trim().contains(">=")) {
					 if(borrowingAmoutnRangeSplit[1].trim().contains("<=")) {
						 if(borrowingAmount>=Integer.parseInt(borrowingAmoutnRangeSplit[0].trim().substring(2)) && borrowingAmount<=Integer.parseInt(borrowingAmoutnRangeSplit[1].trim().substring(2))) {
							 if(checkTerm(termFactorRange,term)) {
								 borrowingAmtAndTermFactorMatch=true;
							 }
						 }
					 }else if(borrowingAmoutnRangeSplit[1].trim().contains("<")) {
						 if(borrowingAmount>=Integer.parseInt(borrowingAmoutnRangeSplit[0].trim().substring(2)) && borrowingAmount<Integer.parseInt(borrowingAmoutnRangeSplit[1].trim().substring(1))) {
							 if(checkTerm(termFactorRange,term)) {
								 borrowingAmtAndTermFactorMatch=true;
							 }
						 }
					 }
				 }else if(borrowingAmoutnRangeSplit[0].trim().contains(">")) {
					 if(borrowingAmoutnRangeSplit[1].trim().contains("<=")) {
						 if(borrowingAmount>Integer.parseInt(borrowingAmoutnRangeSplit[0].trim().substring(1)) && borrowingAmount<=Integer.parseInt(borrowingAmoutnRangeSplit[1].trim().substring(2))) {
							 if(checkTerm(termFactorRange,term)) {
								 borrowingAmtAndTermFactorMatch=true;
							 }
						 }
					 }else if(borrowingAmoutnRangeSplit[1].trim().contains("<")) {
						 if(borrowingAmount>Integer.parseInt(borrowingAmoutnRangeSplit[0].trim().substring(1)) && borrowingAmount<Integer.parseInt(borrowingAmoutnRangeSplit[1].trim().substring(1))) {
							 if(checkTerm(termFactorRange,term)) {
								 borrowingAmtAndTermFactorMatch=true;
							 }
						 }
					 }
				 }
				
			}else if(null!=borrowingAmoutnRangeSplit && borrowingAmoutnRangeSplit.length==1) {
				 if(borrowingAmoutnRangeSplit[0].trim().contains(">=")) {
					 if(borrowingAmount>=Integer.parseInt(borrowingAmoutnRangeSplit[0].trim().substring(2))) {
						 if(checkTerm(termFactorRange,term)) {
							 borrowingAmtAndTermFactorMatch=true;
						 }
					 }
				}else if(borrowingAmoutnRangeSplit[0].trim().contains(">")) {
					 if(borrowingAmount>Integer.parseInt(borrowingAmoutnRangeSplit[0].trim().substring(1))) {
						 if(checkTerm(termFactorRange,term)) {
							 borrowingAmtAndTermFactorMatch=true;
						 }
					 }
				}else if(borrowingAmoutnRangeSplit[0].trim().contains("<=")) {
					 if(borrowingAmount<=Integer.parseInt(borrowingAmoutnRangeSplit[0].trim().substring(2))) {
						 if(checkTerm(termFactorRange,term)) {
							 borrowingAmtAndTermFactorMatch=true;
						 }
					 }
				}else if(borrowingAmoutnRangeSplit[0].trim().contains("<")) {
					 if(borrowingAmount<Integer.parseInt(borrowingAmoutnRangeSplit[0].trim().substring(1))) {
						 if(checkTerm(termFactorRange,term)) {
							 borrowingAmtAndTermFactorMatch=true;
						 }
					 }
				}
			}
		}else {
			if(checkTerm(termFactorRange,term)){
				borrowingAmtAndTermFactorMatch=true;
			}
		}
		if(borrowingAmtAndTermFactorMatch) {
			return true;
		}
		return false;
	}
		
	public boolean checkTerm(String termFactorRange,int term) {
			
		String termSplit[]=termFactorRange.split("&");
		System.out.println("The length is "+termSplit.length +"formatSplit "+termSplit[0].trim());
		if(null!=termSplit && termSplit.length>1) {
			 if(termSplit[0].trim().contains(">=")) {
				 if(termSplit[1].trim().contains("<=")) {
					 if(term>=Integer.parseInt(termSplit[0].trim().substring(2)) && term<=Integer.parseInt(termSplit[1].trim().substring(2))) {
						 return true;
					 }
				 }else if(termSplit[1].trim().contains("<")){
					 if(term>=Integer.parseInt(termSplit[0].trim().substring(2)) && term<Integer.parseInt(termSplit[1].trim().substring(1))) {
						 return true;
					 }
				 }
			 }else if(termSplit[0].trim().contains(">")) {
				 if(termSplit[1].trim().contains("<=")) {
					 if(term>Integer.parseInt(termSplit[0].trim().substring(1)) && term<=Integer.parseInt(termSplit[1].trim().substring(2))) {
						 return true;
					 }
				 }else if(termSplit[1].trim().contains("<")){
					 if(term>Integer.parseInt(termSplit[0].trim().substring(1)) && term<Integer.parseInt(termSplit[1].trim().substring(1))) {
						 return true;
					 }
				 }
			 }
		}else if(null!=termSplit && termSplit.length==1) {
			if(termSplit[0].trim().contains(">=")) {
				if(term>=Integer.parseInt(termSplit[0].trim().substring(2))) {
					 return true;
				 }
			}
			else if(termSplit[0].trim().contains(">")) {
				if(term>Integer.parseInt(termSplit[0].trim().substring(1))) {
					 return true;
				 }
			}
			else if(termSplit[0].trim().contains("<=")) {
				if(term<=Integer.parseInt(termSplit[0].trim().substring(2))) {
					 return true;
				 }
			}
			else if(termSplit[0].trim().contains("<")) {
				if(term<Integer.parseInt(termSplit[0].trim().substring(1))) {
					 return true;
				 }
			}
		}
		return false;
	}
}
