package com.bbva.pisd.lib.r226;

import com.bbva.pisd.dto.contract.entity.ContractEntity;
import com.bbva.pisd.dto.contract.entity.ReceiptEntity;
import com.bbva.pisd.dto.contract.entity.ReceiptSearchCriteriaDTO;

import java.util.List;

/**
 * The  interface PISDR226 class...
 */
public interface PISDR226 {


	void executeSetCommonJdbcTemplate(Object commonJdbcTemplate);

	ContractEntity executeFindByCertifiedBank(String nroCertificadoBanco);

	boolean executeUpdateReceiptsPayment(List<ReceiptEntity> receipts);

	List<ReceiptEntity> executeFindReceiptByChargeEntityExtern(ReceiptSearchCriteriaDTO receiptSearchCriteriaDTO);

}
