package com.bbva.pisd.lib.r226.transfor.map;

import com.bbva.pisd.dto.contract.search.ReceiptSearchCriteria;
import com.bbva.pisd.dto.insurancedao.constants.PISDColumn;
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

}
