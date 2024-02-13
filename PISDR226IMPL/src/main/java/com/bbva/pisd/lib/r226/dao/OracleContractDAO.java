package com.bbva.pisd.lib.r226.dao;


import com.bbva.pisd.dto.contract.constants.PISDQueryName;
import com.bbva.pisd.dto.contract.search.ReceiptSearchCriteria;
import com.bbva.pisd.dto.insurancedao.entities.ContractEntity;
import com.bbva.pisd.lib.r226.interfaces.ContractDAO;
import com.bbva.pisd.lib.r226.pattern.factory.impl.CommonJdbcFactory;
import com.bbva.pisd.lib.r226.pattern.factory.interfaces.BaseDAO;
import com.bbva.pisd.lib.r226.transfor.list.ContractTransformList;
import com.bbva.pisd.lib.r226.transfor.map.ReceiptTransformMap;
import com.bbva.pisd.lib.r226.util.FunctionUtils;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.bbva.pisd.dto.insurancedao.constants.PISDColumn.Contract.FIELD_CONTRACT_STATUS_ID;
import static com.bbva.pisd.dto.insurancedao.constants.PISDColumn.Contract.FIELD_PAYMENT_MEANS_TYPE;
import static com.bbva.pisd.dto.insurancedao.constants.PISDColumn.Receipt.FIELD_RECEIPT_STATUS_TYPE;

public class OracleContractDAO implements ContractDAO {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ContractDAO.class);

    private BaseDAO baseDAO;

    public OracleContractDAO(BaseDAO baseDAO) {
        this.baseDAO = baseDAO;
    }
    @Override
    public List<ContractEntity> findContractBySearchCriteria(ReceiptSearchCriteria searchCriteria) {
        LOGGER.info("[***] OracleContractDAO executeFindReceiptByChargeEntityExtern - {} ", searchCriteria);
        List<ContractEntity> listContract = null;
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> parameters = ReceiptTransformMap.ReceiptSearchCriteriaTransformMap(searchCriteria);
        if (this.baseDAO instanceof CommonJdbcFactory) {
            LOGGER.info("[***] OracleContractDAO findReceiptByChargeEntityExtern instanceof CommonJdbcFactory");
            if (FunctionUtils.parametersIsValid(parameters, FIELD_PAYMENT_MEANS_TYPE, FIELD_CONTRACT_STATUS_ID, FIELD_RECEIPT_STATUS_TYPE)) {
                LOGGER.info("[***] OracleContractDAO findReceiptByChargeEntityExtern Parameters Valid");
                result = this.baseDAO.executeQueryListPagination(parameters,PISDQueryName.SQL_SELECT_BILLED_RECEIPTS.getValue());
                listContract = ContractTransformList.transformListMapToListContractEntity(result);
                LOGGER.info("[***] OracleContractDAO executeFindReceiptByChargeEntityExtern ResultMapper - {}", listContract);
                return listContract;
            }
        }
        return null;
    }
}
