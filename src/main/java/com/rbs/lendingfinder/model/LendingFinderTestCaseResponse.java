package com.rbs.lendingfinder.model;

public class LendingFinderTestCaseResponse {
	
	private Integer testTransactionId;
	private Integer testSetId;
	private String testTransactionNo;
	private String applicationIdentity;
	private String bankDivision;
	private String purpose;
	private Character currentAcctFlag;
	private Character overdraftFlag;
	private Integer borrowingAmount;
	private Integer termFactor;
	private String expectedProductFamily;
	private String expectedProductName;
	private String actualProductFamily;
	private String actualProductName;
	private Character testTransactionFlag;
	private Long totalRecord;
	private String environment;
	private String createdBy;
	
	public Integer getTestTransactionId() {
		return testTransactionId;
	}
	public void setTestTransactionId(Integer testTransactionId) {
		this.testTransactionId = testTransactionId;
	}
	public Integer getTestSetId() {
		return testSetId;
	}
	public void setTestSetId(Integer testSetId) {
		this.testSetId = testSetId;
	}
	public String getTestTransactionNo() {
		return testTransactionNo;
	}
	public void setTestTransactionNo(String testTransactionNo) {
		this.testTransactionNo = testTransactionNo;
	}
	public String getApplicationIdentity() {
		return applicationIdentity;
	}
	public void setApplicationIdentity(String applicationIdentity) {
		this.applicationIdentity = applicationIdentity;
	}
	public String getBankDivision() {
		return bankDivision;
	}
	public void setBankDivision(String bankDivision) {
		this.bankDivision = bankDivision;
	}

	public Integer getBorrowingAmount() {
		return borrowingAmount;
	}
	public void setBorrowingAmount(Integer borrowingAmount) {
		this.borrowingAmount = borrowingAmount;
	}
	public Integer getTermFactor() {
		return termFactor;
	}
	public void setTermFactor(Integer termFactor) {
		this.termFactor = termFactor;
	}
	public Character getTestTransactionFlag() {
		return testTransactionFlag;
	}
	public void setTestTransactionFlag(Character testTransactionFlag) {
		this.testTransactionFlag = testTransactionFlag;
	}
	
	public Long getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(Long totalRecord) {
		this.totalRecord = totalRecord;
	}
	
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	public String getExpectedProductFamily() {
		return expectedProductFamily;
	}
	public void setExpectedProductFamily(String expectedProductFamily) {
		this.expectedProductFamily = expectedProductFamily;
	}
	public String getExpectedProductName() {
		return expectedProductName;
	}
	public void setExpectedProductName(String expectedProductName) {
		this.expectedProductName = expectedProductName;
	}
	public String getActualProductFamily() {
		return actualProductFamily;
	}
	public void setActualProductFamily(String actualProductFamily) {
		this.actualProductFamily = actualProductFamily;
	}
	public String getActualProductName() {
		return actualProductName;
	}
	public void setActualProductName(String actualProductName) {
		this.actualProductName = actualProductName;
	}
	public Character getCurrentAcctFlag() {
		return currentAcctFlag;
	}
	public void setCurrentAcctFlag(Character currentAcctFlag) {
		this.currentAcctFlag = currentAcctFlag;
	}
	public Character getOverdraftFlag() {
		return overdraftFlag;
	}
	public void setOverdraftFlag(Character overdraftFlag) {
		this.overdraftFlag = overdraftFlag;
	}

}
