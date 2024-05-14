package com.bbva.pisd.lib.r226.transfor.list;

import com.bbva.pisd.dto.contract.common.ReceiptDTO;
import com.bbva.pisd.dto.insurancedao.entities.ReceiptEntity;
import com.bbva.pisd.lib.r226.transfor.bean.ReceiptTransformBean;
import com.bbva.pisd.lib.r226.util.CatalogEnum;
import com.bbva.pisd.lib.r226.util.FunctionUtils;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReceiptTransformList {

    private ReceiptTransformList(){

    }

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ReceiptTransformList.class);

    public static List<ReceiptEntity> transformListMapToListReceiptEntity(List<Map<String, Object>> listReceiptMap){
        LOGGER.info("[***] ReceiptTransformList transformListMapToListContractEntity listContractMap - {} ", listReceiptMap);
        if(CollectionUtils.isEmpty(listReceiptMap)){
            return new ArrayList<>();
        }
        return listReceiptMap.stream().map(ReceiptTransformBean::mapTransformReceiptEntity).collect(Collectors.toList());
    }


    public static List<ReceiptDTO> transformListMapToListReceiptDTO(List<Map<String, Object>> listReceipt){
        LOGGER.info("[***] ReceiptTransformList transformListMapToListReceiptDTO listReceipt - {} ", listReceipt);
        if(CollectionUtils.isEmpty(listReceipt)){
            return new ArrayList<>();
        }
        return listReceipt.stream().map(ReceiptTransformBean::mapTransformReceiptDTO).collect(Collectors.toList());
    }


}
