package com.bbva.pisd.lib.r226.dao;

import com.bbva.pisd.dto.contract.constants.PISDQueryName;
import com.bbva.pisd.dto.contract.search.ReceiptSearchCriteria;
import com.bbva.pisd.dto.insurancedao.constants.PISDColumn;
import com.bbva.pisd.dto.insurancedao.constants.PISDConstant;
import com.bbva.pisd.dto.insurancedao.entities.ContractEntity;
import com.bbva.pisd.dto.insurancedao.operation.Operation;
import com.bbva.pisd.dto.insurancedao.operation.OperationConstants;
import com.bbva.pisd.lib.r226.interfaces.ContractDAO;
import com.bbva.pisd.lib.r226.pattern.factory.impl.CommonJdbcFactory;
import com.bbva.pisd.lib.r226.pattern.factory.interfaces.BaseDAO;
import com.bbva.pisd.lib.r226.transfor.bean.ContractTransformBean;
import com.bbva.pisd.lib.r226.transfor.list.ContractTransformList;
import com.bbva.pisd.lib.r226.transfor.map.ContractTransformMap;
import com.bbva.pisd.lib.r226.transfor.map.ReceiptTransformMap;
import com.bbva.pisd.lib.r226.util.FunctionUtils;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.bbva.pisd.dto.insurancedao.constants.PISDColumn.Contract.FIELD_CONTRACT_STATUS_ID;
import static com.bbva.pisd.dto.insurancedao.constants.PISDColumn.Contract.FIELD_PAYMENT_MEANS_TYPE;
import static com.bbva.pisd.dto.insurancedao.constants.PISDColumn.Receipt.FIELD_RECEIPT_STATUS_TYPE;

