package com.bbva.pisd.lib.r226.transfor.map;

import com.bbva.pisd.dto.contract.search.CertifyBankCriteria;
import com.bbva.pisd.dto.insurancedao.constants.PISDColumn;
import com.bbva.pisd.dto.insurancedao.entities.ContractEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ContractTransformMap {


    private static final Logger LOGGER = LoggerFactory.getLogger(ContractTransformMap.class);

    public static Map<String, Object> certifyBankCriteriaTransformMap(CertifyBankCriteria certifyBankCriteria){
        LOGGER.info("[***] ContractTransformMap certifyBankCriteriaTransformMap receiptSearchCriteriaDTO - {} ",certifyBankCriteria);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_ENTITY_ID, certifyBankCriteria.getInsuranceContractEntityId() );
        parameters.put(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_BRANCH_ID, certifyBankCriteria.getInsuranceContractBranchId() );
        parameters.put(PISDColumn.Contract.FIELD_CONTRACT_FIRST_VERFN_DIGIT_ID, certifyBankCriteria.getContractFirstVerfnDigitId());
        parameters.put(PISDColumn.Contract.FIELD_CONTRACT_SECOND_VERFN_DIGIT_ID, certifyBankCriteria.getContractSecondVerfnDigitId());
        parameters.put(PISDColumn.Contract.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID, certifyBankCriteria.getInsrcContractIntAccountId() );
        LOGGER.info("[***] ContractTransformMap certifyBankCriteriaTransformMap parameters - {} ",parameters);
        return parameters;
    }


    public static Map<String, Object> objTransformContractMap(ContractEntity contract){
        LOGGER.info("[***] ContractTransformBean objTransformContractMap contract - {} ", contract);
        Map<String,Object>contractMap= new HashMap<>();
        contractMap.put(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_ENTITY_ID, contract.getInsuranceContractEntityId());
        contractMap.put(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_BRANCH_ID, contract.getInsuranceContractBranchId());
        contractMap.put(PISDColumn.Contract.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID, contract.getInsrcContractIntAccountId());
        contractMap.put(PISDColumn.Contract.FIELD_CONTRACT_FIRST_VERFN_DIGIT_ID, contract.getContractFirstVerfnDigitId());
        contractMap.put(PISDColumn.Contract.FIELD_CONTRACT_SECOND_VERFN_DIGIT_ID, contract.getContractSecondVerfnDigitId());
        contractMap.put(PISDColumn.Contract.FIELD_POLICY_QUOTA_INTERNAL_ID, contract.getPolicyQuotaInternalId());
        contractMap.put(PISDColumn.Contract.FIELD_INSURANCE_PRODUCT_ID, contract.getInsuranceProductId());
        contractMap.put(PISDColumn.Contract.FIELD_INSURANCE_MODALITY_TYPE, contract.getInsuranceModalityType());
        contractMap.put(PISDColumn.Contract.FIELD_INSURANCE_COMPANY_ID, contract.getInsuranceCompanyId());
        contractMap.put(PISDColumn.Contract.FIELD_POLICY_ID, contract.getPolicyId());
        contractMap.put(PISDColumn.Contract.FIELD_INSURANCE_MANAGER_ID, contract.getInsuranceManagerId());
        contractMap.put(PISDColumn.Contract.FIELD_INSURANCE_PROMOTER_ID, contract.getInsurancePromoterId());
        contractMap.put(PISDColumn.Contract.FIELD_CONTRACT_MANAGER_BRANCH_ID, contract.getContractManagerBranchId());
        contractMap.put(PISDColumn.Contract.FIELD_CONTRACT_INCEPTION_DATE, contract.getContractInceptionDate());
        contractMap.put(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_START_DATE, contract.getInsuranceContractStartDate());
        contractMap.put(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_END_DATE, contract.getInsuranceContractEndDate());
        contractMap.put(PISDColumn.Contract.FIELD_INSRNC_VALIDITY_MONTHS_NUMBER, contract.getInsrncValidityMonthsNumber());
        contractMap.put(PISDColumn.Contract.FIELD_INSURANCE_POLICY_END_DATE, contract.getInsurancePolicyEndDate());
        contractMap.put(PISDColumn.Contract.FIELD_POLICY_ANNULATION_DATE, contract.getPolicyAnnulationDate());
        contractMap.put(PISDColumn.Contract.FIELD_AFTR_REPRG_PENDING_INST_NUMBER, contract.getAftrReprgPendingInstNumber());
        contractMap.put(PISDColumn.Contract.FIELD_CONTRACT_REGIS_CST_AGE_NUMBER, contract.getContractRegisCstAgeNumber());
        contractMap.put(PISDColumn.Contract.FIELD_CUSTOMER_ID,contract.getCustomerId());
        contractMap.put(PISDColumn.Contract.FIELD_DOMICILE_CONTRACT_ID,contract.getDomicileContractId());
        contractMap.put(PISDColumn.Contract.FIELD_CARD_ISSUING_MARK_TYPE,contract.getCardIssuingMarkType());
        contractMap.put(PISDColumn.Contract.FIELD_ISSUED_RECEIPT_NUMBER,contract.getIssuedReceiptNumber());
        contractMap.put(PISDColumn.Contract.FIELD_PAYMENT_FREQUENCY_ID, contract.getPaymentFrequencyId());
        contractMap.put(PISDColumn.Contract.FIELD_PREMIUM_AMOUNT, contract.getPremiumAmount());
        contractMap.put(PISDColumn.Contract.FIELD_NET_PREM_PLCY_ORIG_CURR_AMOUNT,contract.getNetPremPlcyOrigCurrAmount());
        contractMap.put(PISDColumn.Contract.FIELD_SETTLE_PENDING_PREMIUM_AMOUNT,contract.getSettlePendingPremiumAmount());
        contractMap.put(PISDColumn.Contract.FIELD_POLICY_FEE_AMOUNT,contract.getPolicyFeeAmount());
        contractMap.put(PISDColumn.Contract.FIELD_CURRENCY_ID,contract.getCurrencyId());
        contractMap.put(PISDColumn.Contract.FIELD_LAST_INSTALLMENT_DATE, contract.getLastInstallmentDate());
        contractMap.put(PISDColumn.Contract.FIELD_INSTALLMENT_PERIOD_FINAL_DATE, contract.getInstallmentPeriodFinalDate());
        contractMap.put(PISDColumn.Contract.FIELD_INSURED_AMOUNT, contract.getInsuredAmount());
        contractMap.put(PISDColumn.Contract.FIELD_BENEFICIARY_TYPE, contract.getBeneficiaryType());
        contractMap.put(PISDColumn.Contract.FIELD_RENEWAL_NUMBER, contract.getRenewalNumber());
        contractMap.put(PISDColumn.Contract.FIELD_NEXT_RENEWAL_START_DATE, contract.getNextRenewalStartDate());
        contractMap.put(PISDColumn.Contract.FIELD_CTRCT_DISPUTE_STATUS_TYPE, contract.getCtrctDisputeStatusType());
        contractMap.put(PISDColumn.Contract.FIELD_CONTRACT_PREVIOUS_BRANCH_ID, contract.getContractPreviousBranchId());
        contractMap.put(PISDColumn.Contract.FIELD_PERIOD_NEXT_PAYMENT_DATE, contract.getPeriodNextPaymentDate());
        contractMap.put(PISDColumn.Contract.FIELD_CONT_PREVIOUS_SITUATION_TYPE,contract.getContPreviousSituationType());
        contractMap.put(PISDColumn.Contract.FIELD_ENDORSEMENT_POLICY_IND_TYPE, contract.getEndorsementPolicyIndType());
        contractMap.put(PISDColumn.Contract.FIELD_INSRNC_CO_CONTRACT_STATUS_TYPE, contract.getInsrncCoContractStatusType());
        contractMap.put(PISDColumn.Contract.FIELD_CONTRACT_STATUS_ID,contract.getContractStatusId());
        contractMap.put(PISDColumn.Contract.FIELD_CREATION_USER_ID,contract.getCreationUserId());
        contractMap.put(PISDColumn.Contract.FIELD_CREATION_DATE, contract.getCreationDate());
        contractMap.put(PISDColumn.Contract.FIELD_USER_AUDIT_ID, contract.getUserAuditId());
        contractMap.put(PISDColumn.Contract.FIELD_AUDIT_DATE, contract.getAuditDate());
        contractMap.put(PISDColumn.Contract.FIELD_INSUR_PENDING_DEBT_IND_TYPE, contract.getInsurPendingDebtIndType());
        contractMap.put(PISDColumn.Contract.FIELD_TOTAL_DEBT_AMOUNT, contract.getTotalDebtAmount());
        contractMap.put(PISDColumn.Contract.FIELD_PREV_PEND_BILL_RCPTS_NUMBER, contract.getPrevPendBillRcptsNumber());
        contractMap.put(PISDColumn.Contract.FIELD_SETTLEMENT_VAR_PREMIUM_AMOUNT, contract.getSettlementVarPremiumAmount());
        contractMap.put(PISDColumn.Contract.FIELD_SETTLEMENT_FIX_PREMIUM_AMOUNT, contract.getSettlementFixPremiumAmount());
        contractMap.put(PISDColumn.Contract.FIELD_INSURANCE_COMPANY_PRODUCT_ID, contract.getInsuranceCompanyProductId());
        contractMap.put(PISDColumn.Contract.FIELD_AUTOMATIC_DEBIT_INDICATOR_TYPE, contract.getAutomaticDebitIndicatorType());
        contractMap.put(PISDColumn.Contract.FIELD_BIOMETRY_TRANSACTION_ID, contract.getBiometryTransactionId());
        contractMap.put(PISDColumn.Contract.FIELD_TELEMARKETING_TRANSACTION_ID, contract.getTelemarketingTransactionId());
        contractMap.put(PISDColumn.Contract.FIELD_DATA_TREATMENT_IND_TYPE, contract.getDataTreatmentIndType());
        contractMap.put(PISDColumn.Contract.FIELD_CONTRACT_ACCEPTANCE_IND_TYPE, contract.getContractAcceptanceIndType());
        contractMap.put(PISDColumn.Contract.FIELD_CONTRACT_NON_CNCL_IND_TYPE, contract.getContractNonCnclIndType());
        contractMap.put(PISDColumn.Contract.FIELD_SALE_CHANNEL_ID, contract.getSaleChannelId());
        contractMap.put(PISDColumn.Contract.FIELD_CONTRACT_RENEWAL_STATUS_TYPE, contract.getContractRenewalStatusType());
        contractMap.put(PISDColumn.Contract.FIELD_CONTRACT_RENEWAL_SENDING_DATE, contract.getContractRenewalSendingDate());
        contractMap.put(PISDColumn.Contract.FIELD_CONTRACT_RENEWAL_RECEIPT_DATE, contract.getContractRenewalReceiptDate());
        contractMap.put(PISDColumn.Contract.FIELD_INSRNC_COMPANY_RENWL_PRPSL_ID, contract.getInsrncCompanyRenwlPrpslId());
        contractMap.put(PISDColumn.Contract.FIELD_INSRC_COMPANY_RENWL_RSPSE_DESC, contract.getInsrcCompanyRenwlRspseDesc());
        contractMap.put(PISDColumn.Contract.FIELD_POLICY_DISCOUNT_COUPON_ID, contract.getPolicyDiscountCouponId());
        contractMap.put(PISDColumn.Contract.FIELD_NON_RENEWED_CTRCT_REASON_TYPE, contract.getNonRenewedCtrctReasontype());
        contractMap.put(PISDColumn.Contract.FIELD_NON_RENW_CTRCT_SUB_REASON_TYPE, contract.getNonRenwCtrctSubReasonType());
        contractMap.put(PISDColumn.Contract.FIELD_ORIGINAL_PAYMENT_SUBCHANNEL_ID, contract.getOriginalPaymentSubChannelId());
        contractMap.put(PISDColumn.Contract.FIELD_PROCESSED_REGISTER_TYPE, contract.getProcessedRegisterType());
        contractMap.put(PISDColumn.Contract.FIELD_OPENPAY_CUSTOMER_CARD_TOKENIZED_ID, contract.getCardTokenizedId());
        contractMap.put(PISDColumn.Contract.FIELD_OPENPAY_COMMERCE_TRANS_DATE, contract.getOpenpayCommerceTransDate());
        contractMap.put(PISDColumn.Contract.FIELD_PAYMENT_MEANS_TYPE, contract.getPaymentMeansType());
        LOGGER.info("[***] ContractTransformBean objTransformContractMap contractMap - {} ", contractMap);
        return contractMap;
    }
}
