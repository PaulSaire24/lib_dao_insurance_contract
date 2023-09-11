package com.bbva.pisd.lib.r226.transfor.list;

import com.bbva.pisd.dto.contract.entity.ReceiptEntity;
import com.bbva.pisd.lib.r226.transfor.bean.ReceiptTransforBean;
import com.bbva.pisd.lib.r226.transfor.map.ReceiptTransforMap;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReceiptTransforList {

    public static List<ReceiptEntity> mapListTransforListReceiptEntity(List<Map<String, Object>> listReceiptsMap){
        if(CollectionUtils.isEmpty(listReceiptsMap)){
            return new ArrayList<>();
        }
        return listReceiptsMap.stream().map(ReceiptTransforBean::mapTransforReceiptEntity).collect(Collectors.toList());
    }

}
