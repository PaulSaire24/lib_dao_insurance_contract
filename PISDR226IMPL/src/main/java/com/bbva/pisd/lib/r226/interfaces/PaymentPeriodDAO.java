package com.bbva.pisd.lib.r226.interfaces;

import com.bbva.pisd.dto.insurancedao.entities.PaymentPeriodEntity;

public interface PaymentPeriodDAO {

    PaymentPeriodEntity findPaymentPeriodByType(String paymentFrequencyType);
}
