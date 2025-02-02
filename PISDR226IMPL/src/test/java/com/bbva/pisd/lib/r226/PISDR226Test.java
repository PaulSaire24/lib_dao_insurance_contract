package com.bbva.pisd.lib.r226;

import com.bbva.apx.exception.db.NoResultException;
import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.domain.jdbc.CommonJdbcTemplate;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import javax.annotation.Resource;

import com.bbva.pisd.dto.contract.common.ReceiptDTO;
import com.bbva.pisd.dto.contract.search.CertifyBankCriteria;
import com.bbva.pisd.dto.insurancedao.entities.PaymentPeriodEntity;
import com.bbva.pisd.lib.r226.mock.Mock;
import com.bbva.pisd.lib.r226.pattern.factory.DAOFactory;
import com.bbva.pisd.lib.r226.pattern.factory.impl.JdbcUtilsFactory;
import com.bbva.pisd.lib.r226.pattern.factory.interfaces.BaseDAO;
import com.bbva.pisd.lib.r226.transfor.list.ReceiptTransformList;
import com.bbva.pisd.lib.r226.transfor.map.ReceiptTransformMap;
import com.bbva.pisd.lib.r226.util.CatalogEnum;
import org.mockito.Mockito;
import com.bbva.elara.utility.jdbc.JdbcUtils;
import com.bbva.pisd.dto.contract.constants.PISDStatus;

