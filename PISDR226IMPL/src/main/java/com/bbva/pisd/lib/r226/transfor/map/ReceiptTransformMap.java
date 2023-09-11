package com.bbva.pisd.lib.r226.transfor.map;

import com.bbva.pisd.dto.contract.constants.PISDColumn;
import com.bbva.pisd.dto.contract.entity.ReceiptSearchCriteriaDTO;
import com.bbva.pisd.lib.r226.interfaces.ContractDAO;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ReceiptTransformMap {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ReceiptTransformMap.class);

    public static Map<String, Object> ReceiptSearchCriteriaTransformMap(ReceiptSearchCriteriaDTO receiptSearchCriteriaDTO){
        LOGGER.info("[***] ReceiptTransformMap ReceiptSearchCriteriaTransformMap receiptSearchCriteriaDTO - {} ",receiptSearchCriteriaDTO);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(PISDColumn.Contract.FIELD_PAYMENT_MEANS_TYPE, receiptSearchCriteriaDTO.getContractPaymentMeansType());
        parameters.put(PISDColumn.Contract.FIELD_CONTRACT_STATUS_ID, receiptSearchCriteriaDTO.getContractStatusId());
        parameters.put(PISDColumn.Receipt.FIELD_RECEIPT_STATUS_TYPE, receiptSearchCriteriaDTO.getReceiptStatusSearch());

        LOGGER.info("[***] ReceiptTransformMap ReceiptSearchCriteriaTransformMap parameters - {} ",parameters);

        return parameters;
    }

}
