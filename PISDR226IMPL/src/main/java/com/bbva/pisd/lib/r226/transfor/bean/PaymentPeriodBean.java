package com.bbva.pisd.lib.r226.transfor.bean;

import com.bbva.pisd.dto.insurancedao.constants.PISDColumn;
import com.bbva.pisd.dto.insurancedao.entities.PaymentPeriodEntity;
import com.bbva.pisd.lib.r226.util.FunctionUtils;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

public class PaymentPeriodBean {

    private PaymentPeriodBean(){}

    public static PaymentPeriodEntity mapTransformPaymentPeriodEntity(Map<String,Object> map){
        PaymentPeriodEntity paymentPeriodEntity = new PaymentPeriodEntity();

        paymentPeriodEntity.setPaymentFrequencyId(FunctionUtils.convertObjectToBigdecimal(
                map.get(PISDColumn.PaymentPeriod.FIELD_PAYMENT_FREQUENCY_ID)));
        paymentPeriodEntity.setPolicyPaymentFrequencyType(Objects.toString(
                map.get(PISDColumn.PaymentPeriod.FIELD_POLICY_PAYMENT_FREQUENCY_TYPE),""));
        paymentPeriodEntity.setPaymentFrequencyName(Objects.toString(
                map.get(PISDColumn.PaymentPeriod.FIELD_PAYMENT_FREQUENCY_NAME),""));
        paymentPeriodEntity.setRegistrySituationType(Objects.toString(
                map.get(PISDColumn.PaymentPeriod.FIELD_REGISTRY_SITUATION_TYPE),""));
        paymentPeriodEntity.setCreationUserId(Objects.toString(
                map.get(PISDColumn.Contract.FIELD_CREATION_USER_ID),""));
        paymentPeriodEntity.setCreationDate(Objects.toString(map.get(PISDColumn.Contract.FIELD_CREATION_DATE),""));
        paymentPeriodEntity.setUserAuditId(Objects.toString(map.get(PISDColumn.Contract.FIELD_USER_AUDIT_ID),""));
        paymentPeriodEntity.setAuditDate(Objects.toString(map.get(PISDColumn.Contract.FIELD_AUDIT_DATE),""));

        return paymentPeriodEntity;
    }

}
