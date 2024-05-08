package com.bbva.pisd.lib.r226;

import com.bbva.apx.exception.db.NoResultException;
import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.domain.jdbc.CommonJdbcTemplate;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import javax.annotation.Resource;

import com.bbva.pisd.dto.contract.common.ReceiptDTO;
import com.bbva.pisd.dto.insurancedao.entities.PaymentPeriodEntity;
import com.bbva.pisd.lib.r226.pattern.factory.impl.JdbcUtilsFactory;
import com.bbva.pisd.lib.r226.transfor.map.ReceiptTransformMap;
import com.bbva.pisd.lib.r226.util.CatalogEnum;
import com.bbva.pisd.lib.r226.util.Properties;
import org.mockito.Mockito;
import com.bbva.elara.utility.jdbc.JdbcUtils;
import com.bbva.pisd.dto.contract.constants.PISDStatus;

import com.bbva.pisd.dto.contract.search.ReceiptSearchCriteria;
import com.bbva.pisd.dto.insurancedao.constants.PISDColumn;
import com.bbva.pisd.dto.insurancedao.constants.PISDConstant;
import com.bbva.pisd.dto.insurancedao.entities.ContractEntity;
import com.bbva.pisd.lib.r226.pattern.factory.impl.CommonJdbcFactory;
import com.bbva.pisd.lib.r226.pattern.factory.impl.CommonJdbcFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.aop.framework.Advised;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/PISDR226-app.xml",
		"classpath:/META-INF/spring/PISDR226-app-test.xml",
		"classpath:/META-INF/spring/PISDR226-arc.xml",
		"classpath:/META-INF/spring/PISDR226-arc-test.xml" })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PISDR226Test {

	@Spy
	private Context context;

	@Resource(name = "pisdR226")
	private PISDR226 pisdR226;
	@Resource(name = "jdbcUtils")
	private JdbcUtils jdbcUtils;

	private JdbcUtilsFactory jdbcUtilsFactory;

	@Resource(name = "applicationConfigurationService")
	private ApplicationConfigurationService applicationConfigurationService;

	private CommonJdbcFactory commonJdbcFactory;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		context = new Context();
		ThreadContext.set(context);
		getObjectIntrospection();

		commonJdbcFactory = Mockito.mock(CommonJdbcFactory.class);
		jdbcUtilsFactory = Mockito.mock(JdbcUtilsFactory.class);
	}
	
	private Object getObjectIntrospection() throws Exception{
		Object result = this.pisdR226;
		if(this.pisdR226 instanceof Advised){
			Advised advised = (Advised) this.pisdR226;
			result = advised.getTargetSource().getTarget();
		}
		return result;
	}


	@Test
	public void executeFindContractBySearchCriteriaConPaginacion(){
		/**
		 *  Context
		 * */

		CommonJdbcTemplate commonJdbcTemplate = Mockito.mock(CommonJdbcTemplate.class);
		this.pisdR226.executeSetCommonJdbcTemplate(commonJdbcTemplate);

		Map<String, Object> receipt = this.getMockMapQueryFindReceiptsSuccessFull();
		List<Map<String, Object>> result = new ArrayList<>();
		result.add(receipt);

		HashMap<String,Object> countRow = new HashMap<>();
		countRow.put(PISDConstant.Pagination.COLUMN_COUNT, 5001);

		ReceiptSearchCriteria receiptSearchCriteria = this.getMockReceiptSearchCriteria();

		Mockito.when(commonJdbcTemplate.queryForList(Mockito.anyString(),Mockito.anyMap())).thenReturn(result);
		Mockito.when(commonJdbcTemplate.queryForMap(Mockito.anyString(),Mockito.anyMap())).thenReturn(countRow);

		/**
		 * Ejecución
		 * */

	List<ContractEntity> listContract = this.pisdR226.executeFindContractBySearchCriteria(receiptSearchCriteria);
		Assert.assertNotNull(listContract);
	}
	@Test
	public void executeFindContractBySearchCriteriaSinPaginacion(){
		/**
		 *  Context
		 * */

		CommonJdbcTemplate commonJdbcTemplate = Mockito.mock(CommonJdbcTemplate.class);
		this.pisdR226.executeSetCommonJdbcTemplate(commonJdbcTemplate);

		Map<String, Object> receipt = this.getMockMapQueryFindReceiptsSuccessFull();
		List<Map<String, Object>> result = new ArrayList<>();
		result.add(receipt);

		HashMap<String,Object> countRow = new HashMap<>();
		countRow.put(PISDConstant.Pagination.COLUMN_COUNT, 2);

		ReceiptSearchCriteria receiptSearchCriteria = this.getMockReceiptSearchCriteria();

		Mockito.when(commonJdbcTemplate.queryForList(Mockito.anyString(),Mockito.anyMap())).thenReturn(result);
		Mockito.when(commonJdbcTemplate.queryForMap(Mockito.anyString(),Mockito.anyMap())).thenReturn(countRow);

		/**
		 * Ejecución
		 * */

		List<ContractEntity> listContract = this.pisdR226.executeFindContractBySearchCriteria(receiptSearchCriteria);
		Assert.assertNotNull(listContract);
	}

	@Test
	public void executeUpdateReceiptsJdbcUtilsTest(){

		Map<String, String> contratos = new HashMap<>();
		contratos.put(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_ENTITY_ID, "1");

		Mockito.when(this.jdbcUtils.update(Mockito.anyString(),Mockito.anyMap())).thenReturn(1);
		this.pisdR226.executeSetCommonJdbcTemplate(null);
		boolean result2 = this.pisdR226.executeUpdateBiometricId("12345678901234567890","12345678","1234");
		Assert.assertTrue(result2);

	}
	@Test
	public void executeFindByCertifiedBankByJdbcUtilsTest(){
		Map<String, Object> contratos = new HashMap<>();
		contratos.put("COUNT",1);
		Mockito.when(this.jdbcUtils.queryForMap(Mockito.anyString(),Mockito.anyMap())).thenReturn(contratos);
		boolean resp=this.pisdR226.executeFindByContract("12345678");
		Assert.assertTrue(resp);
	}

	private Map<String,Object> getMockMapQueryFindReceiptsSuccessFull(){
		Map<String,Object> mapResponse = new HashMap<>();

		mapResponse.put(PISDColumn.Receipt.FIELD_INSURANCE_CONTRACT_ENTITY_ID, "0011");
		mapResponse.put(PISDColumn.Receipt.FIELD_INSURANCE_CONTRACT_BRANCH_ID, "0172");
		mapResponse.put(PISDColumn.Contract.FIELD_CONTRACT_FIRST_VERFN_DIGIT_ID, "1");
		mapResponse.put(PISDColumn.Contract.FIELD_CONTRACT_SECOND_VERFN_DIGIT_ID, "2");
		mapResponse.put(PISDColumn.Receipt.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID, "4000018548");
		mapResponse.put(PISDColumn.Contract.FIELD_POLICY_ID, "1234567");
		mapResponse.put(PISDColumn.Receipt.FIELD_RECEIPT_START_DATE,new Date());
		mapResponse.put(PISDColumn.Receipt.FIELD_RECEIPT_END_DATE,new Date());
		mapResponse.put(PISDColumn.Contract.FIELD_CUSTOMER_ID, "12345678");
		mapResponse.put(PISDColumn.Contract.FIELD_INSURANCE_MODALITY_TYPE, "1");
		mapResponse.put(PISDColumn.Contract.FIELD_CONTRACT_STATUS_ID, "FOR");
		mapResponse.put(PISDColumn.Receipt.FIELD_POLICY_RECEIPT_ID, 1);
		mapResponse.put(PISDColumn.Receipt.FIELD_INSURANCE_COMPANY_RECEIPT_ID, "123456");
		mapResponse.put(PISDColumn.Receipt.FIELD_PREMIUM_PAYMENT_RECEIPT_AMOUNT, 123.0);
		mapResponse.put(PISDColumn.Receipt.FIELD_CURRENCY_ID, "USD");
		mapResponse.put(PISDColumn.Receipt.FIELD_RECEIPT_STATUS_TYPE, "FAC");

		return mapResponse;
	}

	private ReceiptSearchCriteria getMockReceiptSearchCriteria(){
		ReceiptSearchCriteria receiptSearchCriteria = new ReceiptSearchCriteria();

		String receiptStatus = PISDStatus.RECEIPT_STATUS_TYPE.FAC.getValue();

		receiptSearchCriteria.setContractStatusId(PISDStatus.CONTRACT_STATUS_ID.FOR.getValue());
		receiptSearchCriteria.setContractPaymentMeansType(PISDStatus.CONTRACT_PAYMENT_MEANS_TYPE.EXT.getValue());
		receiptSearchCriteria.setReceiptStatusSearch(receiptStatus);

		return receiptSearchCriteria;

	}


	@Test
	public void executeGetRoyalPolicyDetailTest(){
		/**
		 *  Context
		 * */

		String contractNumber = "01234567890123456789";

		Map<String, Object> arg = new HashMap<>();
		arg.put(CatalogEnum.RECEIPT_STATUS_TYPE.name(), CatalogEnum.INC.name());

		List<Map<String, Object>> list = new ArrayList<>();
		list.add(arg);

		Mockito.when(jdbcUtils.queryForList(Mockito.anyString(),Mockito.anyMap())).thenReturn(list);

		List<Map<String, Object>> listContract2 = this.pisdR226.executeGetRoyalPolicyDetail(contractNumber);
		Assert.assertNotNull(listContract2);

		/**
		 * Ejecución
		 * */


	}

	@Test
	public void executeGetRoyalPolicyDetailTestCatch(){

		/**
		 *  Context
		 * */

		String contractNumber = "01234567890123456789";

		when(jdbcUtils.queryForList(Mockito.anyString(),Mockito.anyMap())).thenThrow(new NoResultException("NO RESULT"));
		List<Map<String, Object>> validation = this.pisdR226.executeGetRoyalPolicyDetail(contractNumber);
		assertEquals(0, validation.size());

		/**
		 * Ejecución
		 * */

	}

	@Test
	public void executeGetReceiptsTest(){
		/**
		 *  Context
		 * */

		String contractNumber = "01234567890123456789";

		Map<String, Object> arg = new HashMap<>();
		arg.put(CatalogEnum.RECEIPT_STATUS_TYPE.name(), CatalogEnum.INC.name());

		List<Map<String, Object>> list = new ArrayList<>();
		list.add(arg);

		Mockito.when(jdbcUtils.queryForList(Mockito.anyString(),Mockito.anyMap())).thenReturn(list);

		List<ReceiptDTO> listContract2 = this.pisdR226.executeGetReceipts(contractNumber);
		Assert.assertNotNull(listContract2);

		/**
		 * Ejecución
		 * */

	}

	@Test
	public void executeGetReceiptsTestCatch(){

		/**
		 *  Context
		 * */
		String contractNumber = "01234567890123456789";

		when(jdbcUtils.queryForList(Mockito.anyString(),Mockito.anyMap())).thenThrow(new NoResultException("NO RESULT"));
		List<ReceiptDTO> validation = this.pisdR226.executeGetReceipts(contractNumber);
		assertEquals(0, validation.size());

		/**
		 * Ejecución
		 * */

	}

	@Test
	public void receiptSearchTransformMapNullTest(){

		/**
		 *  Context
		 * */

		String contractNumber = "012345678901234567890";
		Map<String, Object> validation = ReceiptTransformMap.receiptSearchTransformMap(contractNumber);
		assertEquals(Collections.emptyMap(),validation);

		/**
		 * Ejecución
		 * */

	}






}
