package com.rbs.lendingfinder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rbs.lendingfinder.model.LendingFinderAttributeRequest;
import com.rbs.lendingfinder.model.LendingFinderTestCaseResponse;
import com.rbs.lendingfinder.service.LendingFinderService;

@RestController
@RequestMapping("/rbs/th/lf")
public class LendingFinderController {
	
	@Autowired
	private LendingFinderService lendingFinderService;
	
	@PostMapping("/testdata")
	private List<LendingFinderTestCaseResponse> generateTestCaseCombination(@RequestBody LendingFinderAttributeRequest attributeInputList) {
		return lendingFinderService.generateTestCaseCombination(attributeInputList);
	}
}
