package com.bbva.pisd.lib.r226.dao;

import com.bbva.apx.exception.business.BusinessException;
import com.bbva.apx.exception.db.*;
import com.bbva.elara.domain.jdbc.CommonJdbcTemplate;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import com.bbva.pisd.dto.contract.constants.PISDErrors;
import com.bbva.pisd.dto.insurancedao.operation.OperationConstants;
import com.bbva.pisd.dto.insurancedao.operation.Operation;
import com.bbva.pisd.lib.r226.pattern.factory.impl.CommonJdbcFactory;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/META-INF/spring/PISDR226-app.xml",
        "classpath:/META-INF/spring/PISDR226-app-test.xml",
        "classpath:/META-INF/spring/PISDR226-arc.xml",
        "classpath:/META-INF/spring/PISDR226-arc-test.xml" })
public class BaseDAOTest extends TestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDAOTest.class);
    private static String queryTest = "select INSURANCE_CONTRACT_ENTITY_ID, INSURANCE_CONTRACT_BRANCH_ID, INSRC_CONTRACT_INT_ACCOUNT_ID FROM T_PISD_INSURANCE_CONTRACT";

    @Spy
    private Context context;

    @InjectMocks
    private CommonJdbcFactory baseDAO;

    @Mock
    private CommonJdbcTemplate commonJdbcTemplate;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        context = new Context();
        ThreadContext.set(context);
        getObjectIntrospection();
    }

    private Object getObjectIntrospection() throws Exception{
        Object result = this.baseDAO;
        if(this.baseDAO instanceof Advised){
            Advised advised = (Advised) this.baseDAO;
            result = advised.getTargetSource().getTarget();
        }
        return result;
    }

    private Map<String, Object> PrepareParamsToContract(){
        Map<String, Object> params = new HashMap<>();
        // set values
        params.put("SETTLE_PENDING_PREMIUM_AMOUNT","2500");
        params.put("CONTRACT_REGIS_CST_AGE_NUMBER","2");
        params.put("ISSUED_RECEIPT_NUMBER","02");
        params.put("PERIOD_NEXT_PAYMENT_DATE","2021-04-20");
        params.put("NET_PREM_PLCY_ORIG_CURR_AMOUNT","750");
        params.put("ACCUM_OUTSTANDING_DEBT_AMOUNT","150");
        params.put("PREV_PEND_BILL_RCPTS_NUMBER","850");
        // Where condition
        params.put("INSURANCE_CONTRACT_ENTITY_ID","0011");
        params.put("INSURANCE_CONTRACT_BRANCH_ID","0142");
        params.put("INSRC_CONTRACT_INT_ACCOUNT_ID","4000317312");

        return params;
    }
    private Map<String, Object> PrepareParamsSelectConditions() {
        Map<String, Object> params = new HashMap<>();
        params.put("PERIOD_NEXT_PAYMENT_DATE","ICTD");

        return params;
    }

    @Test
    public void executeQuerySelectTest(){
        LOGGER.info("Executing the executeQuerySelectTest...");
        Operation opSelect = new Operation();
        opSelect.setQuery("cnxPISD;select SETTLE_PENDING_PREMIUM_AMOUNT FROM T_PISD_INSURANCE_CONTRACT");
        opSelect.setTypeOperation(OperationConstants.Operation.SELECT);
        opSelect.setParams(PrepareParamsSelectConditions());
        opSelect.setForListQuery(false);

        Map<String, Object> resultExpected = new HashMap<>();
        resultExpected.put("INSURANCE_CONTRACT_ENTITY_ID","0011");
        resultExpected.put("INSURANCE_CONTRACT_BRANCH_ID","0486");
        resultExpected.put("INSRC_CONTRACT_INT_ACCOUNT_ID","1222");
        resultExpected.put("SETTLE_PENDING_VAR_PREM_AMOUNT","2500");

        Mockito.when(commonJdbcTemplate.queryForMap(Mockito.anyString(), Mockito.anyMap())).thenReturn(resultExpected);

        Object resultObject = baseDAO.executeQuery(opSelect);
        Map<String, Object> result = (Map<String, Object>) resultObject;
        Assert.assertEquals(resultExpected, resultObject);
        Mockito.verify(commonJdbcTemplate, Mockito.atLeastOnce())
                .queryForMap(Mockito.anyString(),
                        Mockito.anyMap());
    }

    @Test
    public void executeQueryUpdateTest(){
        LOGGER.info("Executing the executeQueryUpdateTest...");
        Operation opUpdated = new Operation();
        opUpdated.setQuery("cnxPISD;UPDATE T_PISD_INSURANCE_CONTRACT " +
                " SET SETTLE_PENDING_PREMIUM_AMOUNT= :SETTLE_PENDING_PREMIUM_AMOUNT " +
                " WHERE INSURANCE_CONTRACT_ENTITY_ID= :INSURANCE_CONTRACT_ENTITY_ID " +
                "  AND INSURANCE_CONTRACT_BRANCH_ID= :INSURANCE_CONTRACT_BRANCH_ID " +
                "  AND INSRC_CONTRACT_INT_ACCOUNT_ID= :INSRC_CONTRACT_INT_ACCOUNT_ID ");
        opUpdated.setTypeOperation(OperationConstants.Operation.UPDATE);
        opUpdated.setParams(PrepareParamsToContract());

        Mockito.when(commonJdbcTemplate.update(Mockito.anyString(), Mockito.anyMap())).thenReturn(1);

        Object resultObject = baseDAO.executeQuery(opUpdated);

        int result = (int) resultObject;

        Assert.assertEquals(1, result);
        Mockito.verify(commonJdbcTemplate, Mockito.atLeastOnce())
                .update(Mockito.anyString(),
                        Mockito.anyMap());
    }

    @Test
    public void executeQueryForListOKTest(){
        LOGGER.info("Executing the executeQueryForListOKTest...");
        Operation opSelect = new Operation();
        opSelect.setQuery("cnxPISD;select SETTLE_PENDING_PREMIUM_AMOUNT FROM T_PISD_INSURANCE_CONTRACT");
        opSelect.setTypeOperation(OperationConstants.Operation.SELECT);
        opSelect.setParams(PrepareParamsSelectConditions());
        opSelect.setForListQuery(true);

        Map<String, Object> resultExpected = new HashMap<>();
        resultExpected.put("INSURANCE_CONTRACT_ENTITY_ID","0011");
        resultExpected.put("INSURANCE_CONTRACT_BRANCH_ID","0486");
        resultExpected.put("INSRC_CONTRACT_INT_ACCOUNT_ID","1222");
        resultExpected.put("SETTLE_PENDING_VAR_PREM_AMOUNT","2500");

        Map<String, Object> resultExpected01 = new HashMap<>();
        resultExpected01.put("INSURANCE_CONTRACT_ENTITY_ID","0011");
        resultExpected01.put("INSURANCE_CONTRACT_BRANCH_ID","0242");
        resultExpected01.put("INSRC_CONTRACT_INT_ACCOUNT_ID","111");
        resultExpected01.put("SETTLE_PENDING_VAR_PREM_AMOUNT","4588");

        List<Map<String,Object>> responseExpected = new ArrayList<>();
        responseExpected.add(resultExpected);
        responseExpected.add(resultExpected01);
        Mockito.when(commonJdbcTemplate.queryForList(Mockito.anyString(), Mockito.anyMap())).thenReturn(responseExpected);

        Object resultObject= baseDAO.executeQuery(opSelect);
        List<Map<String,Object>> result = (List<Map<String,Object>>) resultObject;

        Assert.assertNotNull( resultObject);

        Assert.assertEquals(2,result.size() );
        Mockito.verify(commonJdbcTemplate, Mockito.atLeastOnce())
                .queryForList(Mockito.anyString(),
                        Mockito.anyMap());

    }

    @Test
    @Ignore
    public void executeQueryNoResultExceptionTest(){
        LOGGER.info("Executing the executeQueryUpdateTest...");
        Operation opUpdated = new Operation();
        opUpdated.setTable("UPDATE T_PISD_INSURANCE_CONTRACT");
        opUpdated.setTypeOperation(OperationConstants.Operation.SELECT);
        opUpdated.setParams(PrepareParamsToContract());
        opUpdated.setForListQuery(false);

        Mockito.when(commonJdbcTemplate.queryForMap(Mockito.anyString(), Mockito.anyMap())).thenThrow(NoResultException.class);

        Object resultObject = null;

        try {
            resultObject= baseDAO.executeQuery(opUpdated);
        }catch (NoResultException e){
            Assert.assertEquals(null, resultObject);
            Assert.assertEquals(PISDErrors.ERROR_DUPLICATE_KEY.getAdviceCode(), this.context.getAdviceList().get(0).getCode());
            Mockito.verify(commonJdbcTemplate, Mockito.atLeastOnce())
                    .queryForMap(Mockito.anyString(),
                            Mockito.anyMap());
        }

    }

    @Test
    @Ignore
    public void executeQuerySelectTimeoutExceptionTest(){
        LOGGER.info("Executing the executeQuerySelectAPXExceptionTest...");
        Operation opSelect = new Operation();
        opSelect.setQuery("cnxPISD;select SETTLE_PENDING_PREMIUM_AMOUNT FROM T_PISD_INSURANCE_CONTRACT");
        opSelect.setTypeOperation(OperationConstants.Operation.SELECT);
        opSelect.setParams(PrepareParamsSelectConditions());

        Mockito.when(commonJdbcTemplate.queryForMap(Mockito.anyString(), Mockito.anyMap())).thenThrow(TimeoutException.class);

        Object resultObject = null;

        try {
            resultObject= baseDAO.executeQuery(opSelect);
            Assert.fail();
        }catch (BusinessException e){
            Assert.assertEquals(null, resultObject);
            Assert.assertEquals(PISDErrors.ERROR_TIME_OUT.getAdviceCode(), e.getAdviceCode());
            Assert.assertEquals(PISDErrors.ERROR_TIME_OUT.getAdviceCode(), this.context.getAdviceList().get(0).getCode());
            Mockito.verify(commonJdbcTemplate, Mockito.atLeastOnce())
                    .queryForMap(Mockito.anyString(),
                            Mockito.anyMap());
        }
    }

    @Test
    @Ignore
    public void executeQueryDuplicateKeyExceptionTest(){
        LOGGER.info("Executing the executeQueryDuplicateKeyExceptionTest...");
        Operation opSelect = new Operation();
        opSelect.setTypeOperation(OperationConstants.Operation.SELECT);
        opSelect.setParams(PrepareParamsSelectConditions());
        opSelect.setForListQuery(false);

        Mockito.when(commonJdbcTemplate.queryForMap(Mockito.anyString(), Mockito.anyMap())).thenThrow(DuplicateKeyException.class);

        Object resultObject = null;

        try {
            resultObject= baseDAO.executeQuery(opSelect);
            Assert.fail();
        }catch (BusinessException e){
            Assert.assertEquals(null, resultObject);
            Assert.assertEquals(PISDErrors.ERROR_DUPLICATE_KEY.getAdviceCode(), e.getAdviceCode());
            Assert.assertEquals(PISDErrors.ERROR_DUPLICATE_KEY.getAdviceCode(), this.context.getAdviceList().get(0).getCode());
            Assert.assertEquals(PISDErrors.ERROR_DUPLICATE_KEY.isRollback(), PISDErrors.ERROR_DUPLICATE_KEY.isRollback());
            Assert.assertEquals(PISDErrors.ERROR_DUPLICATE_KEY.getMessage(), PISDErrors.ERROR_DUPLICATE_KEY.getMessage());
            Mockito.verify(commonJdbcTemplate, Mockito.atLeastOnce())
                    .queryForMap(Mockito.anyString(),
                            Mockito.anyMap());
        }
    }

    //@Test
    public void executeQueryDataIntegrityViolationExceptionTest(){
        LOGGER.info("Executing the executeQueryDataIntegrityViolationExceptionTest...");
        Operation opSelect = new Operation();
        opSelect.setTypeOperation(OperationConstants.Operation.UPDATE);
        opSelect.setQuery("cnxPISD;UPDATE T_PISD_INSURANCE_CONTRACT " +
                " SET SETTLE_PENDING_PREMIUM_AMOUNT= :SETTLE_PENDING_PREMIUM_AMOUNT " +
                " WHERE INSURANCE_CONTRACT_ENTITY_ID= :INSURANCE_CONTRACT_ENTITY_ID " +
                "  AND INSURANCE_CONTRACT_BRANCH_ID= :INSURANCE_CONTRACT_BRANCH_ID " +
                "  AND INSRC_CONTRACT_INT_ACCOUNT_ID= :INSRC_CONTRACT_INT_ACCOUNT_ID ");
        opSelect.setParams(PrepareParamsSelectConditions());

        Mockito.when(commonJdbcTemplate.update(Mockito.anyString(), Mockito.anyMap())).thenThrow(DataIntegrityViolationException.class);

        Object resultObject = null;

        try {
            resultObject= baseDAO.executeQuery(opSelect);
            Assert.fail();
        }catch (BusinessException e){
            Assert.assertEquals(null, resultObject);
            Assert.assertEquals(PISDErrors.ERROR_DUPLICATE_KEY.getAdviceCode(), e.getAdviceCode());
            Assert.assertEquals(PISDErrors.ERROR_DUPLICATE_KEY.getAdviceCode(), this.context.getAdviceList().get(0).getCode());
            Mockito.verify(commonJdbcTemplate, Mockito.atLeastOnce())
                    .update(Mockito.anyString(),
                            Mockito.anyMap());
        }
    }

    @Test
    @Ignore
    public void executeQueryIncorrectResultSizeExceptionTest(){
        LOGGER.info("Executing the executeQueryIncorrectResultSizeExceptionTest...");
        Operation opSelect = new Operation();
        opSelect.setTypeOperation(OperationConstants.Operation.SELECT);
        opSelect.setParams(PrepareParamsSelectConditions());
        opSelect.setForListQuery(false);
        Mockito.when(commonJdbcTemplate.queryForMap(Mockito.anyString(), Mockito.anyMap())).thenThrow(IncorrectResultSizeException.class);

        Object resultObject = null;

        try {
            resultObject= baseDAO.executeQuery(opSelect);
            Assert.fail();
        }catch (BusinessException e){
            Assert.assertEquals(null, resultObject);
            Assert.assertEquals(PISDErrors.ERROR_INCORRECT_RESULT.getAdviceCode(), e.getAdviceCode());
            Assert.assertEquals(PISDErrors.ERROR_INCORRECT_RESULT.getAdviceCode(), this.context.getAdviceList().get(0).getCode());
            Mockito.verify(commonJdbcTemplate, Mockito.atLeastOnce())
                    .queryForMap(Mockito.anyString(),
                            Mockito.anyMap());
        }
    }

    @Test
    public void executeBatchQueryTest(){
        LOGGER.info("Executing the executeBatchQueryTest...");
        Operation opSelect = new Operation();
        opSelect.setNameProp("UPDATED_CONTRACT");
        opSelect.setTypeOperation(OperationConstants.Operation.BATCH);
        Map<String, Object> [] params = new Map[]{ PrepareParamsToContract(), PrepareParamsToContract()};
        opSelect.setBatchValues(params);

        int [] resultExpected = new int[2];
        Mockito.when(commonJdbcTemplate.batchUpdate(Mockito.anyString(), Mockito.any(params.getClass()))).thenReturn(resultExpected);

        Object resultObject = baseDAO.executeQuery(opSelect);

        int [] result = (int []) resultObject;
        Assert.assertEquals(result.length, resultExpected.length);
        Mockito.verify(commonJdbcTemplate, Mockito.atLeastOnce())
                .batchUpdate(Mockito.anyString(),
                        Mockito.any(params.getClass()));
    }

    @Test
    @Ignore
    public void executeQueryListOK() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("INSURANCE_CONTRACT_ENTITY_ID","0011");

        Map<String, Object> resultExpected = new HashMap<>();
        resultExpected.put("INSURANCE_CONTRACT_ENTITY_ID","0011");
        resultExpected.put("INSURANCE_CONTRACT_BRANCH_ID","0486");
        resultExpected.put("INSRC_CONTRACT_INT_ACCOUNT_ID","1222");
        resultExpected.put("SETTLE_PENDING_VAR_PREM_AMOUNT","2500");

        List<Map<String,Object>> responseExpected = new ArrayList<>();
        responseExpected.add(resultExpected);
        when(commonJdbcTemplate.queryForList(Mockito.anyString(), Mockito.anyMap())).thenReturn(responseExpected);
        List<Map<String, Object>> result = baseDAO.executeQueryListPagination(parameters, queryTest);

        Assert.assertNotNull( result );

        Assert.assertEquals(1, result.size() );
        Mockito.verify(commonJdbcTemplate, Mockito.atLeastOnce()).queryForList(Mockito.anyString(),Mockito.anyMap());
    }

    @Test
    @Ignore
    public void executeQueryListExceptionTest(){
        LOGGER.info("Executing the executeQueryUpdateTest...");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("INSURANCE_CONTRACT_ENTITY_ID","0011");

        when(commonJdbcTemplate.queryForList(Mockito.anyString(), Mockito.anyMap())).thenThrow(NoResultException.class);

        Object resultObject = null;

        try {
            resultObject= baseDAO.executeQueryListPagination(parameters, queryTest);
        }catch (NoResultException e){
            Assert.assertEquals(null, resultObject);
            Assert.assertEquals(PISDErrors.QUERY_EMPTY_RESULT.getAdviceCode(), this.context.getAdviceList().get(0).getCode());
            Mockito.verify(commonJdbcTemplate, Mockito.atLeastOnce()).queryForMap(Mockito.anyString(),Mockito.anyMap());
        }
    }

    @Test
    @Ignore
    public void executeQueryListPaginationOK() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("INSURANCE_CONTRACT_ENTITY_ID","0011");

        Map<String, Object> resultExpected = new HashMap<>();
        resultExpected.put("INSURANCE_CONTRACT_ENTITY_ID","0011");
        resultExpected.put("INSURANCE_CONTRACT_BRANCH_ID","0486");
        resultExpected.put("INSRC_CONTRACT_INT_ACCOUNT_ID","1222");
        resultExpected.put("SETTLE_PENDING_VAR_PREM_AMOUNT","2500");

        List<Map<String,Object>> responseExpected = new ArrayList<>();
        responseExpected.add(resultExpected);
        when(commonJdbcTemplate.queryForList(Mockito.anyString(), Mockito.anyMap())).thenReturn(responseExpected);
        List<Map<String, Object>> result = baseDAO.executeQueryListPagination(parameters, queryTest);

        Assert.assertNotNull( result );
        Assert.assertEquals(1, result.size() );
    }

    @Test
    @Ignore
    public void executeQueryListPaginationExceptionTest(){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("INSURANCE_CONTRACT_ENTITY_ID","0011");

        when(commonJdbcTemplate.queryForList(Mockito.anyString(), Mockito.anyMap())).thenThrow(NoResultException.class);

        Object resultObject = null;

        try {
            resultObject= baseDAO.executeQueryListPagination(parameters, queryTest);
        }catch (NoResultException e){
            Assert.assertEquals(null, resultObject);
            Assert.assertEquals(PISDErrors.QUERY_EMPTY_RESULT.getAdviceCode(), this.context.getAdviceList().get(0).getCode());
        }
    }
}