public class ContractDAOImpl implements ContractDAO {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ContractDAOImpl.class);
    private static final String COUNT="COUNT";
    private static final String RESULT_NUMBER = "RESULT_NUMBER";
    private static final String PISD_SQL_UPDATE_BIOMETRIC="PISD.SQL_UPDATE.BIOMETRIC";
    private static final String PISD_SQL_SELECT_CONTRACT="PISD.SQL_SELECT_CONTRACT";
    private static final String PISD_SQL_SELECT_CONTRACT_BY_ID_AND_PRODUCT = "PISD.FIND_CONTRACT_REGISTERED";
    private static final String PISD_FIND_CONTRACT_BY_ID = "PISD.FIND_CONTRACT_BY_ID";
    private static final String PISD_VALIDATE_IF_QUOTATION_EXISTS_IN_CONTRACT =
            "PISD.VALIDATE_IF_QUOTATION_EXISTS_IN_CONTRACT";
    private static final String PISD_INSERT_INSURANCE_CONTRACT = "PISD.INSERT_INSURANCE_CONTRACT";


    private final BaseDAO baseDAO;

    public ContractDAOImpl(BaseDAO baseDAO) {
        this.baseDAO = baseDAO;
    }

    @Override
    public List<ContractEntity> findContractBySearchCriteria(ReceiptSearchCriteria searchCriteria) {
        LOGGER.info("[***] ContractDAOImpl executeFindReceiptByChargeEntityExtern - {} ", searchCriteria);
        List<ContractEntity> listContract = null;
        List<Map<String, Object>> result;
        Map<String, Object> parameters = ReceiptTransformMap.receiptSearchCriteriaTransformMap(searchCriteria);
        if (this.baseDAO instanceof CommonJdbcFactory) {
            LOGGER.info("[***] ContractDAOImpl findReceiptByChargeEntityExtern instanceof CommonJdbcFactory");
            if (FunctionUtils.parametersIsValid(parameters, FIELD_PAYMENT_MEANS_TYPE, FIELD_CONTRACT_STATUS_ID, FIELD_RECEIPT_STATUS_TYPE)) {
                LOGGER.info("[***] ContractDAOImpl findReceiptByChargeEntityExtern Parameters Valid");
                result = this.baseDAO.executeQueryListPagination(parameters,PISDQueryName.SQL_SELECT_BILLED_RECEIPTS.getValue());
                listContract = ContractTransformList.transformListMapToListContractEntity(result);
                LOGGER.info("[***] ContractDAOImpl executeFindReceiptByChargeEntityExtern ResultMapper - {}", listContract);
                return listContract;
            }
        }
        return null;
    }

    @Override
    public boolean updateBiometricId(String insuranceContractId, String biometricId, String usuario) {
        LOGGER.info("[***] ContractDAOImpl insuranceContractId - {} ", insuranceContractId);
        LOGGER.info("[***] ContractDAOImpl biometricId - {} ", biometricId);
        LOGGER.info("[***] ContractDAOImpl usuario - {} ", usuario);

        String contractEntityId = insuranceContractId.substring(0, 4);
        String contractBranchId = insuranceContractId.substring(4, 8);
        String contractIntAccount = insuranceContractId.substring(8, 20);
        Map<String, Object> contractParameters = ContractTransformMap.contractTransformMapContract(contractEntityId,contractBranchId,contractIntAccount,biometricId,usuario);

        int result;

        Operation operation = Operation.Builder.an()
                .withNameProp(PISD_SQL_UPDATE_BIOMETRIC)
                .withTypeOperation(OperationConstants.Operation.UPDATE)
                .withParams(contractParameters).build();

        result = (int) this.baseDAO.executeQuery(operation);

        LOGGER.info("[***] ContractDAOImpl updateCardDataInContract result - {} ", result);

        return result == PISDConstant.Numeros.UNO;


    }
    @Override
    public Boolean findByContract(String  biometricId) {
        LOGGER.info("[***] ContractDAOImpl biometricId - {} ", biometricId);
        boolean resp=false;
        Map<String, Object> parameters = ContractTransformMap.contractTransformMapone(biometricId);
        Operation operation = Operation.Builder.an()
                .withTypeOperation(OperationConstants.Operation.SELECT)
                .withNameProp(PISD_SQL_SELECT_CONTRACT)
                .withIsForListQuery(false)
                .withParams(parameters).build();
        LOGGER.info("operation jdbcUtils{}",operation);
        Map<String, Object> map = (Map<String, Object>) this.baseDAO.executeQuery(operation);
        int count=(int)map.get(COUNT);
        if(count==1){
            resp=true;
        }
        LOGGER.info("[***] ContractDAOImpl updateCardDataInContract result - {} ", resp);
        return resp;
    }

    @Override
    public ContractEntity findContractByIdAndProductId(String contractId, String productId) {
        LOGGER.info("[***] ContractDAOImpl findContractByIdAndProductId - {} ", contractId);
        LOGGER.info("[***] ContractDAOImpl findContractByIdAndProductId - {} ", productId);

        ContractEntity contractEntity = null;
        Map<String,Object> parameters = ContractTransformMap.transformContractByIdAndProductMap(contractId,productId);

        if(FunctionUtils.parametersIsValid(parameters, PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_ENTITY_ID,
                PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_BRANCH_ID,
                PISDColumn.Contract.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID,
                PISDColumn.Contract.FIELD_INSURANCE_PRODUCT_ID)){

            Operation operation = Operation.Builder.an()
                    .withTypeOperation(OperationConstants.Operation.SELECT)
                    .withNameProp(PISD_SQL_SELECT_CONTRACT_BY_ID_AND_PRODUCT)
                    .withIsForListQuery(false)
                    .withParams(parameters)
                    .build();

            Map<String,Object> map = (Map<String,Object>) this.baseDAO.executeQuery(operation);

            if(!CollectionUtils.isEmpty(map)){
                contractEntity = ContractTransformBean.mapTransformContractEntity(map).build();
            }
        }

        return contractEntity;
    }

    @Override
    public boolean findQuotationExistInContract(String quotationId) {
        LOGGER.info("[***] ContractDAOImpl findQuotationExistInContract -> {} ", quotationId);

        boolean resp = false;

        Map<String, Object> parameters = Collections.singletonMap(
                PISDColumn.Contract.FIELD_POLICY_QUOTA_INTERNAL_ID,quotationId);

        Operation operation = Operation.Builder.an()
                .withTypeOperation(OperationConstants.Operation.SELECT)
                .withNameProp(PISD_VALIDATE_IF_QUOTATION_EXISTS_IN_CONTRACT)
                .withIsForListQuery(false)
                .withParams(parameters).build();

        Map<String, Object> map = (Map<String, Object>) this.baseDAO.executeQuery(operation);

        Integer count = FunctionUtils.mapConvertToInteger(RESULT_NUMBER,map);

        if(count == 1){
            resp = true;
        }

        LOGGER.info("[***] ContractDAOImpl findQuotationExistInContract result -> {} ", resp);
        return resp;
    }

    @Override
    public int insertInsuranceContract(Map<String, Object> map) {
        int affectedRows = 0;

        if(FunctionUtils.parametersIsValid(map, PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_ENTITY_ID,
                PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_BRANCH_ID,
                PISDColumn.Contract.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID,
                PISDColumn.Contract.FIELD_INSURANCE_PRODUCT_ID,
                PISDColumn.Contract.FIELD_INSURANCE_MODALITY_TYPE,
                PISDColumn.Contract.FIELD_INSURANCE_COMPANY_ID,
                PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_START_DATE,
                PISDColumn.Contract.FIELD_CUSTOMER_ID,
                PISDColumn.Contract.FIELD_INSRNC_CO_CONTRACT_STATUS_TYPE,
                PISDColumn.Contract.FIELD_USER_AUDIT_ID
        )){
            Operation operation = Operation.Builder.an()
                    .withTypeOperation(OperationConstants.Operation.UPDATE)
                    .withNameProp(PISD_INSERT_INSURANCE_CONTRACT)
                    .withIsForListQuery(false)
                    .withParams(map).build();

            affectedRows = (int) this.baseDAO.executeQuery(operation);
        }

        return affectedRows;
    }

    @Override
    public ContractEntity findContractById(String contractId){
        LOGGER.info("[***] ContractDAOImpl findContractById - {} ", contractId);

        ContractEntity contractEntity = null;
        if (!FunctionUtils.contractIsValid(contractId)) return contractEntity;
        Map<String,Object> parameters = ContractTransformMap.transformContractById(contractId);

        Operation operation = Operation.Builder.an()
                .withTypeOperation(OperationConstants.Operation.SELECT)
                .withNameProp(PISD_FIND_CONTRACT_BY_ID)
                .withIsForListQuery(false)
                .withParams(parameters)
                .build();

        Map<String,Object> map = (Map<String,Object>) this.baseDAO.executeQuery(operation);

        if(!CollectionUtils.isEmpty(map)){
            contractEntity = ContractTransformBean.mapTransformContractEntity(map).build();
        }

        return contractEntity;
    }

}
