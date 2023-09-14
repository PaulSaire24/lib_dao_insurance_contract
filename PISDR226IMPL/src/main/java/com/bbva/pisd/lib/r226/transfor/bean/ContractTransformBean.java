package com.bbva.pisd.lib.r226.transfor.bean;

import com.bbva.pisd.dto.insurancedao.entities.ContractEntity;

import java.util.Map;

public class ContractTransformBean {

    public static ContractEntity mapTransformContractEntity(Map<String,Object> map){
        ContractEntity contract = new ContractEntity();
        contract.setContractStatusId((String) map.get("X"));
        return contract;
    }


}
