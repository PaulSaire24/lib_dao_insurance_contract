package com.bbva.pisd.lib.r226.transfor.bean;

import com.bbva.pisd.dto.contract.entity.ContractEntity;

import java.util.Map;

public class ContractTransforBean {

    public static ContractEntity mapTransforContractEntity(Map<String,Object> map){
        ContractEntity contract = new ContractEntity();
        contract.setContractStatusId((String) map.get("X"));
        return contract;
    }


}
