package com.rbs.lendingfinder.model;

import java.util.List;

public class LendingFinderTestCaseResult {
	
	private Integer totalTestCases;
	private Integer passed;
	private Integer failed;
	private List<LendingFinderTestCaseResponse> testcasesResultList;
	
	public Integer getTotalTestCases() {
		return totalTestCases;
	}
	public void setTotalTestCases(Integer totalTestCases) {
		this.totalTestCases = totalTestCases;
	}
	public Integer getPassed() {
		return passed;
	}
	public void setPassed(Integer passed) {
		this.passed = passed;
	}
	public Integer getFailed() {
		return failed;
	}
	public void setFailed(Integer failed) {
		this.failed = failed;
	}
	public List<LendingFinderTestCaseResponse> getTestcasesResultList() {
		return testcasesResultList;
	}
	public void setTestcasesResultList(List<LendingFinderTestCaseResponse> testcasesResultList) {
		this.testcasesResultList = testcasesResultList;
	}
	
}
