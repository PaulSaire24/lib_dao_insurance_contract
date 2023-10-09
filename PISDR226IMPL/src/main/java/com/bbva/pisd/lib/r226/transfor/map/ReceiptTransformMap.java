package com.bbva.pisd.lib.r226.transfor.map;

import com.bbva.pisd.dto.contract.search.ReceiptSearchCriteria;
import com.bbva.pisd.dto.insurancedao.constants.PISDColumn;
import com.bbva.pisd.dto.insurancedao.entities.ReceiptEntity;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ReceiptTransformMap {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ReceiptTransformMap.class);

    public static Map<String, Object> ReceiptSearchCriteriaTransformMap(ReceiptSearchCriteria receiptSearchCriteria){
        LOGGER.info("[***] ReceiptTransformMap ReceiptSearchCriteriaTransformMap receiptSearchCriteriaDTO - {} ",receiptSearchCriteria);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(PISDColumn.Contract.FIELD_PAYMENT_MEANS_TYPE, receiptSearchCriteria.getContractPaymentMeansType());
        parameters.put(PISDColumn.Contract.FIELD_CONTRACT_STATUS_ID, receiptSearchCriteria.getContractStatusId());
        parameters.put(PISDColumn.Receipt.FIELD_RECEIPT_STATUS_TYPE, receiptSearchCriteria.getReceiptStatusSearch());

        LOGGER.info("[***] ReceiptTransformMap ReceiptSearchCriteriaTransformMap parameters - {} ",parameters);

        return parameters;
    }


    public static Map<String, Object> ReceiptTransformMapReceipts(ReceiptEntity receipt){
        LOGGER.info("[***] ReceiptTransformBean ReceiptTransformMapReceipts receipt - {} ", receipt);

        Map<String, Object> mapReceipt = new HashMap<>();

        mapReceipt.put(PISDColumn.Receipt.FIELD_INSURANCE_CONTRACT_ENTITY_ID,receipt.getInsuranceContractEntityId());
        mapReceipt.put(PISDColumn.Receipt.FIELD_INSURANCE_CONTRACT_BRANCH_ID,receipt.getInsuranceContractBranchId());
        mapReceipt.put(PISDColumn.Receipt.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID,receipt.getInsrcContractIntAccountId());
        mapReceipt.put(PISDColumn.Receipt.FIELD_POLICY_RECEIPT_ID,receipt.getPolicyReceiptId());
        mapReceipt.put(PISDColumn.Receipt.FIELD_INSURANCE_COMPANY_RECEIPT_ID,receipt.getInsuranceCompanyReceiptId());
        mapReceipt.put(PISDColumn.Receipt.FIELD_PREMIUM_PAYMENT_RECEIPT_AMOUNT,receipt.getPremiumPaymentReceiptAmount());
        mapReceipt.put(PISDColumn.Receipt.FIELD_CURRENCY_ID,receipt.getCurrencyId());
        mapReceipt.put(PISDColumn.Receipt.FIELD_RECEIPT_STATUS_TYPE,receipt.getReceiptStatusType());
        mapReceipt.put(PISDColumn.Receipt.FIELD_OPENPAY_RECEIPT_STATUS_TYPE, receipt.getCommerceReceiptStatusType());
        mapReceipt.put(PISDColumn.Receipt.FIELD_COMMERCE_SEND_OPENPAY_DATE, receipt.getCommerceSendDate());
        mapReceipt.put(PISDColumn.Receipt.FIELD_USER_AUDIT_ID, receipt.getUserAuditId());

        return mapReceipt;
    }
}
