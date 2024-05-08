package com.bbva.pisd.lib.r226.transfor.list;

import com.bbva.pisd.dto.insurancedao.constants.PISDColumn;
import com.bbva.pisd.dto.insurancedao.entities.ContractEntity;
import com.bbva.pisd.lib.r226.transfor.bean.ContractTransformBean;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ContractTransformList {

    private ContractTransformList(){

    }
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ContractTransformList.class);

    public static List<ContractEntity> transformListMapToListContractEntity(List<Map<String, Object>> listContractMap){
        LOGGER.info("[***] ReceiptTransformList transformListMapToListContractEntity listContractMap - {} ", listContractMap);
        if(CollectionUtils.isEmpty(listContractMap)){
            return new ArrayList<>();
        }
        List<ContractEntity>contractEntityList=new ArrayList<>();

        IntStream.range(0,listContractMap.size()).forEach(i->{
            Map<String, Object>receiptPrimaryMap = listContractMap.get(i);
            String entityP  = receiptPrimaryMap.get(PISDColumn.Receipt.FIELD_INSURANCE_CONTRACT_ENTITY_ID).toString();
            String branchP  = receiptPrimaryMap.get(PISDColumn.Receipt.FIELD_INSURANCE_CONTRACT_BRANCH_ID).toString();
            String accountP = receiptPrimaryMap.get(PISDColumn.Receipt.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID).toString();

            String certifyBankPrimary = entityP.concat(branchP).concat(accountP);

            List<Map<String, Object>>receiptMaps = new ArrayList<>();
            receiptMaps.add(receiptPrimaryMap);
            IntStream.range(i+1,listContractMap.size()).forEach(j->{
                Map<String, Object>receiptSecondaryMap = listContractMap.get(j);
                String entity  = receiptSecondaryMap.get(PISDColumn.Receipt.FIELD_INSURANCE_CONTRACT_ENTITY_ID).toString();
                String branch  = receiptSecondaryMap.get(PISDColumn.Receipt.FIELD_INSURANCE_CONTRACT_BRANCH_ID).toString();
                String account = receiptSecondaryMap.get(PISDColumn.Receipt.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID).toString();
                String certifyBankSecondary = entity.concat(branch).concat(account);

                if(certifyBankPrimary.equalsIgnoreCase(certifyBankSecondary)){
                    receiptMaps.add(receiptSecondaryMap);
                }
            });
            boolean isNotGroupReceipt=contractEntityList.stream().noneMatch(contractEntity -> {
                String entity  = contractEntity.getInsuranceContractEntityId();
                String branch  = contractEntity.getInsuranceContractBranchId();
                String account = contractEntity.getInsrcContractIntAccountId();
                String contrato = entity.concat(branch).concat(account);

                return contrato.equalsIgnoreCase(certifyBankPrimary);
            });
            if (isNotGroupReceipt){
                ContractEntity contractEntity = ContractTransformBean.mapTransformContractEntityAndReceiptEntity(receiptPrimaryMap,receiptMaps);
                contractEntityList.add(contractEntity);
            }

        });
        return contractEntityList;
    }
}