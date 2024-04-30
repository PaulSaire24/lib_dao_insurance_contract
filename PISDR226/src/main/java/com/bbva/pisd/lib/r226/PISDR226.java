package com.bbva.pisd.lib.r226;

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
	List<ContractEntity> executeFindContractBySearchCriteria(ReceiptSearchCriteria SearchCriteria);
	boolean executeUpdateBiometricId(String insuranceContractId, String biometricId, String usuario);
	public boolean executeFindByContract(String biometricId);

}
