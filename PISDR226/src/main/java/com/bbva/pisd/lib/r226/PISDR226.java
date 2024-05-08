package com.bbva.pisd.lib.r226;

import com.bbva.pisd.dto.contract.common.ReceiptDTO;
import com.bbva.pisd.dto.contract.search.ReceiptSearchCriteria;
import com.bbva.pisd.dto.insurancedao.entities.ContractEntity;
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

	List<Map<String, Object>> executeGetRoyalPolicyDetail(String contractNumber);
	List<ReceiptDTO> executeGetReceipts(String contractNumber);


}
