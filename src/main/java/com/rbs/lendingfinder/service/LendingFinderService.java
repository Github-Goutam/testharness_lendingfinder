package com.rbs.lendingfinder.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.rbs.lendingfinder.common.LFConstant;
import com.rbs.lendingfinder.common.LFException;
import com.rbs.lendingfinder.entity.LendingFinderLookUpEntity;
import com.rbs.lendingfinder.entity.LendingFinderTestCaseResponseEntity;
import com.rbs.lendingfinder.entity.LendingFinderTestSetEntity;
import com.rbs.lendingfinder.helper.LendingFinderHelper;
import com.rbs.lendingfinder.model.LendingFinderAttributeRequest;
import com.rbs.lendingfinder.model.LendingFinderTestCaseResponse;
import com.rbs.lendingfinder.repository.LendingFinderLookUpRepository;
import com.rbs.lendingfinder.repository.LendingFinderTestCaseResponseRepository;
import com.rbs.lendingfinder.repository.LendingFinderTestSetRepository;

@Service
public class LendingFinderService {
	
	@Autowired
	private LendingFinderHelper lendingFinderHelper;
	
	@Autowired
	private LendingFinderTestSetRepository lendingFinderTestSetRepository;
	
	@Autowired
	private LendingFinderTestCaseResponseRepository lendingFinderTestCaseResponseRepository;
	
	@Autowired
	private LendingFinderLookUpRepository lendingFinderLookUpRepository;
	
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
		//Logic to add the record in the Test_Set Table
		String borrowingAmount = attributeInput.getBorrowingAmount().stream().map(String::valueOf).collect(Collectors.joining(","));
		String termFactor = attributeInput.getTermFactor().stream().map(String::valueOf).collect(Collectors.joining(","));
		String currentAccount = String.join(",", "1", "0");
		String overDraft = String.join(",", "1", "0");
		LendingFinderTestSetEntity testSetEntity = new LendingFinderTestSetEntity();
		BeanUtils.copyProperties(attributeInput, testSetEntity);
		testSetEntity.setBorrowingAmount(borrowingAmount);
		testSetEntity.setTermFactor(termFactor);
		testSetEntity.setCurrentAcctFlag(currentAccount);
		testSetEntity.setOverdraftFlag(overDraft);
		testSetEntity = lendingFinderTestSetRepository.save(testSetEntity);	
		
		System.out.println("Total testcase size::"+list4.size());
		int j = 0;
		int transactionId=1;
		List<LendingFinderTestCaseResponseEntity> lendingFinderTestCaseResponseEntities = new ArrayList<>();

