package com.bbva.pisd.lib.r226.transfor.list;

import com.bbva.pisd.dto.insurancedao.entities.ReceiptEntity;
import com.bbva.pisd.lib.r226.transfor.bean.ReceiptTransformBean;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReceiptTransformList {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ReceiptTransformList.class);

    public static List<ReceiptEntity> mapListTransformListReceiptEntity(List<Map<String, Object>> listReceiptsMap){
        LOGGER.info("[***] ReceiptTransformList mapListTransformListReceiptEntity listReceiptsMap - {} ", listReceiptsMap);

        if(CollectionUtils.isEmpty(listReceiptsMap)){
            return new ArrayList<>();
        }

        return listReceiptsMap.stream().map(ReceiptTransformBean::mapTransformReceiptEntity).collect(Collectors.toList());
    }

}
