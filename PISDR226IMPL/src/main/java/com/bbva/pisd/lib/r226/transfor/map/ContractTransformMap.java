package com.bbva.pisd.lib.r226.transfor.map;

import com.bbva.pisd.dto.contract.search.CertifyBankCriteria;
import com.bbva.pisd.dto.insurancedao.constants.PISDColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ContractTransformMap {


    private static final Logger LOGGER = LoggerFactory.getLogger(ContractTransformMap.class);

    public static Map<String, Object> certifyBankCriteriaTransformMap(CertifyBankCriteria certifyBankCriteria){
        LOGGER.info("[***] ContractTransformMap certifyBankCriteriaTransformMap receiptSearchCriteriaDTO - {} ",certifyBankCriteria);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_ENTITY_ID, certifyBankCriteria.getInsuranceContractEntityId() );
        parameters.put(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_BRANCH_ID, certifyBankCriteria.getInsuranceContractBranchId() );
        parameters.put(PISDColumn.Contract.FIELD_CONTRACT_FIRST_VERFN_DIGIT_ID, certifyBankCriteria.getContractFirstVerfnDigitId());
        parameters.put(PISDColumn.Contract.FIELD_CONTRACT_SECOND_VERFN_DIGIT_ID, certifyBankCriteria.getContractSecondVerfnDigitId());
        parameters.put(PISDColumn.Contract.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID, certifyBankCriteria.getInsrcContractIntAccountId() );
        LOGGER.info("[***] ContractTransformMap certifyBankCriteriaTransformMap parameters - {} ",parameters);
        return parameters;
    }
}
