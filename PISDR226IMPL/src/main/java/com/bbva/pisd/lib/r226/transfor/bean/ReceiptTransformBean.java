package com.bbva.pisd.lib.r226.transfor.bean;


import com.bbva.pisd.dto.insurancedao.constants.PISDColumn;
import com.bbva.pisd.dto.insurancedao.entities.ReceiptEntity;
import com.bbva.pisd.lib.r226.util.FunctionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ReceiptTransformBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptTransformBean.class);

    public static ReceiptEntity mapTransformReceiptEntity(Map<String, Object> map){
        LOGGER.info("[***] ReceiptTransformBean mapTransformReceiptEntity map - {} ", map);
        return ReceiptEntity.Builder.an()
                .withContract(ContractTransformBean.mapTransformContractEntity(map).build())
                .withInsuranceContractEntityId( (String)map.get(PISDColumn.Receipt.FIELD_INSURANCE_CONTRACT_ENTITY_ID))
                .withInsuranceContractBranchId( (String)map.get(PISDColumn.Receipt.FIELD_INSURANCE_CONTRACT_BRANCH_ID))
                .withInsrcContractIntAccountId( (String)map.get(PISDColumn.Receipt.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID))
                .withPolicyReceiptId(FunctionUtils.mapConvertToInteger(PISDColumn.Receipt.FIELD_POLICY_RECEIPT_ID, map))
                .withInsuranceCompanyReceiptId( (String)map.get(PISDColumn.Receipt.FIELD_INSURANCE_COMPANY_RECEIPT_ID))
                .withPremiumPaymentReceiptAmount( FunctionUtils.mapConvertToDouble(PISDColumn.Receipt.FIELD_PREMIUM_PAYMENT_RECEIPT_AMOUNT, map))
                .withCurrencyId( (String)map.get(PISDColumn.Receipt.FIELD_CURRENCY_ID))
                .withReceiptStatusType( (String)map.get(PISDColumn.Receipt.FIELD_RECEIPT_STATUS_TYPE))
                .build();
    }

    public static Map<String, Object> entityTransformReceiptMap(ReceiptEntity receipt){
        LOGGER.info("[***] ReceiptTransformBean objTransformReceiptEntity receipt - {} ", receipt);
        Map<String, Object> mapReceipt = new HashMap<>();
        mapReceipt.put(PISDColumn.Receipt.FIELD_INSURANCE_CONTRACT_ENTITY_ID,receipt.getInsuranceContractEntityId());
        mapReceipt.put(PISDColumn.Receipt.FIELD_INSURANCE_CONTRACT_BRANCH_ID,receipt.getInsuranceContractBranchId());
        mapReceipt.put(PISDColumn.Receipt.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID,receipt.getInsrcContractIntAccountId());
        mapReceipt.put(PISDColumn.Receipt.FIELD_POLICY_RECEIPT_ID,receipt.getPolicyReceiptId());
        mapReceipt.put(PISDColumn.Receipt.FIELD_INSURANCE_COMPANY_RECEIPT_ID,receipt.getInsuranceCompanyReceiptId());
        mapReceipt.put(PISDColumn.Receipt.FIELD_PREMIUM_PAYMENT_RECEIPT_AMOUNT,receipt.getPremiumPaymentReceiptAmount());
        mapReceipt.put(PISDColumn.Receipt.FIELD_CURRENCY_ID,receipt.getCurrencyId());
        mapReceipt.put(PISDColumn.Receipt.FIELD_RECEIPT_STATUS_TYPE,receipt.getReceiptStatusType());
        return mapReceipt;
    }

}
