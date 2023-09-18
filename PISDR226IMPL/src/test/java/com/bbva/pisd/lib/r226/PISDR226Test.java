package com.bbva.pisd.lib.r226;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.domain.jdbc.CommonJdbcTemplate;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import javax.annotation.Resource;

import com.bbva.pisd.dto.contract.constants.PISDConstant;
import com.bbva.pisd.dto.contract.search.ReceiptSearchCriteria;
import com.bbva.pisd.dto.insurancedao.entities.ReceiptEntity;
import com.bbva.pisd.lib.r226.pattern.factory.impl.CommonJdbcFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock.*;
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
		//Mockito.when(commonJdbcFactory..thenReturn(result);
		ReceiptSearchCriteria receiptSearchCriteria = new ReceiptSearchCriteria();
		receiptSearchCriteria.setContractStatusId("FOR");
		receiptSearchCriteria.setContractPaymentMeansType("EXT");
		ArrayList<String> receiptStatus = new ArrayList<>();
		receiptStatus.add("FAC");
		receiptStatus.add("IMP");
		receiptSearchCriteria.setReceiptStatusSearch(receiptStatus);

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
		Map<String, Object> receipt = new HashMap<>();
		receipt.put("INSURANCE_CONTRACT_ENTITY_ID","0011");
		receipt.put("INSURANCE_CONTRACT_BRANCH_ID","0172");
		receipt.put("CONTRACT_FIRST_VERFN_DIGIT_ID","1");
		receipt.put("CONTRACT_SECOND_VERFN_DIGIT_ID","2");
		receipt.put("INSRC_CONTRACT_INT_ACCOUNT_ID","4000018548");
		receipt.put("POLICY_ID","1234567");
		receipt.put("CUSTOMER_ID","12345678");
		receipt.put("INSURANCE_MODALITY_TYPE","1");
		receipt.put("CONTRACT_STATUS_ID","FOR");
		receipt.put("POLICY_RECEIPT_ID", 1);
		receipt.put("INSURANCE_COMPANY_RECEIPT_ID","123456");
		receipt.put("PREMIUM_PAYMENT_RECEIPT_AMOUNT", 123.0);
		receipt.put("CURRENCY_ID","USD");
		receipt.put("RECEIPT_STATUS_TYPE","FAC");

		List<Map<String, Object>> result = new ArrayList<>();
		result.add(receipt);

		CommonJdbcTemplate commonJdbcTemplate = Mockito.mock(CommonJdbcTemplate.class);
		this.pisdR226.executeSetCommonJdbcTemplate(commonJdbcTemplate);
		HashMap<String,Object> countRow = new HashMap<>();
		countRow.put(PISDConstant.Pagination.COLUMN_COUNT,5001);
		Mockito.when(commonJdbcTemplate.queryForList(Mockito.anyString(),Mockito.anyMap())).thenReturn(result);
		Mockito.when(commonJdbcTemplate.queryForMap(Mockito.anyString(),Mockito.anyMap())).thenReturn(countRow);
		//Mockito.when(commonJdbcFactory..thenReturn(result);
		ReceiptSearchCriteria receiptSearchCriteria = new ReceiptSearchCriteria();
		receiptSearchCriteria.setContractStatusId("FOR");
		receiptSearchCriteria.setContractPaymentMeansType("EXT");
		ArrayList<String> receiptStatus = new ArrayList<>();
		receiptStatus.add("FAC");
		receiptStatus.add("IMP");
		receiptSearchCriteria.setReceiptStatusSearch(receiptStatus);

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
	
}
