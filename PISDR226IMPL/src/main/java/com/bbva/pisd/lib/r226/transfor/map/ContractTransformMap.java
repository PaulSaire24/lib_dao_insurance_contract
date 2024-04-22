package com.bbva.pisd.lib.r226.transfor.map;
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
        Map<String,Object> arguments = new HashMap<>();
        arguments.put(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_ENTITY_ID, contractId.substring(0,4));
        arguments.put(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_BRANCH_ID, contractId.substring(4,8));
        arguments.put(PISDColumn.Contract.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID,contractId.substring(10));
        arguments.put(PISDColumn.Contract.FIELD_INSURANCE_PRODUCT_ID, productId);

        LOGGER.info("[***] ContractTransformMap transformContractByIdAndProductMap arguments - {} ", arguments);
        return arguments;
    }


}