import com.bbva.pisd.dto.contract.search.ReceiptSearchCriteria;
import com.bbva.pisd.dto.insurancedao.constants.PISDColumn;
import com.bbva.pisd.dto.insurancedao.constants.PISDConstant;
import com.bbva.pisd.dto.insurancedao.entities.ContractEntity;
import com.bbva.pisd.lib.r226.pattern.factory.impl.CommonJdbcFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.aop.framework.Advised;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertTrue;

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
		assertTrue(result2);

	}
	@Test
	public void executeFindByCertifiedBankByJdbcUtilsTest(){
		Map<String, Object> contratos = new HashMap<>();
		contratos.put("COUNT",1);
		Mockito.when(this.jdbcUtils.queryForMap(Mockito.anyString(),Mockito.anyMap())).thenReturn(contratos);
		boolean resp=this.pisdR226.executeFindByContract("12345678");
		assertTrue(resp);
	}

	private Map<String,Object> getMockMapQueryFindReceiptsSuccessFull(){
		Map<String,Object> mapResponse = new HashMap<>();

		mapResponse.put(PISDColumn.Receipt.FIELD_INSURANCE_CONTRACT_ENTITY_ID, "0011");
		mapResponse.put(PISDColumn.Receipt.FIELD_INSURANCE_CONTRACT_BRANCH_ID, "0172");
		mapResponse.put(PISDColumn.Contract.FIELD_CONTRACT_FIRST_VERFN_DIGIT_ID, "1");
		mapResponse.put(PISDColumn.Contract.FIELD_CONTRACT_SECOND_VERFN_DIGIT_ID, "2");
		mapResponse.put(PISDColumn.Receipt.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID, "4000018548");
		mapResponse.put(PISDColumn.Contract.FIELD_POLICY_ID, "1234567");
		mapResponse.put(PISDColumn.Receipt.FIELD_RECEIPT_START_DATE,"2021-01-01");
		mapResponse.put(PISDColumn.Receipt.FIELD_RECEIPT_END_DATE,"2021-01-01");
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
		Map<String, Object> validation = ReceiptTransformMap.mapContractNumber(contractNumber);
		assertEquals(Collections.emptyMap(),validation);

		/**
		 * Ejecución
		 * */

	}

	@Test
	public void executeExistContractByIdAndProductIdTest(){
		Map<String, Object> contratos = new HashMap<>();
		contratos.put(PISDColumn.Contract.FIELD_POLICY_ID, "139383334");
		contratos.put(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_START_DATE, "2025-01-04");
		contratos.put(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_END_DATE, "2026-01-04");
		contratos.put(PISDColumn.Contract.FIELD_CUSTOMER_ID, "97194649");
		contratos.put(PISDColumn.Contract.FIELD_PREMIUM_AMOUNT, new BigDecimal(932));
		contratos.put(PISDColumn.Contract.FIELD_INSRNC_CO_CONTRACT_STATUS_TYPE, "FOR");
		contratos.put(PISDColumn.Contract.FIELD_CONTRACT_STATUS_ID, "FOR");

		Mockito.when(this.jdbcUtils.queryForMap(Mockito.anyString(),Mockito.anyMap())).thenReturn(contratos);

		ContractEntity result = this.pisdR226.executeExistContractByIdAndProductId("00110473284000037421","13");
		Assert.assertNotNull(result);
	}

	@Test
	public void executeFindQuotationIfExistInContractTest(){

		Map<String, Object> contratos = new HashMap<>();
		contratos.put("RESULT_NUMBER",1);

		Mockito.when(this.jdbcUtils.queryForMap(Mockito.anyString(),Mockito.anyMap())).thenReturn(contratos);

		boolean resp = this.pisdR226.executeFindQuotationIfExistInContract("12345678");

		Assert.assertTrue(resp);

	}

	@Test
	public void executeFindPaymentPeriodByTypeTest(){
		Map<String, Object> mapResult = new HashMap<>();
		mapResult.put(PISDColumn.PaymentPeriod.FIELD_PAYMENT_FREQUENCY_ID, 1);
		mapResult.put(PISDColumn.PaymentPeriod.FIELD_POLICY_PAYMENT_FREQUENCY_TYPE, "M");
		mapResult.put(PISDColumn.PaymentPeriod.FIELD_PAYMENT_FREQUENCY_NAME, "Mensual");

		Mockito.when(this.jdbcUtils.queryForMap(Mockito.anyString(),Mockito.anyMap())).thenReturn(mapResult);

		PaymentPeriodEntity result = this.pisdR226.executeFindPaymentPeriodByType("M");

		Assert.assertNotNull(result);
	}

	@Test
	public void executeInsertInsuranceContractTest(){
		Map<String, Object> map = new HashMap<>();
		map.put(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_ENTITY_ID, "0011");
		map.put(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_BRANCH_ID, "0846");
		map.put(PISDColumn.Contract.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID, "1009836432");
		map.put(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_START_DATE, "19/05/21");
		map.put(PISDColumn.Contract.FIELD_INSURANCE_CONTRACT_END_DATE, "19/05/22");
		map.put(PISDColumn.Contract.FIELD_CUSTOMER_ID, "12345678");
		map.put(PISDColumn.Contract.FIELD_PREMIUM_AMOUNT, 123.0);
		map.put(PISDColumn.Contract.FIELD_INSRNC_CO_CONTRACT_STATUS_TYPE, "FOR");
		map.put(PISDColumn.Contract.FIELD_AUDIT_DATE, "19/05/21 04:20:49,151987000 PM");
		map.put(PISDColumn.Contract.FIELD_USER_AUDIT_ID, "p121328");
		map.put(PISDColumn.Contract.FIELD_CONTRACT_STATUS_ID, "FOR");
		map.put(PISDColumn.Contract.FIELD_INSURANCE_PRODUCT_ID, 13);
		map.put(PISDColumn.Contract.FIELD_INSURANCE_MODALITY_TYPE,"01");
		map.put(PISDColumn.Contract.FIELD_INSURANCE_COMPANY_ID, "01");

		Mockito.when(this.jdbcUtils.update(Mockito.anyString(),Mockito.anyMap())).thenReturn(1);

		int result = this.pisdR226.executeInsertInsuranceContract(map);

		Assert.assertEquals(1,result);

	}
	@Test
	public void testGetDetailMappedWithEmptyList() {

		/**
		 *  Context
		 * */

		List<Map<String, Object>> emptyList = Collections.emptyList();
		List<ReceiptDTO> result = ReceiptTransformList.transformListMapToListReceiptDTO(emptyList);
		assertTrue("The result should be an empty list", result.isEmpty());

		/**
		 * Ejecución
		 * */

	}

	@Test
	public void executeFindByCertifiedBankTest(){

		CommonJdbcTemplate commonJdbcTemplate = Mockito.mock(CommonJdbcTemplate.class);
		this.pisdR226.executeSetCommonJdbcTemplate(commonJdbcTemplate);
		Map<String, Object> contratos = new HashMap<>();
		contratos.put("00114444111010101010", Mock.mockContract());
		List<Map<String, Object>> result = new ArrayList<>();
		result.add(contratos);

		Mockito.when(commonJdbcTemplate.queryForList(Mockito.anyString(),Mockito.anyMap())).thenReturn(result);

		CertifyBankCriteria certifyBankCriteria = Mock.mockCertifyCriteria();
		ContractEntity contractEntity =  this.pisdR226.executeFindByCertifiedBank(certifyBankCriteria);
		Assert.assertNotNull(contractEntity);
	}
	@Test
	public void executeFindByCertifiedBankByJdbcUtilsTest2(){
		CommonJdbcTemplate commonJdbcTemplate = null;
		Map<String, Object> contratos = new HashMap<>();
		contratos.put("00114444111234567890", Mock.mockContract2());
		List<Map<String, Object>> result = new ArrayList<>();
		result.add(contratos);

		Mockito.when(this.jdbcUtils.queryForList(Mockito.anyString(),Mockito.anyMap())).thenReturn(result);

		BaseDAO daoFactory = DAOFactory.getDAOFactory(commonJdbcTemplate,jdbcUtils);


		this.pisdR226.executeSetCommonJdbcTemplate(commonJdbcTemplate);
		CertifyBankCriteria certifyBankCriteria = Mock.mockCertifyCriteria();
		ContractEntity contractEntity =  this.pisdR226.executeFindByCertifiedBank(certifyBankCriteria);
		Assert.assertNotNull(contractEntity);
	}
}
