package com.bbva.pisd.lib.r226.dao;

import com.bbva.pisd.dto.insurancedao.constants.PISDColumn;
import com.bbva.pisd.dto.insurancedao.entities.PaymentPeriodEntity;
import com.bbva.pisd.dto.insurancedao.operation.Operation;
import com.bbva.pisd.dto.insurancedao.operation.OperationConstants;
import com.bbva.pisd.lib.r226.interfaces.PaymentPeriodDAO;
import com.bbva.pisd.lib.r226.pattern.factory.interfaces.BaseDAO;
import com.bbva.pisd.lib.r226.transfor.bean.PaymentPeriodBean;
import com.bbva.pisd.lib.r226.util.FunctionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Map;

public class PaymentPeriodImpl implements PaymentPeriodDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentPeriodImpl.class);

    private final BaseDAO baseDAO;

    private static final String PISD_SELECT_PAYMENT_PERIOD = "PISD.SELECT_PAYMENT_PERIOD";


    public PaymentPeriodImpl(BaseDAO baseDAO) {
        this.baseDAO = baseDAO;
    }

    @Override
    public PaymentPeriodEntity findPaymentPeriodByType(String paymentFrequencyType) {
        LOGGER.info("[***] PaymentPeriodImpl findPaymentPeriodByType() - paymentFrequencyType -> {} ", paymentFrequencyType);

        PaymentPeriodEntity paymentPeriod = null;

        Map<String, Object> parameters = Collections.singletonMap(
                PISDColumn.PaymentPeriod.FIELD_POLICY_PAYMENT_FREQUENCY_TYPE,paymentFrequencyType);

        if(FunctionUtils.parametersIsValid(parameters, PISDColumn.PaymentPeriod.FIELD_POLICY_PAYMENT_FREQUENCY_TYPE)){
            Operation operation = Operation.Builder.an()
                    .withTypeOperation(OperationConstants.Operation.SELECT)
                    .withNameProp(PISD_SELECT_PAYMENT_PERIOD)
                    .withIsForListQuery(false)
                    .withParams(parameters).build();

            Map<String, Object> map = (Map<String, Object>) this.baseDAO.executeQuery(operation);

            if(!CollectionUtils.isEmpty(map)){
                paymentPeriod = PaymentPeriodBean.mapTransformPaymentPeriodEntity(map);
            }
        }

        return paymentPeriod;
    }

}
