package com.bbva.pisd.lib.r226.interfaces;

import com.bbva.pisd.dto.contract.entity.ContractEntity;
import com.bbva.pisd.dto.contract.entity.ReceiptEntity;
import com.bbva.pisd.dto.contract.entity.ReceiptSearchCriteriaDTO;

import java.util.List;
import java.util.Map;

public interface ContractDAO {

    ContractEntity findByCertifiedBank(String nroCertifyBank);

    boolean updateReceiptsPayment(List<ReceiptEntity> receipts);

    List<ReceiptEntity> findReceiptByChargeEntityExtern(ReceiptSearchCriteriaDTO receiptSearchCriteriaDTO);


}
