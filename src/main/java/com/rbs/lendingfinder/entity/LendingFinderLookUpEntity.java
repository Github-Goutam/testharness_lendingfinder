package com.rbs.lendingfinder.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LND_THS_LF_LOOKUP_REF")
public class LendingFinderLookUpEntity extends BaseEntity{
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "THS_LOOKUP_ID")
	private Integer lookUpId;
	@Column(name = "TERM")
	private String termFactor;
	@Column(name = "BORROWING_AMT")
	private String borrowingAmount;
	@Column(name="CURRENTACCT_FLAG")
	private Character currentFlag;
	@Column(name="OVERDRAFT_FLAG")
	private Character overdraftFlag;
	@Column(name="PRODUCT_FAMILY")
	private String productFamily;
	@Column(name="PRODUCT_NAME")
	private String productName;
	@Column(name="INTEREST_RATE")
	private String interestRate;
	
	public Integer getLookUpId() {
		return lookUpId;
	}
	public void setLookUpId(Integer lookUpId) {
		this.lookUpId = lookUpId;
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
	
	public String getProductFamily() {
		return productFamily;
	}
	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Character getCurrentFlag() {
		return currentFlag;
	}
	public void setCurrentFlag(Character currentFlag) {
		this.currentFlag = currentFlag;
	}
	public Character getOverdraftFlag() {
		return overdraftFlag;
	}
	public void setOverdraftFlag(Character overdraftFlag) {
		this.overdraftFlag = overdraftFlag;
	}
	public String getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}
	
}