		for (int i = 0; i < list4.size() / 5; i++) {
			LendingFinderTestCaseResponseEntity lendingFinderTestCaseResponseEntity = new LendingFinderTestCaseResponseEntity();
			lendingFinderTestCaseResponseEntity.setTestSetId(testSetEntity.getTestSetId());
			String counter = String.format("%05d", transactionId++); 
			lendingFinderTestCaseResponseEntity.setTestTransactionNo("TH_"+testSetEntity.getTestSetId()+"_"+ counter);
			lendingFinderTestCaseResponseEntity.setTestTransactionFlag(LFConstant.TestCase_Processed_N);
			lendingFinderTestCaseResponseEntity.setApplicationIdentity(attributeInput.getApplicationIdentity());
			lendingFinderTestCaseResponseEntity.setBankDivision(attributeInput.getBankDivision());
			//lendingFinderTestCaseResponseEntity.setProductFamily(attributeInput.getProductFamily());
			//lendingFinderTestCaseResponseEntity.setProductName(attributeInput.getProductName());
			lendingFinderTestCaseResponseEntity.setPurposeId(attributeInput.getPurposeId());
			lendingFinderTestCaseResponseEntity.setBorrowingAmount(list4.get(j++));
			//lendingFinderTestCaseResponseEntity.setRiskBand(list4.get(j++));
			lendingFinderTestCaseResponseEntity.setTermFactor(list4.get(j++));
			lendingFinderTestCaseResponseEntity.setCurrentAcctFlag(list4.get(j++) != 0 ? 'Y' : 'N');
			lendingFinderTestCaseResponseEntity.setOverdraftFlag(list4.get(j++) != 0 ? 'Y' : 'N');
			lendingFinderTestCaseResponseEntities.add(lendingFinderTestCaseResponseEntity);
		}
		//Saving to Transaction Testcase DB
		lendingFinderTestCaseResponseEntities = lendingFinderTestCaseResponseRepository.saveAll(lendingFinderTestCaseResponseEntities);
		List<LendingFinderTestCaseResponse> lendingFinderTestCaseResponses = new ArrayList<>();
		if(lendingFinderTestCaseResponseEntities != null && !lendingFinderTestCaseResponseEntities.isEmpty()) {
			Map<Integer, String> businessAttributeMap = lendingFinderHelper.findBusinessAttributeDescription();
			for(LendingFinderTestCaseResponseEntity entity : lendingFinderTestCaseResponseEntities) {
				LendingFinderTestCaseResponse lendingFinderTestCaseResponse = new LendingFinderTestCaseResponse();
				BeanUtils.copyProperties(entity, lendingFinderTestCaseResponse);
				lendingFinderTestCaseResponse.setApplicationIdentity(businessAttributeMap.get(entity.getApplicationIdentity()));
				lendingFinderTestCaseResponse.setBankDivision(businessAttributeMap.get(entity.getBankDivision()));
				//lendingFinderTestCaseResponse.setProductName(businessAttributeMap.get(entity.getProductName()));
				//lendingFinderTestCaseResponse.setProductFamily(businessAttributeMap.get(entity.getProductFamily()));
				lendingFinderTestCaseResponse.setPurposeId(businessAttributeMap.get(entity.getPurposeId()));
				lendingFinderTestCaseResponse.setTotalRecord((long) lendingFinderTestCaseResponseEntities.size());
				lendingFinderTestCaseResponses.add(lendingFinderTestCaseResponse);
			}
		}else {
			throw new LFException(HttpStatus.NOT_FOUND,"Test Case not found","Not found");
		}
		return lendingFinderTestCaseResponses;	
		
	}
	
	/*
	 * This method will generate the Test case AIR & APR from the lookup table data
	 */
	public List<LendingFinderTestCaseResponse> generateTestCaseAirApr(Integer testSetId) {
		List<LendingFinderTestCaseResponse> lfTestCaseResponseList=new ArrayList<>();
		List<LendingFinderTestCaseResponseEntity> lfCaseResponseEntityList=new ArrayList<>();

		if(null!=testSetId) {
			Optional<List<LendingFinderTestCaseResponseEntity>> lfCaseResponseEntityLists=lendingFinderTestCaseResponseRepository.findByTestSetId(testSetId);
			if(lfCaseResponseEntityLists.isPresent()) {
				lfCaseResponseEntityLists.get().forEach(lfCaseResponses->{
					LendingFinderTestCaseResponseEntity lfCaseResponseEntity=new LendingFinderTestCaseResponseEntity();
					BeanUtils.copyProperties(lfCaseResponses, lfCaseResponseEntity);
					Integer term=lfCaseResponses.getTermFactor();
					Integer borrowingAmount=lfCaseResponses.getBorrowingAmount();
					Integer purpose=lfCaseResponses.getPurposeId();
					Character currAccountFlag=lfCaseResponses.getCurrentAcctFlag();
					Character overDraftFlag=lfCaseResponses.getOverdraftFlag();
					//Making DB call to retrive the AIR & APR
					Optional<List<LendingFinderLookUpEntity>> pricingLookUpList=lendingFinderLookUpRepository.findByPurposeIdAndCurrentAcctFlagAndOverdraftFlag(purpose,currAccountFlag,overDraftFlag);
					String finalProductFamily="";
					String finalProductName="";
					if(pricingLookUpList.isPresent()) {
						for(LendingFinderLookUpEntity pricingLookup : pricingLookUpList.get()) {
							//Forming the Term Factor Range
							String termFactorRange=LFConstant.Empty_String;	
							//Forming the Risk Band Range
							String borrowingAmoutnRange=LFConstant.Empty_String;
							if(null!=pricingLookup.getBorrowingAmount()) {
								borrowingAmoutnRange=pricingLookup.getBorrowingAmount();
							}
							if(null!=pricingLookup.getTermFactor()) {
								termFactorRange=pricingLookup.getTermFactor();
							}
							
							if(!StringUtils.isEmpty(borrowingAmoutnRange) && !StringUtils.isEmpty(termFactorRange)) {
								boolean finalValue=lendingFinderHelper.setLookUpProductFamilyAndProductNameValues(borrowingAmount,term,borrowingAmoutnRange,termFactorRange,lfCaseResponseEntity,pricingLookup);
								if(finalValue) {
									if(""!=finalProductFamily) {
										finalProductFamily=finalProductFamily+","+pricingLookup.getProductFamily();
									}else {
										finalProductFamily=finalProductFamily+pricingLookup.getProductFamily();

									}
									if(""!=finalProductName) {
										finalProductName=finalProductName+","+pricingLookup.getProductName();
									}else {
										finalProductName=finalProductName+pricingLookup.getProductName();
									}
								}
							}
							
						}
						lfCaseResponseEntity.setExpectedProductFamily(finalProductFamily);
						lfCaseResponseEntity.setExpectedProductName(finalProductName);

					}
					lfCaseResponseEntityList.add(lfCaseResponseEntity);
					//Saving one by one
					lendingFinderTestCaseResponseRepository.save(lfCaseResponseEntity);
				});
			}
			Optional<List<LendingFinderTestCaseResponseEntity>> lfCaseResponseEntityFinalList=lendingFinderTestCaseResponseRepository.findByTestSetId(testSetId);

			if(lfCaseResponseEntityFinalList.get() != null && !lfCaseResponseEntityFinalList.get().isEmpty()) {
				Map<Integer, String> businessAttributeMap = lendingFinderHelper.findBusinessAttributeDescription();
				for(LendingFinderTestCaseResponseEntity entity : lfCaseResponseEntityFinalList.get()) {
					LendingFinderTestCaseResponse lendingFinderTestCaseResponse = new LendingFinderTestCaseResponse();
					BeanUtils.copyProperties(entity, lendingFinderTestCaseResponse);
					lendingFinderTestCaseResponse.setApplicationIdentity(businessAttributeMap.get(entity.getApplicationIdentity()));
					lendingFinderTestCaseResponse.setBankDivision(businessAttributeMap.get(entity.getBankDivision()));
					//lendingFinderTestCaseResponse.setProductName(businessAttributeMap.get(entity.getProductName()));
					//lendingFinderTestCaseResponse.setProductFamily(businessAttributeMap.get(entity.getProductFamily()));
					lendingFinderTestCaseResponse.setPurposeId(businessAttributeMap.get(entity.getPurposeId()));
					lendingFinderTestCaseResponse.setTotalRecord((long) lfCaseResponseEntityFinalList.get().size());
					lfTestCaseResponseList.add(lendingFinderTestCaseResponse);
				}
			}else {
				throw new LFException(HttpStatus.NOT_FOUND,"Test Case not found","Not found");
			}
		}
		return lfTestCaseResponseList;	
	}
}
