package com.rbs.lendingfinder.model;

public class LendingFinderTestSet {
	
	private Integer testSetId;
	private String applicationIdentity;
	private String bankDivision;
	private String borrowingAmt;
	private String termFactor;
	private Character processedFlag;
	private Boolean currentFlag;
	private Boolean overdraftFlag;
	private String purposeId;
	private String environment;
	
	
	public Integer getTestSetId() {
		return testSetId;
	}
	public void setTestSetId(Integer testSetId) {
		this.testSetId = testSetId;
	}
	public String getBankDivision() {
		return bankDivision;
	}
	public void setBankDivision(String bankDivision) {
		this.bankDivision = bankDivision;
	}
	
	public String getBorrowingAmt() {
		return borrowingAmt;
	}
	public void setBorrowingAmt(String borrowingAmt) {
		this.borrowingAmt = borrowingAmt;
	}
	public Character getProcessedFlag() {
		return processedFlag;
	}
	public void setProcessedFlag(Character processedFlag) {
		this.processedFlag = processedFlag;
	}
	public String getApplicationIdentity() {
		return applicationIdentity;
	}
	public void setApplicationIdentity(String applicationIdentity) {
		this.applicationIdentity = applicationIdentity;
	}
	public String getTermFactor() {
		return termFactor;
	}
	public void setTermFactor(String termFactor) {
		this.termFactor = termFactor;
	}
	public Boolean getCurrentFlag() {
		return currentFlag;
	}
	public void setCurrentFlag(Boolean currentFlag) {
		this.currentFlag = currentFlag;
	}
	public Boolean getOverdraftFlag() {
		return overdraftFlag;
	}
	public void setOverdraftFlag(Boolean overdraftFlag) {
		this.overdraftFlag = overdraftFlag;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public String getPurposeId() {
		return purposeId;
	}
	public void setPurposeId(String purposeId) {
		this.purposeId = purposeId;
	}
	
}
