package com.bbva.pisd.lib.r226;

import com.bbva.pisd.dto.contract.search.ReceiptSearchCriteria;
import com.bbva.pisd.dto.insurancedao.entities.ContractEntity;

import java.util.List;

/**
 * The  interface PISDR226 class...
 */
public interface PISDR226 {


	void executeSetCommonJdbcTemplate(Object commonJdbcTemplate);
	List<ContractEntity> executeFindContractBySearchCriteria(ReceiptSearchCriteria SearchCriteria);
	boolean executeUpdateBiometricId(String insuranceContractId, String biometricId, String usuario);
	public boolean executeFindByContract(String biometricId);
	ContractEntity executeExistContractByIdAndProductId(String contractId, String productId);
}
