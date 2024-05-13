package com.bbva.pisd.lib.r226.interfaces;

import com.bbva.pisd.dto.contract.search.ReceiptSearchCriteria;
import com.bbva.pisd.dto.insurancedao.entities.ContractEntity;

import java.util.List;
import java.util.Map;

public interface ContractDAO {
    List<ContractEntity> findContractBySearchCriteria(ReceiptSearchCriteria SearchCriteria);

    boolean updateBiometricId(String insuranceContractId, String biometricId, String usuario);
    public Boolean findByContract(String  biometricId);
    ContractEntity findContractByIdAndProductId(String contractId, String productId);
    boolean findQuotationExistInContract(String quotationId);
    int insertInsuranceQuotation(Map<String,Object> map);
}
