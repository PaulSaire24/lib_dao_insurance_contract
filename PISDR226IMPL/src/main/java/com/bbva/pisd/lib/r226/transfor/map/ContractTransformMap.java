package com.bbva.pisd.lib.r226.transfor.map;

import com.bbva.pisd.dto.contract.search.CertifyBankCriteria;
import com.bbva.pisd.dto.insurancedao.constants.PISDColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ContractTransformMap {

    private ContractTransformMap(){}

    private static final Logger LOGGER = LoggerFactory.getLogger(ContractTransformMap.class);
    private static final String FIELD_BIOMETRIC_KEY_ID="BIOMETRIC_KEY_ID";

    public static Map<String, Object> contractTransformMapContract(String contractEntityId, String contractBranchId, String contractIntAccount,String biometricId,String usuario){
        LOGGER.info("[***] ContractTransformBean objTransformContractMap contractEntityId - {} ", contractEntityId);
        LOGGER.info("[***] ContractTransformBean objTransformContractMap contractBranchId - {} ", contractBranchId);
        LOGGER.info("[***] ContractTransformBean objTransformContractMap contractIntAccount - {} ", contractIntAccount);

        Map<String,Object>contractMap= new HashMap<>();
        contractMap.put(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_ENTITY_ID, contractEntityId);
        contractMap.put(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_BRANCH_ID, contractBranchId);
        contractMap.put(PISDColumn.Contract.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID, contractIntAccount);
        contractMap.put(FIELD_BIOMETRIC_KEY_ID,biometricId);
        contractMap.put(PISDColumn.Contract.FIELD_USER_AUDIT_ID, usuario);


        LOGGER.info("[***] ContractTransformBean objTransformContractMap contractMap - {} ", contractMap);
        return contractMap;
    }

    public static Map<String, Object> contractTransformMapone(String biometricId ){
        LOGGER.info("[***] ContractTransformBean objTransformContractMap contractEntityId - {} ", biometricId);

        Map<String,Object>contractMap= new HashMap<>();
        contractMap.put(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_ENTITY_ID, biometricId);
        LOGGER.info("[***] ContractTransformBean objTransformContractMap contractMap - {} ", contractMap);
        return contractMap;
    }

    public static Map<String,Object> transformContractByIdAndProductMap(String contractId,String productId){
        Map<String,Object> arguments = transformContractById(contractId);
        arguments.put(PISDColumn.Contract.FIELD_INSURANCE_PRODUCT_ID, productId);

        LOGGER.info("[***] ContractTransformMap transformContractByIdAndProductMap arguments - {} ", arguments);
        return arguments;
    }

    public static Map<String,Object> transformContractById(String contractId){
        Map<String,Object> arguments = new HashMap<>();
        arguments.put(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_ENTITY_ID, contractId.substring(0,4));
        arguments.put(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_BRANCH_ID, contractId.substring(4,8));
        arguments.put(PISDColumn.Contract.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID,contractId.substring(10));

        LOGGER.info("[***] ContractTransformMap transformContractById arguments - {} ", arguments);
        return arguments;
    }

    public static Map<String, Object> certifyBankCriteriaTransformMap(CertifyBankCriteria certifyBankCriteria){
        LOGGER.info("[***] ContractTransformMap certifyBankCriteriaTransformMap receiptSearchCriteriaDTO - {} ",certifyBankCriteria);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_ENTITY_ID, certifyBankCriteria.getInsuranceContractEntityId() );
        parameters.put(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_BRANCH_ID, certifyBankCriteria.getInsuranceContractBranchId() );
        parameters.put(PISDColumn.Contract.FIELD_CONTRACT_FIRST_VERFN_DIGIT_ID, certifyBankCriteria.getContractFirstVerfnDigitId());
        parameters.put(PISDColumn.Contract.FIELD_CONTRACT_SECOND_VERFN_DIGIT_ID, certifyBankCriteria.getContractSecondVerfnDigitId());
        parameters.put(PISDColumn.Contract.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID, certifyBankCriteria.getInsrcContractIntAccountId());

        LOGGER.info("[***] ContractTransformMap certifyBankCriteriaTransformMap parameters - {} ",parameters);

        return parameters;
    }
}
