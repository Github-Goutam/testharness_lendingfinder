package com.rbs.lendingfinder.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LND_THS_LF_TESTSET_TB")
public class LendingFinderTestSetEntity extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TEST_SET_ID")
	private Integer testSetId;
	@Column(name = "APPL_IDENTITY")
	private Integer applicationIdentity;
	@Column(name = "BANK_DIVISION")
	private Integer bankDivision;
	@Column(name = "BORROWING_AMT")
	private String borrowingAmount;
	@Column(name = "TERM")
	private String termFactor;
	@Column(name = "PURPOSE")
	private Integer purpose;
	@Column(name = "CURRENTACCT_FLAG")
	private String currentAcctFlag;
	@Column(name = "OVERDRAFT_FLAG")
	private String overdraftFlag;
	@Column(name = "PROCESSED_FLAG")
	private Character processedFlag;
	@Column(name = "ENVIRONMENT")
	private String envrionment;
	

	public Integer getTestSetId() {
		return testSetId;
	}
	public void setTestSetId(Integer testSetId) {
		this.testSetId = testSetId;
	}
	public Integer getBankDivision() {
		return bankDivision;
	}
	public void setBankDivision(Integer bankDivision) {
		this.bankDivision = bankDivision;
	}
	public String getCurrentAcctFlag() {
		return currentAcctFlag;
	}
	public void setCurrentAcctFlag(String currentAcctFlag) {
		this.currentAcctFlag = currentAcctFlag;
	}
	public String getOverdraftFlag() {
		return overdraftFlag;
	}
	public void setOverdraftFlag(String overdraftFlag) {
		this.overdraftFlag = overdraftFlag;
	}
	
	public Character getProcessedFlag() {
		return processedFlag;
	}
	public void setProcessedFlag(Character processedFlag) {
		this.processedFlag = processedFlag;
	}
	public Integer getApplicationIdentity() {
		return applicationIdentity;
	}
	public void setApplicationIdentity(Integer applicationIdentity) {
		this.applicationIdentity = applicationIdentity;
	}
	public String getTermFactor() {
		return termFactor;
	}
	public void setTermFactor(String termFactor) {
		this.termFactor = termFactor;
	}
	
	public String getBorrowingAmount() {
		return borrowingAmount;
	}
	public void setBorrowingAmount(String borrowingAmount) {
		this.borrowingAmount = borrowingAmount;
	}
	public String getEnvrionment() {
		return envrionment;
	}
	public void setEnvrionment(String envrionment) {
		this.envrionment = envrionment;
	}
	public Integer getPurpose() {
		return purpose;
	}
	public void setPurpose(Integer purpose) {
		this.purpose = purpose;
	}
}

