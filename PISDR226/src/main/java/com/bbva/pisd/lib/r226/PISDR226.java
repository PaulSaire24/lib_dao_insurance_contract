package com.bbva.pisd.lib.r226;

import com.bbva.pisd.dto.contract.search.ReceiptSearchCriteria;
import com.bbva.pisd.dto.insurancedao.entities.ContractEntity;
import com.bbva.pisd.dto.insurancedao.entities.ReceiptEntity;

import java.util.List;

/**
 * The  interface PISDR226 class...
 */
public interface PISDR226 {


	void executeSetCommonJdbcTemplate(Object commonJdbcTemplate);

	ContractEntity executeFindByCertifiedBank(String nroCertifyBank);

	boolean executeUpdateReceiptsPayment(List<ReceiptEntity> receipts);

	List<ReceiptEntity> executeFindReceiptByChargeEntityExtern(ReceiptSearchCriteria receiptSearchCriteriaDTO);

}
