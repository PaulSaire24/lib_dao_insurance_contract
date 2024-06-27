package com.bbva.pisd.lib.r226;

import com.bbva.pisd.dto.contract.common.ReceiptDTO;
import com.bbva.pisd.dto.contract.search.ReceiptSearchCriteria;
import com.bbva.pisd.dto.insurancedao.entities.ContractEntity;
import com.bbva.pisd.dto.insurancedao.entities.PaymentPeriodEntity;

import java.util.List;
import java.util.Map;

/**
 * The  interface PISDR226 class...
 */
public interface PISDR226 {

	void executeSetCommonJdbcTemplate(Object commonJdbcTemplate);
	List<ContractEntity> executeFindContractBySearchCriteria(ReceiptSearchCriteria searchCriteria);
	boolean executeUpdateBiometricId(String insuranceContractId, String biometricId, String usuario);
	public boolean executeFindByContract(String biometricId);

	List<Map<String, Object>> executeGetRoyalPolicyDetail(String contractNumber);
	List<ReceiptDTO> executeGetReceipts(String contractNumber);
	ContractEntity executeExistContractByIdAndProductId(String contractId, String productId);
	boolean executeFindQuotationIfExistInContract(String quotationId);
	PaymentPeriodEntity executeFindPaymentPeriodByType(String paymentFrequencyType);
	int executeInsertInsuranceContract(Map<String,Object> map);
	String executeGetCustomerIdFromContract(String contractId);

}
