package com.bbva.pisd.lib.r226.transfor.map;

import com.bbva.pisd.dto.contract.search.ReceiptSearchCriteria;
import com.bbva.pisd.dto.insurancedao.constants.PISDColumn;
import com.bbva.pisd.lib.r226.util.CatalogEnum;
import java.util.Collections;
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

    public static Map<String, Object> receiptSearchTransformMap(String contractNumber){

        if (contractNumber == null || contractNumber.length() != 20) {
            LOGGER.info("PISDR103Impl - mapContractNumber - Número de contrato no válido ------ contractNumber: {}", contractNumber);
            return Collections.emptyMap();
        }
        Map<String, Object> arguments = new HashMap<>();
        arguments.put(CatalogEnum.ENTITY_ID.name(), contractNumber.substring(0, 4));
        arguments.put(CatalogEnum.BRANCH_ID.name(), contractNumber.substring(4, 8));
        arguments.put(CatalogEnum.FIRST_VERFN_DIGIT.name(), contractNumber.substring(8, 9));
        arguments.put(CatalogEnum.SECOND_VERFN_DIGIT.name(), contractNumber.substring(9, 10));
        arguments.put(CatalogEnum.ACCOUNT_ID.name(), contractNumber.substring(10, 20));
        arguments.put(CatalogEnum.ESTADO_DE_POLIZA.name(), CatalogEnum.ESTADO_DE_POLIZA.getValue());
        arguments.put(CatalogEnum.TIPO_DE_CONTACTO.name(), CatalogEnum.TIPO_DE_CONTACTO.getValue());
        arguments.put(CatalogEnum.TIPO_DE_UNIDAD.name(), CatalogEnum.TIPO_DE_UNIDAD.getValue());
        arguments.put(CatalogEnum.FRECUENCIA_DE_PAGO.name(), CatalogEnum.FRECUENCIA_DE_PAGO.getValue());
        arguments.put(CatalogEnum.RECEIPT_STATUS_TYPE.name(), CatalogEnum.INC.name());

        return arguments;

    }





}
