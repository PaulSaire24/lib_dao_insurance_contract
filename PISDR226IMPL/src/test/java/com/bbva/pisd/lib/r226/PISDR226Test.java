package com.bbva.pisd.lib.r226;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.domain.jdbc.CommonJdbcTemplate;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import javax.annotation.Resource;

import com.bbva.pisd.dto.contract.constants.PISDConstant;
import com.bbva.pisd.dto.contract.constants.PISDStatus;
import com.bbva.pisd.dto.contract.search.CertifyBankCriteria;
import com.bbva.pisd.dto.contract.search.ReceiptSearchCriteria;
import com.bbva.pisd.dto.insurancedao.constants.PISDColumn;
import com.bbva.pisd.dto.insurancedao.entities.ContractEntity;
import com.bbva.pisd.dto.insurancedao.entities.ReceiptEntity;
import com.bbva.pisd.lib.r226.mock.Mock;
import com.bbva.pisd.lib.r226.pattern.factory.impl.CommonJdbcFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.aop.framework.Advised;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/PISDR226-app.xml",
		"classpath:/META-INF/spring/PISDR226-app-test.xml",
		"classpath:/META-INF/spring/PISDR226-arc.xml",
		"classpath:/META-INF/spring/PISDR226-arc-test.xml" })
public class PISDR226Test {

	@Spy
	private Context context;

	@Resource(name = "pisdR226")
	private PISDR226 pisdR226;

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
	public void executeFindByCertifiedBankTest(){

		CommonJdbcTemplate commonJdbcTemplate = Mockito.mock(CommonJdbcTemplate.class);
		this.pisdR226.executeSetCommonJdbcTemplate(commonJdbcTemplate);
		Map<String, Object>contratos = new HashMap<>();
		contratos.put("00114444111010101010",Mock.mockContract2());
		List<Map<String, Object>> result = new ArrayList<>();
		result.add(contratos);


		Mockito.when(commonJdbcTemplate.queryForList(Mockito.anyString(),Mockito.anyMap())).thenReturn(result);
		CertifyBankCriteria certifyBankCriteria = Mock.mockCertifyCriteria();
		ContractEntity contractEntity =  this.pisdR226.executeFindByCertifiedBank(certifyBankCriteria);
		Assert.assertNotNull(contractEntity);


	}
	@Test
	public void executeUpdateReceiptsPaymentTest(){
		/**
		 *  Context
		 * */
		CommonJdbcTemplate commonJdbcTemplate = Mockito.mock(CommonJdbcTemplate.class);
		this.pisdR226.executeSetCommonJdbcTemplate(commonJdbcTemplate);
		int [] result = {1};
		//Mockito.when(commonJdbcTemplate.queryForMap(Mockito.anyString(), Mockito.anyMap())).thenReturn(map);
		Map<String, Object> [] array = new HashMap[5];
		Mockito.when(commonJdbcTemplate.batchUpdate(Mockito.anyString(), Mockito.any(array.getClass()))).thenReturn(result);

		List<ReceiptEntity> receiptEntities = new ArrayList<>();
		receiptEntities.add(Mock.mockReceipts());
		//contractEntities.add(Mock.mockContract2());
		boolean result2 = this.pisdR226.executeUpdateReceiptsPayment(receiptEntities);
		Assert.assertTrue(result2);
	}
	@Test
	public void executeUpdateContractsPaymentTest(){
		/**
		 *  Context
		 * */
		CommonJdbcTemplate commonJdbcTemplate = Mockito.mock(CommonJdbcTemplate.class);
		this.pisdR226.executeSetCommonJdbcTemplate(commonJdbcTemplate);
		int [] result = {1};
		//Mockito.when(commonJdbcTemplate.queryForMap(Mockito.anyString(), Mockito.anyMap())).thenReturn(map);
		Map<String, Object> [] array = new HashMap[5];
		Mockito.when(commonJdbcTemplate.batchUpdate(Mockito.anyString(), Mockito.any(array.getClass()))).thenReturn(result);

		List<ContractEntity> contractEntities = new ArrayList<>();
		contractEntities.add(Mock.mockContract());
		//contractEntities.add(Mock.mockContract2());
		boolean result2 = this.pisdR226.executeUpdateContractsPayment(contractEntities);
		Assert.assertTrue(result2);
	}
	@Test
	public void executeFindEmptyReceiptByChargeEntityExternTest(){
		/**
		 *  Context
		 * */
		CommonJdbcTemplate commonJdbcTemplate = Mockito.mock(CommonJdbcTemplate.class);
		this.pisdR226.executeSetCommonJdbcTemplate(commonJdbcTemplate);

		ArrayList<Object> result = new ArrayList<>();
		HashMap<String,Object> countRow = new HashMap<>();
		countRow.put(PISDConstant.Pagination.COLUMN_COUNT,0);

 		Mockito.when(commonJdbcTemplate.queryForList(Mockito.anyString(),Mockito.anyMap())).thenReturn(result);
		Mockito.when(commonJdbcTemplate.queryForMap(Mockito.anyString(),Mockito.anyMap())).thenReturn(countRow);

		ReceiptSearchCriteria receiptSearchCriteria = this.getMockReceiptSearchCriteria();

		/**
		 * Ejecución
		 * */

		List<ReceiptEntity> listReceipts = this.pisdR226.executeFindReceiptByChargeEntityExtern(receiptSearchCriteria);
		Assert.assertNotNull(listReceipts);
	}

	@Test
	public void executeFindNoEmptyReceiptByChargeEntityExternTest(){
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

		List<ReceiptEntity> listReceipts = this.pisdR226.executeFindReceiptByChargeEntityExtern(receiptSearchCriteria);
		Assert.assertNotNull(listReceipts);
	}
	
	@Test
	public void executeTest(){
		Assert.assertEquals(0, context.getAdviceList().size());
	}

	private Map<String,Object> getMockMapQueryFindReceiptsSuccessFull(){
		Map<String,Object> mapResponse = new HashMap<>();

		mapResponse.put(PISDColumn.Receipt.FIELD_INSURANCE_CONTRACT_ENTITY_ID, "0011");
		mapResponse.put(PISDColumn.Receipt.FIELD_INSURANCE_CONTRACT_BRANCH_ID, "0172");
		mapResponse.put(PISDColumn.Contract.FIELD_CONTRACT_FIRST_VERFN_DIGIT_ID, "1");
		mapResponse.put(PISDColumn.Contract.FIELD_CONTRACT_SECOND_VERFN_DIGIT_ID, "2");
		mapResponse.put(PISDColumn.Receipt.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID, "4000018548");
		mapResponse.put(PISDColumn.Contract.FIELD_POLICY_ID, "1234567");
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

		ArrayList<String> receiptStatus = new ArrayList<>();
		receiptStatus.add(PISDStatus.RECEIPT_STATUS_TYPE.FAC.getValue());
		receiptStatus.add(PISDStatus.RECEIPT_STATUS_TYPE.IMP.getValue());
		receiptSearchCriteria.setContractStatusId(PISDStatus.CONTRACT_STATUS_ID.FOR.getValue());
		receiptSearchCriteria.setContractPaymentMeansType(PISDStatus.CONTRACT_PAYMENT_MEANS_TYPE.EXT.getValue());
		receiptSearchCriteria.setReceiptStatusSearch(receiptStatus);

		return receiptSearchCriteria;

	}
	
}
