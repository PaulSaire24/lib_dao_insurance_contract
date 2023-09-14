package com.bbva.pisd.lib.r226.interfaces;

import com.bbva.pisd.dto.contract.search.ReceiptSearchCriteria;
import com.bbva.pisd.dto.insurancedao.entities.ContractEntity;
import com.bbva.pisd.dto.insurancedao.entities.ReceiptEntity;

import java.util.List;
import java.util.Map;

public interface ContractDAO {

    ContractEntity findByCertifiedBank(String nroCertifyBank);

    boolean updateReceiptsPayment(List<ReceiptEntity> receipts);

    List<ReceiptEntity> findReceiptByChargeEntityExtern(ReceiptSearchCriteria receiptSearchCriteriaDTO);


}
