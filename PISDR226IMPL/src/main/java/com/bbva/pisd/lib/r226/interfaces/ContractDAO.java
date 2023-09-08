package com.bbva.pisd.lib.r226.interfaces;

import com.bbva.pisd.dto.contract.entity.ContractEntity;

import java.util.List;

public interface ContractDAO {

    ContractEntity findByCertifiedBank(String nroCertifyBank);

    boolean updateReceiptsPayment(List<ReceiptEntity> receipts);

    List<ReceiptEntity> findReceiptByChargeEntityExtern(PaymentSearchCriteriaDTO paymentSearchCriteriaDTO);

}
