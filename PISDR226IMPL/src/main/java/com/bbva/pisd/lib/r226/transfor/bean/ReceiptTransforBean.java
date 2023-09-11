package com.bbva.pisd.lib.r226.transfor.bean;

import com.bbva.pisd.dto.contract.constants.PISDColumn;
import com.bbva.pisd.dto.contract.entity.ReceiptEntity;

import java.util.Map;

public class ReceiptTransforBean {

    public static ReceiptEntity mapTransforReceiptEntity(Map<String, Object> map){
        ReceiptEntity receipt = new ReceiptEntity();
        receipt.setContract(ContractTransforBean.mapTransforContractEntity(map));
        receipt.setInsuranceContractEntityId( (String)map.get(PISDColumn.Receipt.FIELD_INSURANCE_CONTRACT_ENTITY_ID));
        receipt.setInsuranceContractBranchId( (String)map.get(PISDColumn.Receipt.FIELD_INSURANCE_CONTRACT_BRANCH_ID));
        receipt.setInsrcContractIntAccountId( (String)map.get(PISDColumn.Receipt.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID));
        receipt.setPolicyReceiptId( (Integer) map.get(PISDColumn.Receipt.FIELD_POLICY_RECEIPT_ID));
        receipt.setInsuranceCompanyReceiptId( (String)map.get(PISDColumn.Receipt.FIELD_INSURANCE_COMPANY_RECEIPT_ID));
        receipt.setPremiumPaymentReceiptAmount( (Double)map.get(PISDColumn.Receipt.FIELD_PREMIUM_PAYMENT_RECEIPT_AMOUNT));
        receipt.setCurrencyId( (String)map.get(PISDColumn.Receipt.FIELD_CURRENCY_ID));
        receipt.setReceiptStatusType( (String)map.get(PISDColumn.Receipt.FIELD_RECEIPT_STATUS_TYPE));
        return receipt;
    }

}
