package com.bbva.pisd.lib.r226.transfor.bean;

import com.bbva.pisd.dto.insurancedao.constants.PISDColumn;
import com.bbva.pisd.dto.insurancedao.entities.ContractEntity;
import com.bbva.pisd.lib.r226.transfor.list.ReceiptTransformList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class ContractTransformBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContractTransformBean.class);

    public static ContractEntity.ContractBuilder mapTransformContractEntityAndReceiptEntity(Map<String,Object> contract, List<Map<String,Object>> receipt){
        return ContractTransformBean.mapTransformContractEntity(contract)
                .withReceipts(ReceiptTransformList.mapListTransformListReceiptEntity(receipt));
    }


    public static ContractEntity.ContractBuilder mapTransformContractEntity(Map<String,Object> map){
        LOGGER.info("[***] ContractTransformBean mapTransformContractEntity paramters - {} ", map);
        return ContractEntity.ContractBuilder.an()
                .withInsuranceContractEntityId((String) map.get(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_ENTITY_ID))
                .withInsuranceContractBranchId((String) map.get(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_BRANCH_ID))
                .withInsrcContractIntAccountId((String) map.get(PISDColumn.Contract.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID))
                .withContractFirstVerfnDigitId((String) map.get(PISDColumn.Contract.FIELD_CONTRACT_FIRST_VERFN_DIGIT_ID))
                .withContractSecondVerfnDigitId((String) map.get(PISDColumn.Contract.FIELD_CONTRACT_SECOND_VERFN_DIGIT_ID))
                .withPolicyQuotaInternalId((String) map.get(PISDColumn.Contract.FIELD_POLICY_QUOTA_INTERNAL_ID))
                .withInsuranceProductId((String) map.get(PISDColumn.Contract.FIELD_INSURANCE_PRODUCT_ID))
                .withInsuranceModalityType((String) map.get(PISDColumn.Contract.FIELD_INSURANCE_MODALITY_TYPE))
                .withInsuranceCompanyId((String) map.get(PISDColumn.Contract.FIELD_INSURANCE_COMPANY_ID))
                .withPolicyId((String) map.get(PISDColumn.Contract.FIELD_POLICY_ID))
                .withInsuranceManagerId((String) map.get(PISDColumn.Contract.FIELD_INSURANCE_MANAGER_ID))
                .withInsurancePromoterId((String) map.get(PISDColumn.Contract.FIELD_INSURANCE_PROMOTER_ID))
                .withContractManagerBranchId((String) map.get(PISDColumn.Contract.FIELD_CONTRACT_MANAGER_BRANCH_ID))
                .withContractInceptionDate((map.get(PISDColumn.Contract.FIELD_CONTRACT_INCEPTION_DATE).toString()))
                .withInsuranceContractStartDate((map.get(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_START_DATE).toString()))
                .withInsuranceContractEndDate((map.get(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_END_DATE).toString()))
                .withInsrncValidityMonthsNumber((String) map.get(PISDColumn.Contract.FIELD_INSRNC_VALIDITY_MONTHS_NUMBER))
                .withInsurancePolicyEndDate((map.get(PISDColumn.Contract.FIELD_INSURANCE_POLICY_END_DATE).toString()))
                .withPolicyAnnulationDate((map.get(PISDColumn.Contract.FIELD_POLICY_ANNULATION_DATE).toString()))
                .withAftrReprgPendingInstNumber((String) map.get(PISDColumn.Contract.FIELD_AFTR_REPRG_PENDING_INST_NUMBER))
                .withContractRegisCstAgeNumber((String) map.get(PISDColumn.Contract.FIELD_CONTRACT_REGIS_CST_AGE_NUMBER))
                .withCustomerId((String) map.get(PISDColumn.Contract.FIELD_CUSTOMER_ID))
                .withDomicileContractId((String) map.get(PISDColumn.Contract.FIELD_DOMICILE_CONTRACT_ID))
                .withCardIssuingMarkType((String) map.get(PISDColumn.Contract.FIELD_CARD_ISSUING_MARK_TYPE))
                .withIssuedReceiptNumber((String) map.get(PISDColumn.Contract.FIELD_ISSUED_RECEIPT_NUMBER))
                .withPaymentFrequencyId((String) map.get(PISDColumn.Contract.FIELD_PAYMENT_FREQUENCY_ID))
                .withPremiumAmount((String) map.get(PISDColumn.Contract.FIELD_PREMIUM_AMOUNT))
                .withNetPremPlcyOrigCurrAmount((String) map.get(PISDColumn.Contract.FIELD_NET_PREM_PLCY_ORIG_CURR_AMOUNT))
                .withSettlementVarPremiumAmount((String) map.get(PISDColumn.Contract.FIELD_SETTLE_PENDING_PREMIUM_AMOUNT))
                .withPolicyFeeAmount((String) map.get(PISDColumn.Contract.FIELD_POLICY_FEE_AMOUNT))
                .withCurrencyId((String) map.get(PISDColumn.Contract.FIELD_CURRENCY_ID))
                .withLastInstallmentDate((map.get(PISDColumn.Contract.FIELD_LAST_INSTALLMENT_DATE).toString()))
                .withInstallmentPeriodFinalDate((map.get(PISDColumn.Contract.FIELD_INSTALLMENT_PERIOD_FINAL_DATE).toString()))
                .withInsuredAmount((String) map.get(PISDColumn.Contract.FIELD_INSURED_AMOUNT))
                .withBeneficiaryType((String) map.get(PISDColumn.Contract.FIELD_BENEFICIARY_TYPE))
                .withRenewalNumber((String) map.get(PISDColumn.Contract.FIELD_RENEWAL_NUMBER))
                .withNextRenewalStartDate((map.get(PISDColumn.Contract.FIELD_NEXT_RENEWAL_START_DATE).toString()))
                .withCtrctDisputeStatusType((String) map.get(PISDColumn.Contract.FIELD_CTRCT_DISPUTE_STATUS_TYPE))
                .withContractPreviousBranchId((String) map.get(PISDColumn.Contract.FIELD_CONTRACT_PREVIOUS_BRANCH_ID))
                .withPeriodNextPaymentDate((map.get(PISDColumn.Contract.FIELD_PERIOD_NEXT_PAYMENT_DATE).toString()))
                .withContPreviousSituationType((String) map.get(PISDColumn.Contract.FIELD_CONT_PREVIOUS_SITUATION_TYPE))
                .withEndorsementPolicyIndType((String) map.get(PISDColumn.Contract.FIELD_ENDORSEMENT_POLICY_IND_TYPE))
                .withInsrncCoContractStatusType((String) map.get(PISDColumn.Contract.FIELD_INSRNC_CO_CONTRACT_STATUS_TYPE))
                .withContractStatusId((String) map.get(PISDColumn.Contract.FIELD_CONTRACT_STATUS_ID))
                .withCreationUserId((String) map.get(PISDColumn.Contract.FIELD_CREATION_USER_ID))
                .withCreationDate((map.get(PISDColumn.Contract.FIELD_CREATION_DATE).toString()))
                .withUserAuditId((String) map.get(PISDColumn.Contract.FIELD_USER_AUDIT_ID))
                .withAuditDate((map.get(PISDColumn.Contract.FIELD_AUDIT_DATE).toString()))
                .withInsurPendingDebtIndType((String) map.get(PISDColumn.Contract.FIELD_INSUR_PENDING_DEBT_IND_TYPE))
                .withTotalDebtAmount((String) map.get(PISDColumn.Contract.FIELD_TOTAL_DEBT_AMOUNT))
                .withPrevPendBillRcptsNumber((String) map.get(PISDColumn.Contract.FIELD_PREV_PEND_BILL_RCPTS_NUMBER))
                .withSettlementVarPremiumAmount((String) map.get(PISDColumn.Contract.FIELD_SETTLEMENT_VAR_PREMIUM_AMOUNT))
                .withSettlementFixPremiumAmount((String) map.get(PISDColumn.Contract.FIELD_SETTLEMENT_FIX_PREMIUM_AMOUNT))
                .withInsuranceCompanyProductId((String) map.get(PISDColumn.Contract.FIELD_INSURANCE_COMPANY_PRODUCT_ID))
                .withAutomaticDebitIndicatorType((String) map.get(PISDColumn.Contract.FIELD_AUTOMATIC_DEBIT_INDICATOR_TYPE))
                .withBiometryTransactionId((String) map.get(PISDColumn.Contract.FIELD_BIOMETRY_TRANSACTION_ID))
                .withTelemarketingTransactionId((String) map.get(PISDColumn.Contract.FIELD_TELEMARKETING_TRANSACTION_ID))
                .withDataTreatmentIndType((String) map.get(PISDColumn.Contract.FIELD_DATA_TREATMENT_IND_TYPE))
                .withContractAcceptanceIndType((String) map.get(PISDColumn.Contract.FIELD_CONTRACT_ACCEPTANCE_IND_TYPE))
                .withContractNonCnclIndType((String) map.get(PISDColumn.Contract.FIELD_CONTRACT_NON_CNCL_IND_TYPE))
                .withSaleChannelId((String) map.get(PISDColumn.Contract.FIELD_SALE_CHANNEL_ID))
                .withContractRenewalStatusType((String) map.get(PISDColumn.Contract.FIELD_CONTRACT_RENEWAL_STATUS_TYPE))
                .withContractRenewalSendingDate((map.get(PISDColumn.Contract.FIELD_CONTRACT_RENEWAL_SENDING_DATE).toString()))
                .withContractRenewalReceiptDate((map.get(PISDColumn.Contract.FIELD_CONTRACT_RENEWAL_RECEIPT_DATE).toString()))
                .withInsrncCompanyRenwlPrpslId((String) map.get(PISDColumn.Contract.FIELD_INSRNC_COMPANY_RENWL_PRPSL_ID))
                .withInsrcCompanyRenwlRspseDesc((String) map.get(PISDColumn.Contract.FIELD_INSRC_COMPANY_RENWL_RSPSE_DESC))
                .withPolicyDiscountCouponId((String) map.get(PISDColumn.Contract.FIELD_POLICY_DISCOUNT_COUPON_ID))
                .withNonRenewedCtrctReasontype((String) map.get(PISDColumn.Contract.FIELD_NON_RENEWED_CTRCT_REASON_TYPE))
                .withNonRenwCtrctSubReasonType((String) map.get(PISDColumn.Contract.FIELD_NON_RENW_CTRCT_SUB_REASON_TYPE))
                .withOriginalPaymentSubChannelId((String) map.get(PISDColumn.Contract.FIELD_ORIGINAL_PAYMENT_SUBCHANNEL_ID))
                .withProcessedRegisterType((String) map.get(PISDColumn.Contract.FIELD_PROCESSED_REGISTER_TYPE))
                .withTokenSupplierId((String) map.get(PISDColumn.Contract.FIELD_TOKEN_SUPPLIER_ID))
                .withOpenpayCommerceTransDate((map.get(PISDColumn.Contract.FIELD_OPENPAY_COMMERCE_TRANS_DATE).toString()))
                .withPaymentMeansType((String) map.get(PISDColumn.Contract.FIELD_PAYMENT_MEANS_TYPE));
    }
}
