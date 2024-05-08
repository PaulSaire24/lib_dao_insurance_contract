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

    public static List<ReceiptDTO> getDetailMapped(List<Map<String, Object>> rawDetail) {

        LOGGER.info("***** PISDR226Impl - getDetailMapped : {} *****", rawDetail);

        List<ReceiptDTO> receipts = new ArrayList<>();

        rawDetail.forEach(map -> {

            ReceiptDTO re = ReceiptDTO.Builder.an()
                    .insuranceContractEntityId((String) map.get(CatalogEnum.INSURANCE_CONTRACT_ENTITY_ID.getValue()))
                    .insuranceContractBranchId((String) map.get(CatalogEnum.INSURANCE_CONTRACT_BRANCH_ID.getValue()))
                    .insrcContractIntAccountId((String) map.get(CatalogEnum.INSRC_CONTRACT_INT_ACCOUNT_ID.getValue()))
                    .policyReceiptId(FunctionUtils.mapConvertToInteger(CatalogEnum.POLICY_RECEIPT_ID.getValue(),map))
                    .premiumPaymentReceiptAmount(FunctionUtils.mapConvertToDouble(CatalogEnum.PREMIUM_PAYMENT_RECEIPT_AMOUNT.getValue(),map))
                    .insrncCoReceiptStatusType((String) map.get(CatalogEnum.INSRNC_CO_RECEIPT_STATUS_TYPE.getValue()))
                    .receiptStatusType((String) map.get(CatalogEnum.RECEIPT_STATUS_TYPE.getValue()))
                    .renewalReceiptSeqNumber(FunctionUtils.mapConvertToInteger(CatalogEnum.RENEWAL_RECEIPT_SEQ_NUMBER.getValue(),map))
                    .renewalNumber(FunctionUtils.mapConvertToInteger(CatalogEnum.RENEWAL_NUMBER.getValue(),map))
                    .receiptIssueDate((String) map.get(CatalogEnum.RECEIPT_ISSUE_DATE.getValue()))
                    .receiptStartDate((String) map.get(CatalogEnum.RECEIPT_START_DATE.getValue()))
                    .receiptEndDate((String) map.get(CatalogEnum.RECEIPT_END_DATE.getValue()))
                    .receiptExpirationDate((String) map.get(CatalogEnum.RECEIPT_EXPIRATION_DATE.getValue()))
                    .build();

            receipts.add(re);

        });

        return receipts;

    }


}
