package com.bbva.pisd.lib.r226.transfor.bean;


import com.bbva.pisd.dto.contract.common.AmountDTO;
import com.bbva.pisd.dto.contract.common.ReceiptDTO;
import com.bbva.pisd.dto.insurancedao.constants.PISDColumn;
import com.bbva.pisd.dto.insurancedao.entities.ReceiptEntity;
import com.bbva.pisd.lib.r226.util.CatalogEnum;
import com.bbva.pisd.lib.r226.util.FunctionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ReceiptTransformBean {

    private ReceiptTransformBean(){

    }
    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptTransformBean.class);

    public static ReceiptEntity mapTransformReceiptEntity(Map<String, Object> map){
        LOGGER.info("[***] START ReceiptTransformBean mapTransformReceiptEntity map - {} ", map);

        ReceiptEntity receiptEntity = ReceiptEntity.Builder.an()
                .withInsuranceContractEntityId( (String)map.get(PISDColumn.Receipt.FIELD_INSURANCE_CONTRACT_ENTITY_ID))
                .withInsuranceContractBranchId( (String)map.get(PISDColumn.Receipt.FIELD_INSURANCE_CONTRACT_BRANCH_ID))
                .withInsrcContractIntAccountId( (String)map.get(PISDColumn.Receipt.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID))
                .withPolicyReceiptId(FunctionUtils.mapConvertToInteger(PISDColumn.Receipt.FIELD_POLICY_RECEIPT_ID, map))
                .withInsuranceCompanyReceiptId( (String)map.get(PISDColumn.Receipt.FIELD_INSURANCE_COMPANY_RECEIPT_ID))
                .withPremiumPaymentReceiptAmount( FunctionUtils.mapConvertToDouble(PISDColumn.Receipt.FIELD_PREMIUM_PAYMENT_RECEIPT_AMOUNT, map))
                .withCurrencyId( (String)map.get(PISDColumn.Receipt.FIELD_CURRENCY_ID))
                .withReceiptStartDate((String)map.get(PISDColumn.Receipt.FIELD_RECEIPT_START_DATE))
                .withReceiptEndDate((String)map.get(PISDColumn.Receipt.FIELD_RECEIPT_END_DATE))
                .withReceiptStatusType( (String)map.get(PISDColumn.Receipt.FIELD_RECEIPT_STATUS_TYPE))
                .build();
        LOGGER.info("[***] END ReceiptTransformBean mapTransformReceiptEntity map - {} ", receiptEntity);
        return receiptEntity;
    }


    public static ReceiptDTO mapTransformReceiptDTO(Map<String, Object> map){
        LOGGER.info("[***] ReceiptTransformBean mapTransformReceiptDTO map - {} ", map);
        return ReceiptDTO.Builder.an()
                .insuranceContractEntityId((String) map.get(CatalogEnum.INSURANCE_CONTRACT_ENTITY_ID.getValue()))
                .insuranceContractBranchId((String) map.get(CatalogEnum.INSURANCE_CONTRACT_BRANCH_ID.getValue()))
                .insrcContractIntAccountId((String) map.get(CatalogEnum.INSRC_CONTRACT_INT_ACCOUNT_ID.getValue()))
                .policyReceiptId(FunctionUtils.mapConvertToInteger(CatalogEnum.POLICY_RECEIPT_ID.getValue(),map))
                .paymentAmount(AmountDTO.Builder.an().amount(FunctionUtils.mapConvertToDouble(CatalogEnum.PREMIUM_PAYMENT_RECEIPT_AMOUNT.getValue(), map))
                        .currency((String) map.get(CatalogEnum.CURRENCY_ID.getValue()))
                        .build())
                .insrncCoReceiptStatusType((String) map.get(CatalogEnum.INSRNC_CO_RECEIPT_STATUS_TYPE.getValue()))
                .status((String) map.get(CatalogEnum.RECEIPT_STATUS_TYPE.getValue()))
                .renewalReceiptSeqNumber(FunctionUtils.mapConvertToInteger(CatalogEnum.RENEWAL_RECEIPT_SEQ_NUMBER.getValue(),map))
                .renewalNumber(FunctionUtils.mapConvertToInteger(CatalogEnum.RENEWAL_NUMBER.getValue(),map))
                .receiptIssueDate((String) map.get(CatalogEnum.RECEIPT_ISSUE_DATE.getValue()))
                .receiptStartDate((String) map.get(CatalogEnum.RECEIPT_START_DATE.getValue()))
                .receiptEndDate((String) map.get(CatalogEnum.RECEIPT_END_DATE.getValue()))
                .receiptExpirationDate((String) map.get(CatalogEnum.RECEIPT_EXPIRATION_DATE.getValue()))
                .build();
    }

}
