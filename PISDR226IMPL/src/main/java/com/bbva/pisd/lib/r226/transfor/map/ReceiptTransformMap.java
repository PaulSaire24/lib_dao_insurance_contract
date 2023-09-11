package com.bbva.pisd.lib.r226.transfor.map;

import com.bbva.pisd.dto.contract.constants.PISDColumn;
import com.bbva.pisd.dto.contract.entity.ReceiptSearchCriteriaDTO;

import java.util.HashMap;
import java.util.Map;

public class ReceiptTransformMap {

    public static Map<String, Object> ReceiptSearchCriteriaTransformMap(ReceiptSearchCriteriaDTO receiptSearchCriteriaDTO){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(PISDColumn.Contract.FIELD_PAYMENT_MEANS_TYPE, receiptSearchCriteriaDTO.getContractPaymentMeansType());
        parameters.put(PISDColumn.Contract.FIELD_CONTRACT_STATUS_ID, receiptSearchCriteriaDTO.getContractStatusId());
        parameters.put(PISDColumn.Receipt.FIELD_RECEIPT_STATUS_TYPE, receiptSearchCriteriaDTO.getReceiptStatusSearch());
        return parameters;
    }

}
