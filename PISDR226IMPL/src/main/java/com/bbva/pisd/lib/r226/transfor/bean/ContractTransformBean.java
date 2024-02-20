package com.bbva.pisd.lib.r226.transfor.bean;

import com.bbva.pisd.dto.insurancedao.constants.PISDColumn;
import com.bbva.pisd.dto.insurancedao.entities.ContractEntity;
import com.bbva.pisd.dto.insurancedao.entities.ReceiptEntity;
import com.bbva.pisd.lib.r226.transfor.list.ContractTransformList;
import com.bbva.pisd.lib.r226.transfor.list.ReceiptTransformList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ContractTransformBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContractTransformBean.class);

    public static ContractEntity mapTransformContractEntityAndReceiptEntity(Map<String,Object> contract,List<Map<String,Object>>receipts){

        return ContractTransformBean.mapTransformContractEntity(contract)
                .withReceipts(ReceiptTransformList.transformListMapToListReceiptEntity(receipts))
                .build();
    }

    public static ContractEntity.ContractBuilder mapTransformContractEntity(Map<String,Object> map){
        LOGGER.info("[***] ContractTransformBean mapTransformContractEntity paramters - {} ", map);
        return ContractEntity.ContractBuilder.an()
                .withInsuranceContractEntityId(Objects.toString(map.get(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_ENTITY_ID),""))
                .withInsuranceContractBranchId(Objects.toString(map.get(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_BRANCH_ID),""))
                .withInsrcContractIntAccountId(Objects.toString(map.get(PISDColumn.Contract.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID),""))
                .withContractFirstVerfnDigitId(Objects.toString(map.get(PISDColumn.Contract.FIELD_CONTRACT_FIRST_VERFN_DIGIT_ID),""))
                .withContractSecondVerfnDigitId(Objects.toString(map.get(PISDColumn.Contract.FIELD_CONTRACT_SECOND_VERFN_DIGIT_ID),""))
                .withPolicyQuotaInternalId(Objects.toString(map.get(PISDColumn.Contract.FIELD_POLICY_QUOTA_INTERNAL_ID),""))
                .withInsuranceProductId(Objects.toString(map.get(PISDColumn.Contract.FIELD_INSURANCE_PRODUCT_ID),""))
                .withInsuranceProductName(Objects.toString(map.get(PISDColumn.Contract.FIELD_INSURANCE_PRODUCT_NAME),""))
                .withInsuranceModalityType(Objects.toString(map.get(PISDColumn.Contract.FIELD_INSURANCE_MODALITY_TYPE),""))
                .withInsuranceCompanyId(Objects.toString(map.get(PISDColumn.Contract.FIELD_INSURANCE_COMPANY_ID),""))
                .withPolicyId(Objects.toString(map.get(PISDColumn.Contract.FIELD_POLICY_ID),""))
                .withInsuranceManagerId(Objects.toString(map.get(PISDColumn.Contract.FIELD_INSURANCE_MANAGER_ID),""))
                .withInsurancePromoterId(Objects.toString(map.get(PISDColumn.Contract.FIELD_INSURANCE_PROMOTER_ID),""))
                .withContractManagerBranchId(Objects.toString(map.get(PISDColumn.Contract.FIELD_CONTRACT_MANAGER_BRANCH_ID),""))
                .withContractInceptionDate(Objects.toString(map.get(PISDColumn.Contract.FIELD_CONTRACT_INCEPTION_DATE),""))
                .withInsuranceContractStartDate(Objects.toString(map.get(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_START_DATE),""))
                .withInsuranceContractEndDate(Objects.toString(map.get(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_END_DATE),""))
                .withInsrncValidityMonthsNumber(Objects.toString(map.get(PISDColumn.Contract.FIELD_INSRNC_VALIDITY_MONTHS_NUMBER),""))
                .withInsurancePolicyEndDate(Objects.toString(map.get(PISDColumn.Contract.FIELD_INSURANCE_POLICY_END_DATE),""))
                .withPolicyAnnulationDate(Objects.toString(map.get(PISDColumn.Contract.FIELD_POLICY_ANNULATION_DATE),""))
                .withAftrReprgPendingInstNumber(Objects.toString(map.get(PISDColumn.Contract.FIELD_AFTR_REPRG_PENDING_INST_NUMBER),""))
                .withContractRegisCstAgeNumber(Objects.toString(map.get(PISDColumn.Contract.FIELD_CONTRACT_REGIS_CST_AGE_NUMBER),""))
                .withCustomerId(Objects.toString(map.get(PISDColumn.Contract.FIELD_CUSTOMER_ID),""))
                .withDomicileContractId(Objects.toString(map.get(PISDColumn.Contract.FIELD_DOMICILE_CONTRACT_ID),""))
                .withCardIssuingMarkType(Objects.toString(map.get(PISDColumn.Contract.FIELD_CARD_ISSUING_MARK_TYPE),""))
                .withIssuedReceiptNumber(Objects.toString(map.get(PISDColumn.Contract.FIELD_ISSUED_RECEIPT_NUMBER),""))
                .withPaymentFrequencyId(Objects.toString(map.get(PISDColumn.Contract.FIELD_PAYMENT_FREQUENCY_ID),""))
                .withPremiumAmount(Objects.toString(map.get(PISDColumn.Contract.FIELD_PREMIUM_AMOUNT),""))
                .withNetPremPlcyOrigCurrAmount(Objects.toString(map.get(PISDColumn.Contract.FIELD_NET_PREM_PLCY_ORIG_CURR_AMOUNT),""))
                .withSettlementVarPremiumAmount(Objects.toString(map.get(PISDColumn.Contract.FIELD_SETTLE_PENDING_PREMIUM_AMOUNT),""))
                .withPolicyFeeAmount(Objects.toString(map.get(PISDColumn.Contract.FIELD_POLICY_FEE_AMOUNT),""))
                .withCurrencyId(Objects.toString(map.get(PISDColumn.Contract.FIELD_CURRENCY_ID),""))
                .withLastInstallmentDate(Objects.toString(map.get(PISDColumn.Contract.FIELD_LAST_INSTALLMENT_DATE),""))
                .withInstallmentPeriodFinalDate(Objects.toString(map.get(PISDColumn.Contract.FIELD_INSTALLMENT_PERIOD_FINAL_DATE),""))
                .withInsuredAmount(Objects.toString(map.get(PISDColumn.Contract.FIELD_INSURED_AMOUNT),""))
                .withBeneficiaryType(Objects.toString(map.get(PISDColumn.Contract.FIELD_BENEFICIARY_TYPE),""))
                .withRenewalNumber(Objects.toString(map.get(PISDColumn.Contract.FIELD_RENEWAL_NUMBER),""))
                .withNextRenewalStartDate(Objects.toString(map.get(PISDColumn.Contract.FIELD_NEXT_RENEWAL_START_DATE),""))
                .withCtrctDisputeStatusType(Objects.toString(map.get(PISDColumn.Contract.FIELD_CTRCT_DISPUTE_STATUS_TYPE),""))
                .withContractPreviousBranchId(Objects.toString(map.get(PISDColumn.Contract.FIELD_CONTRACT_PREVIOUS_BRANCH_ID),""))
                .withPeriodNextPaymentDate(Objects.toString(map.get(PISDColumn.Contract.FIELD_PERIOD_NEXT_PAYMENT_DATE),""))
                .withContPreviousSituationType(Objects.toString(map.get(PISDColumn.Contract.FIELD_CONT_PREVIOUS_SITUATION_TYPE),""))
                .withEndorsementPolicyIndType(Objects.toString(map.get(PISDColumn.Contract.FIELD_ENDORSEMENT_POLICY_IND_TYPE),""))
                .withInsrncCoContractStatusType(Objects.toString(map.get(PISDColumn.Contract.FIELD_INSRNC_CO_CONTRACT_STATUS_TYPE),""))
                .withContractStatusId(Objects.toString(map.get(PISDColumn.Contract.FIELD_CONTRACT_STATUS_ID),""))
                .withCreationUserId(Objects.toString(map.get(PISDColumn.Contract.FIELD_CREATION_USER_ID),""))
                .withCreationDate(Objects.toString(map.get(PISDColumn.Contract.FIELD_CREATION_DATE),""))
                .withUserAuditId(Objects.toString(map.get(PISDColumn.Contract.FIELD_USER_AUDIT_ID),""))
                .withAuditDate(Objects.toString(map.get(PISDColumn.Contract.FIELD_AUDIT_DATE),""))
                .withInsurPendingDebtIndType(Objects.toString(map.get(PISDColumn.Contract.FIELD_INSUR_PENDING_DEBT_IND_TYPE),""))
                .withTotalDebtAmount(Objects.toString(map.get(PISDColumn.Contract.FIELD_TOTAL_DEBT_AMOUNT),""))
                .withPrevPendBillRcptsNumber(Objects.toString(map.get(PISDColumn.Contract.FIELD_PREV_PEND_BILL_RCPTS_NUMBER),""))
                .withSettlementVarPremiumAmount(Objects.toString(map.get(PISDColumn.Contract.FIELD_SETTLEMENT_VAR_PREMIUM_AMOUNT),""))
                .withSettlementFixPremiumAmount(Objects.toString(map.get(PISDColumn.Contract.FIELD_SETTLEMENT_FIX_PREMIUM_AMOUNT),""))
                .withInsuranceCompanyProductId(Objects.toString(map.get(PISDColumn.Contract.FIELD_INSURANCE_COMPANY_PRODUCT_ID),""))
                .withAutomaticDebitIndicatorType(Objects.toString(map.get(PISDColumn.Contract.FIELD_AUTOMATIC_DEBIT_INDICATOR_TYPE),""))
                .withBiometryTransactionId(Objects.toString(map.get(PISDColumn.Contract.FIELD_BIOMETRY_TRANSACTION_ID),""))
                .withTelemarketingTransactionId(Objects.toString(map.get(PISDColumn.Contract.FIELD_TELEMARKETING_TRANSACTION_ID),""))
                .withDataTreatmentIndType(Objects.toString(map.get(PISDColumn.Contract.FIELD_DATA_TREATMENT_IND_TYPE),""))
                .withContractAcceptanceIndType(Objects.toString(map.get(PISDColumn.Contract.FIELD_CONTRACT_ACCEPTANCE_IND_TYPE),""))
                .withContractNonCnclIndType(Objects.toString(map.get(PISDColumn.Contract.FIELD_CONTRACT_NON_CNCL_IND_TYPE),""))
                .withSaleChannelId(Objects.toString(map.get(PISDColumn.Contract.FIELD_SALE_CHANNEL_ID),""))
                .withContractRenewalStatusType(Objects.toString(map.get(PISDColumn.Contract.FIELD_CONTRACT_RENEWAL_STATUS_TYPE),""))
                .withContractRenewalSendingDate(Objects.toString(map.get(PISDColumn.Contract.FIELD_CONTRACT_RENEWAL_SENDING_DATE),""))
                .withContractRenewalReceiptDate(Objects.toString(map.get(PISDColumn.Contract.FIELD_CONTRACT_RENEWAL_RECEIPT_DATE),""))
                .withInsrncCompanyRenwlPrpslId(Objects.toString(map.get(PISDColumn.Contract.FIELD_INSRNC_COMPANY_RENWL_PRPSL_ID),""))
                .withInsrcCompanyRenwlRspseDesc(Objects.toString(map.get(PISDColumn.Contract.FIELD_INSRC_COMPANY_RENWL_RSPSE_DESC),""))
                .withPolicyDiscountCouponId(Objects.toString(map.get(PISDColumn.Contract.FIELD_POLICY_DISCOUNT_COUPON_ID),""))
                .withNonRenewedCtrctReasontype(Objects.toString(map.get(PISDColumn.Contract.FIELD_NON_RENEWED_CTRCT_REASON_TYPE),""))
                .withNonRenwCtrctSubReasonType(Objects.toString(map.get(PISDColumn.Contract.FIELD_NON_RENW_CTRCT_SUB_REASON_TYPE),""))
                .withOriginalPaymentSubChannelId(Objects.toString(map.get(PISDColumn.Contract.FIELD_ORIGINAL_PAYMENT_SUBCHANNEL_ID),""))
                .withProcessedRegisterType(Objects.toString(map.get(PISDColumn.Contract.FIELD_PROCESSED_REGISTER_TYPE),""))
                .withCardTokenizedId(Objects.toString(map.get(PISDColumn.Contract.FIELD_OPENPAY_CUSTOMER_CARD_TOKENIZED_ID),""))
                .withOpenpayCommerceTransDate(Objects.toString(map.get(PISDColumn.Contract.FIELD_OPENPAY_COMMERCE_TRANS_DATE),""))
                .withPaymentMeansType(Objects.toString(map.get(PISDColumn.Contract.FIELD_PAYMENT_MEANS_TYPE),""));
    }
}
