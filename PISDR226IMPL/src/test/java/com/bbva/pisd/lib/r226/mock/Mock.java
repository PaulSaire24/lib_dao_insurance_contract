package com.bbva.pisd.lib.r226.mock;

import com.bbva.pisd.dto.contract.search.CertifyBankCriteria;
import com.bbva.pisd.dto.insurancedao.entities.ContractEntity;
import com.bbva.pisd.dto.insurancedao.entities.ReceiptEntity;

public class Mock {

    public static ContractEntity mockContract(){

      return  ContractEntity.ContractBuilder.an()
                .withInsuranceContractEntityId("0011")
                .withInsuranceContractBranchId("4444")
                .withContractFirstVerfnDigitId("1")
                .withContractSecondVerfnDigitId("1")
                .withInsrcContractIntAccountId("1234567890")
                .withCurrencyId("PEN")
                .build();
    }
    public static ReceiptEntity mockReceipts(){

        return  ReceiptEntity.Builder.an()
                .withInsuranceContractEntityId("0011")
                .withInsuranceContractBranchId("4444")
                .withInsrcContractIntAccountId("1234567890")
                .withCurrencyId("PEN")
                .withPolicyReceiptId(12)
                .withReceiptStatusType("PEN")

                .build();
    }

    public static CertifyBankCriteria mockCertifyCriteria(){

        return  CertifyBankCriteria.CertifyBankCriteriaBuilder.an()
                .withInsuranceContractEntityId("0011")
                .withInsuranceContractBranchId("4444")
                .withContractFirstVerfnDigitId("1")
                .withContractSecondVerfnDigitId("1")
                .withInsrcContractIntAccountId("1234567890")
                .build();
    }

    public static ContractEntity mockContract2(){

        return  ContractEntity.ContractBuilder.an()
                .withInsuranceContractEntityId("0011")
                .withInsuranceContractBranchId("4444")
                .withContractFirstVerfnDigitId("1")
                .withContractSecondVerfnDigitId("1")
                .withInsrcContractIntAccountId("1234567890")
                .withCurrencyId("PEN")
                .withCustomerId("001123")
                .build();
    }
}
