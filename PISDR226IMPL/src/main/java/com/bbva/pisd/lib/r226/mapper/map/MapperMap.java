package com.bbva.pisd.lib.r226.mapper.map;

import com.bbva.pisd.dto.contract.constants.PISDColumn;
import com.bbva.pisd.dto.contract.entity.ContractEntity;
import com.bbva.pisd.dto.contract.entity.ReceiptEntity;
import com.bbva.pisd.dto.contract.entity.ReceiptSearchCriteriaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapperMap {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapperMap.class);

    public static Map<String, Object> getMapSelectFindReceipts(ReceiptSearchCriteriaDTO receiptSearchCriteriaDTO){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(PISDColumn.Contract.FIELD_PAYMENT_MEANS_TYPE, receiptSearchCriteriaDTO.getContractPaymentMeansType()); //Solo pago por terceros
        parameters.put(PISDColumn.Contract.FIELD_CONTRACT_STATUS_ID, receiptSearchCriteriaDTO.getContractStatusId());//Solo formalizados
        return parameters;
    }

    public static List<ReceiptEntity> getListReceipts(List<Map<String, Object>> list){
        List<ReceiptEntity> listReceipts = new ArrayList<>();

        if(!CollectionUtils.isEmpty(list)){
            list.forEach(map -> listReceipts.add( getReceipt(map)) );
        }

        return listReceipts;
    }

    public static ReceiptEntity getReceipt(Map<String, Object> map){
        ReceiptEntity receipt = new ReceiptEntity();
        ContractEntity contract = new ContractEntity();
        contract.setInsuranceContractEntityId( (String)map.get(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_ENTITY_ID));
        contract.setInsuranceContractBranchId( (String)map.get(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_BRANCH_ID));
        contract.setContractFirstVerfnDigitId( (String)map.get(PISDColumn.Contract.FIELD_CONTRACT_FIRST_VERFN_DIGIT_ID));
        contract.setContractSecondVerfnDigitId( (String)map.get(PISDColumn.Contract.FIELD_CONTRACT_SECOND_VERFN_DIGIT_ID));
        contract.setInsrcContractIntAccountId( (String)map.get(PISDColumn.Contract.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID));
        contract.setPolicyId( (String)map.get(PISDColumn.Contract.FIELD_POLICY_ID));
        contract.setCustomerId( (String)map.get(PISDColumn.Contract.FIELD_CUSTOMER_ID));
        contract.setInsuranceProductId( (String)map.get(PISDColumn.Contract.FIELD_INSURANCE_PRODUCT_ID));
        contract.setInsuranceModalityType( (String)map.get(PISDColumn.Contract.FIELD_INSURANCE_MODALITY_TYPE));
        contract.setContractStatusId( (String)map.get(PISDColumn.Contract.FIELD_CONTRACT_STATUS_ID));
        receipt.setContract(contract);
        receipt.setInsuranceContractEntityId( (String)map.get(PISDColumn.Receipt.FIELD_INSURANCE_CONTRACT_ENTITY_ID));
        receipt.setInsuranceContractBranchId( (String)map.get(PISDColumn.Receipt.FIELD_INSURANCE_CONTRACT_BRANCH_ID));
        receipt.setInsrcContractIntAccountId( (String)map.get(PISDColumn.Receipt.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID));
        receipt.setPolicyReceiptId( (Integer) map.get(PISDColumn.Receipt.FIELD_POLICY_RECEIPT_ID));
        receipt.setInsuranceCompanyReceiptId( (String)map.get(PISDColumn.Receipt.FIELD_INSURANCE_COMPANY_RECEIPT_ID));
        receipt.setPremiumPaymentReceiptAmount( (Double)map.get(PISDColumn.Receipt.FIELD_PREMIUM_PAYMENT_RECEIPT_AMOUNT));
        receipt.setCurrencyId( (String)map.get(PISDColumn.Receipt.FIELD_CURRENCY_ID));
        receipt.setReceiptStatusType( (String)map.get(PISDColumn.Receipt.FIELD_RECEIPT_STATUS_TYPE));
        return receipt;
    }
}
