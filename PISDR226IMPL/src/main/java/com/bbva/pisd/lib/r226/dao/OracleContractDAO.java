package com.bbva.pisd.lib.r226.dao;


import com.bbva.apx.exception.business.BusinessException;
import com.bbva.elara.library.AbstractLibrary;
import com.bbva.pisd.dto.contract.constants.PISDConstant;
import com.bbva.pisd.dto.contract.constants.PISDQueryName;
import com.bbva.pisd.dto.contract.search.CertifyBankCriteria;
import com.bbva.pisd.dto.contract.search.ReceiptSearchCriteria;
import com.bbva.pisd.dto.insurancedao.constants.PISDColumn;
import com.bbva.pisd.dto.insurancedao.entities.ContractEntity;
import com.bbva.pisd.dto.insurancedao.entities.ReceiptEntity;
import com.bbva.pisd.dto.insurancedao.operation.Operation;
import com.bbva.pisd.dto.insurancedao.operation.OperationConstants;
import com.bbva.pisd.lib.r226.interfaces.ContractDAO;
import com.bbva.pisd.lib.r226.pattern.factory.impl.CommonJdbcFactory;
import com.bbva.pisd.lib.r226.pattern.factory.interfaces.BaseDAO;
import com.bbva.pisd.lib.r226.transfor.bean.ContractTransformBean;
import com.bbva.pisd.lib.r226.transfor.list.ReceiptTransformList;
import com.bbva.pisd.lib.r226.transfor.map.ContractTransformMap;
import com.bbva.pisd.lib.r226.transfor.map.ReceiptTransformMap;
import com.bbva.pisd.lib.r226.util.FunctionUtils;
import com.bbva.pisd.lib.r226.util.JsonHelper;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.bbva.pisd.dto.insurancedao.constants.PISDColumn.Contract.FIELD_CONTRACT_STATUS_ID;
import static com.bbva.pisd.dto.insurancedao.constants.PISDColumn.Contract.FIELD_PAYMENT_MEANS_TYPE;
import static com.bbva.pisd.dto.insurancedao.constants.PISDColumn.Receipt.FIELD_RECEIPT_STATUS_TYPE;

public class OracleContractDAO extends AbstractLibrary implements ContractDAO {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ContractDAO.class);

    private BaseDAO baseDAO;

    public OracleContractDAO(BaseDAO baseDAO) {
        this.baseDAO = baseDAO;
    }


    @Override
    public ContractEntity findByCertifiedBank(CertifyBankCriteria certifyBankCriteria) {
        LOGGER.info("[***]  findByCertifiedBank :: Criteria {} ", JsonHelper.getInstance().toJsonString(certifyBankCriteria));
        ContractEntity contract = null;
        int size = 0;
        Map<String, Object> parameters = ContractTransformMap.certifyBankCriteriaTransformMap(certifyBankCriteria);
        if (FunctionUtils.parametersIsValid(parameters, PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_ENTITY_ID)) {
            if (this.baseDAO instanceof CommonJdbcFactory) {
                Operation operation = Operation.Builder.an()
                        .withQuery(PISDQueryName.SQL_SELECT_CONTRACT.getValue())
                        .withTypeOperation(OperationConstants.Operation.SELECT).withIsForListQuery(true)
                        .withParams(parameters).build();
                List<Map<String, Object>> maps = (List<Map<String, Object>>) this.baseDAO.executeQuery(operation);
                if (!CollectionUtils.isEmpty(maps)) {
                    contract = ContractTransformBean.mapTransformContractEntity(maps.get(PISDConstant.Numeros.CERO));
                    LOGGER.info("[OracleContractDAO]  findContractByCertificateBank - Contract  [ {} ] ", JsonHelper.getInstance().toJsonString(contract));
//                contract.setReceipts(maps.stream().map(r-> ReceiptBean.buildReceiptFromDatabase(r)).collect(Collectors.toList()));
                    size = maps.size();
                }
                LOGGER.info("[***]  findByCertifiedBank :: size {} - result {} ", size, contract);
            }
        }
        LOGGER.info("[***]  findByCertifiedBank ::  Invalid Parameters ");
//        throw new BusinessException(PISDSimulationDAOErrors.QUERY_EMPTY_RESULT.getAdviceCode(), false, "Paramtros");
        return contract;
    }

    @Override
    public boolean updateReceiptsPayment(List<ReceiptEntity> receipts) {
        return false;
    }

    @Override
    public List<ReceiptEntity> findReceiptByChargeEntityExtern(ReceiptSearchCriteria receiptSearchCriteria) {

        LOGGER.info("[***] OracleContractDAO executeFindReceiptByChargeEntityExtern - {} ", receiptSearchCriteria);
        List<ReceiptEntity> listReceipts = null;
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> parameters = ReceiptTransformMap.ReceiptSearchCriteriaTransformMap(receiptSearchCriteria);

        if (this.baseDAO instanceof CommonJdbcFactory) {
            if (FunctionUtils.parametersIsValid(parameters, FIELD_PAYMENT_MEANS_TYPE, FIELD_CONTRACT_STATUS_ID, FIELD_RECEIPT_STATUS_TYPE)) {

                Long countResult = executeQueryFindReceiptsCount(receiptSearchCriteria);

                if (countResult > PISDConstant.Pagination.PAGINATION) {

                    long countPaginates = countResult / PISDConstant.Pagination.PAGINATION;
                    long mod            = countResult % PISDConstant.Pagination.PAGINATION;
                    countPaginates = countPaginates + (mod > 0 ? 1 : 0);

                    for (int i = 0; i < countPaginates; i++) {
                        result.addAll(this.baseDAO.executeQueryListPagination(PISDQueryName.SQL_SELECT_RECEIPTS_CHARGE_THIRD.getValue(), parameters, i, PISDConstant.Pagination.PAGINATION));
                    }

                } else {
                    result = this.baseDAO.executeQueryList(PISDQueryName.SQL_SELECT_RECEIPTS_CHARGE_THIRD.getValue(), parameters);
                    LOGGER.info("[***] OracleContractDAO executeFindReceiptByChargeEntityExtern Result - {}", result);
                }
                listReceipts = ReceiptTransformList.mapListTransformListReceiptEntity(result);
                LOGGER.info("[***] OracleContractDAO executeFindReceiptByChargeEntityExtern ResultMapper - {}", listReceipts);
                return listReceipts;
            }
        }

        return null;
    }

    public Long executeQueryFindReceiptsCount(ReceiptSearchCriteria receiptSearchCriteria) {
        LOGGER.info("[***] OracleContractDAO executeQueryFindReceiptsCount receiptSearchCriteria - {}", receiptSearchCriteria);
        Long countResult = 0l;
        Map<String, Object> parameters = ReceiptTransformMap.ReceiptSearchCriteriaTransformMap(receiptSearchCriteria);

        Operation operation = Operation.Builder.an()
                .withQuery(PISDQueryName.SQL_SELECT_RECEIPTS_CHARGE_THIRD_COUNT.getValue())
                .withTypeOperation(OperationConstants.Operation.SELECT).withIsForListQuery(false)
                .withParams(parameters).build();

        Map<String, Object> map = (Map<String, Object>) this.baseDAO.executeQuery(operation);

        countResult = Long.valueOf(map.get(PISDConstant.Pagination.COLUMN_COUNT).toString());

        LOGGER.info("[***] OracleContractDAO executeQueryFindReceiptsCount countResult - {}", countResult);

        return countResult;

    }

}
