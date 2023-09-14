package com.bbva.pisd.lib.r226.transfor.bean;


import com.bbva.pisd.dto.insurancedao.constants.PISDColumn;
import com.bbva.pisd.dto.insurancedao.entities.ReceiptEntity;
import com.bbva.pisd.lib.r226.transfor.list.ReceiptTransformList;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ReceiptTransformBean {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ReceiptTransformBean.class);

    public static ReceiptEntity mapTransformReceiptEntity(Map<String, Object> map){
        LOGGER.info("[***] ReceiptTransformBean mapTransformReceiptEntity map - {} ", map);

        return ReceiptEntity.Builder.an()
                .withContract(ContractTransformBean.mapTransformContractEntity(map))
                .withInsuranceContractEntityId( (String)map.get(PISDColumn.Receipt.FIELD_INSURANCE_CONTRACT_ENTITY_ID))
                .withInsuranceContractBranchId( (String)map.get(PISDColumn.Receipt.FIELD_INSURANCE_CONTRACT_BRANCH_ID))
                .withInsrcContractIntAccountId( (String)map.get(PISDColumn.Receipt.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID))
                .withPolicyReceiptId( (Integer) map.get(PISDColumn.Receipt.FIELD_POLICY_RECEIPT_ID))
                .withInsuranceCompanyReceiptId( (String)map.get(PISDColumn.Receipt.FIELD_INSURANCE_COMPANY_RECEIPT_ID))
                .withPremiumPaymentReceiptAmount( (Double)map.get(PISDColumn.Receipt.FIELD_PREMIUM_PAYMENT_RECEIPT_AMOUNT))
                .withCurrencyId( (String)map.get(PISDColumn.Receipt.FIELD_CURRENCY_ID))
                .withReceiptStatusType( (String)map.get(PISDColumn.Receipt.FIELD_RECEIPT_STATUS_TYPE))
                .build();
    }

}
