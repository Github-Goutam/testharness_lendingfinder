package com.rbs.lendingfinder.model;

import java.util.List;

public class LendingFinderAttributeRequest {
	
	private List<Integer> borrowingAmount;
	private List<Integer> termFactor;
	private Integer bankDivision;
	private Integer applicationIdentity;
	private String userId;
	private Integer purpose;
	private Boolean currentAcctFlag;
	private Boolean overdraftFlag;
	
	
	public List<Integer> getBorrowingAmount() {
		return borrowingAmount;
	}
	public void setBorrowingAmount(List<Integer> borrowingAmount) {
		this.borrowingAmount = borrowingAmount;
	}
	public List<Integer> getTermFactor() {
		return termFactor;
	}
	public void setTermFactor(List<Integer> termFactor) {
		this.termFactor = termFactor;
	}
	public Integer getBankDivision() {
		return bankDivision;
	}
	public void setBankDivision(Integer bankDivision) {
		this.bankDivision = bankDivision;
	}
	public Integer getApplicationIdentity() {
		return applicationIdentity;
	}
	public void setApplicationIdentity(Integer applicationIdentity) {
		this.applicationIdentity = applicationIdentity;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getPurpose() {
		return purpose;
	}
	public void setPurpose(Integer purpose) {
		this.purpose = purpose;
	}
	public Boolean getCurrentAcctFlag() {
		return currentAcctFlag;
	}
	public void setCurrentAcctFlag(Boolean currentAcctFlag) {
		this.currentAcctFlag = currentAcctFlag;
	}
	public Boolean getOverdraftFlag() {
		return overdraftFlag;
	}
	public void setOverdraftFlag(Boolean overdraftFlag) {
		this.overdraftFlag = overdraftFlag;
	}	
	
}
