package com.rbs.lendingfinder.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rbs.lendingfinder.helper.LendingFinderHelper;
import com.rbs.lendingfinder.model.LendingFinderAttributeRequest;
import com.rbs.lendingfinder.model.LendingFinderTestCaseResponse;

@Service
public class LendingFinderService {
	
	/*
	 * This method will generate the Test case Combination based on the attribute inputs
	 */
	public List<LendingFinderTestCaseResponse> generateTestCaseCombination(LendingFinderAttributeRequest attributeInput) {
		final List<List<Integer>> lists = new ArrayList<List<Integer>>();
		lists.add(new ArrayList<>(attributeInput.getBorrowingAmount()));		
		lists.add(new ArrayList<>(attributeInput.getTermFactor()));	
		int currentAccountFlags[] = {0,1};
		int overDraftFlags [] = {0,1};
		lists.add(Arrays.stream(currentAccountFlags).boxed().collect(Collectors.toList()));
		lists.add(Arrays.stream(overDraftFlags).boxed().collect(Collectors.toList()));
		//Logic to create test case combination
		final List<Integer> list4 = new ArrayList<Integer>();
		LendingFinderHelper.permute(lists, (permutation -> list4.addAll(permutation)));
		//TODO
		return null;
	}

}
