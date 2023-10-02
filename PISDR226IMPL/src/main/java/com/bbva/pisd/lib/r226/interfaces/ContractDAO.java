package com.bbva.pisd.lib.r226.interfaces;

import com.bbva.pisd.dto.contract.search.CertifyBankCriteria;
import com.bbva.pisd.dto.contract.search.ReceiptSearchCriteria;
import com.bbva.pisd.dto.insurancedao.entities.ContractEntity;
import com.bbva.pisd.dto.insurancedao.entities.ReceiptEntity;

import java.util.List;
import java.util.Map;

public interface ContractDAO {


    ContractEntity findByCertifiedBank(CertifyBankCriteria certifyBankCriteria);

    boolean updateReceiptsPayment(List<ReceiptEntity> receipts);
    boolean updateContractPayment(List<ContractEntity> contractEntity);

    List<ReceiptEntity> findReceiptByChargeEntityExtern(ReceiptSearchCriteria receiptSearchCriteriaDTO);

}
