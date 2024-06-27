package com.bbva.pisd.lib.r226.impl;

import com.bbva.apx.exception.db.NoResultException;
import com.bbva.elara.domain.jdbc.CommonJdbcTemplate;
import com.bbva.pisd.dto.contract.common.ReceiptDTO;
import com.bbva.pisd.dto.contract.search.CertifyBankCriteria;
import com.bbva.pisd.dto.contract.search.ReceiptSearchCriteria;
import com.bbva.pisd.dto.insurancedao.entities.ContractEntity;
import com.bbva.pisd.dto.insurancedao.entities.PaymentPeriodEntity;
import com.bbva.pisd.lib.r226.dao.ContractDAOImpl;
import com.bbva.pisd.lib.r226.dao.PaymentPeriodImpl;
import com.bbva.pisd.lib.r226.interfaces.ContractDAO;
import com.bbva.pisd.lib.r226.interfaces.PaymentPeriodDAO;
import com.bbva.pisd.lib.r226.pattern.factory.DAOFactory;
import com.bbva.pisd.lib.r226.pattern.factory.interfaces.BaseDAO;
import com.bbva.pisd.lib.r226.transfor.list.ReceiptTransformList;
import com.bbva.pisd.lib.r226.transfor.map.ReceiptTransformMap;
import com.bbva.pisd.lib.r226.util.JsonHelper;
import com.bbva.pisd.lib.r226.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The PISDR226Impl class...
 */
public class PISDR226Impl extends PISDR226Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(PISDR226Impl.class);

	private CommonJdbcTemplate commonJdbcTemplate;
	@Override
	public List<ContractEntity> executeFindContractBySearchCriteria(ReceiptSearchCriteria searchCriteria) {
		LOGGER.info("[***] PISDR226Impl executeFindContractBySearchCriteria searchCriteria - {} ", JsonHelper.getInstance().toJsonString(searchCriteria));

		BaseDAO daoFactory = DAOFactory.getDAOFactory(commonJdbcTemplate,jdbcUtils);
		ContractDAO contractDAO = new ContractDAOImpl(daoFactory);
		List<ContractEntity> listContract = contractDAO.findContractBySearchCriteria(searchCriteria);

		LOGGER.info("[***] PISDR226Impl executeFindReceiptByChargeEntityExtern listReceipts count {}", listContract!=null?listContract.size():0);

		return listContract;
	}

	@Override
	public boolean executeUpdateBiometricId(String insuranceContractId, String biometricId, String usuario) {
		LOGGER.info("[***] PISDR226Impl executeUpdateBiometricId - insuranceContractId {} ", insuranceContractId);
		LOGGER.info("[***] PISDR226Impl executeUpdateBiometricId - biometricId {} ",  biometricId);
		LOGGER.info("[***] PISDR226Impl executeUpdateBiometricId - usuario {} ",  usuario);
		BaseDAO baseDAO = DAOFactory.getDAOFactory(commonJdbcTemplate, jdbcUtils);
		ContractDAO contractDAO = new ContractDAOImpl(baseDAO);
		boolean result = contractDAO.updateBiometricId(insuranceContractId,biometricId,usuario);
		LOGGER.info("[***] PISDR226Impl executeUpdateBiometricId - {} ", result);
		return result;
	}

	@Override
	public boolean executeFindByContract(String biometricId) {
		LOGGER.info("[***] PISDR226Impl executeFindByContract - biometricId {} ",  biometricId);
		BaseDAO baseDAO = DAOFactory.getDAOFactory(commonJdbcTemplate, jdbcUtils);
		ContractDAO contractDAO = new ContractDAOImpl(baseDAO);
		boolean result = contractDAO.findByContract(biometricId);
		LOGGER.info("[***] PISDR226Impl executeFindByContract - {} ", result);
		return result;
	}

	//metodo para obtener el detalle de poliza
	@Override
	public List<Map<String, Object>> executeGetRoyalPolicyDetail(String contractNumber) {
		LOGGER.info("***** PISDR226Impl - executeGetRoyalPolicyDetail START  *****  contractNumber: {}", contractNumber);

		Map<String, Object> param = ReceiptTransformMap.mapContractNumber(contractNumber);
		if (CollectionUtils.isEmpty(param)) { return new ArrayList<>(); }

		List<Map<String, Object>> response = new ArrayList<>();
		try {
			response = this.jdbcUtils.queryForList(Properties.QUERY_SELECT_INSRC_CONTRACT_DETAIL.getValue(), param);
		} catch(NoResultException ex) {
			LOGGER.info("PISDR226Impl - executeGetRoyalPolicyDetail - QUERY EMPTY RESULT [PISD.SELECT_INSURANCE_CONTRACT_DETAIL]");
		}
		LOGGER.info("***** PISDR226Impl - executeGetRoyalPolicyDetail END ***** response : {}", response);
		return response;
	}

	//metodo para obtener la lista de recibos
	@Override
	public List<ReceiptDTO> executeGetReceipts(String contractNumber) {
		LOGGER.info("***** PISDR226Impl - executeGetReceipts START  *****  contractNumber: {}", contractNumber);

		List<ReceiptDTO> response = new ArrayList<>();

		Map<String, Object> param = ReceiptTransformMap.mapContractNumber(contractNumber);
		if (CollectionUtils.isEmpty(param)) { return new ArrayList<>(); }

		List<Map<String, Object>> list = new ArrayList<>();
		try {
			list = this.jdbcUtils.queryForList(Properties.QUERY_SELECT_INSRC_RECEIPTS_DETAIL.getValue(), param);
			response = ReceiptTransformList.transformListMapToListReceiptDTO(list);

		} catch(NoResultException ex) {
			LOGGER.info("PISDR226Impl - executeGetReceipts - QUERY EMPTY RESULT [PISD.SELECT_INSURANCE_RECEIPTS_DETAIL]");
		}
		LOGGER.info("***** PISDR226Impl - executeGetReceipts END ***** response : {}", response);

		return response;
	}
	@Override
	public ContractEntity executeFindByCertifiedBank(CertifyBankCriteria certifyBankCriteria) {
		LOGGER.info("[***] PISDR226Impl executeFindByCertifiedBank certifyBankCriteria - {} ", certifyBankCriteria);
		BaseDAO baseDAO = DAOFactory.getDAOFactory(commonJdbcTemplate, jdbcUtils);
		ContractDAO contractDAO = new ContractDAOImpl(baseDAO);
		ContractEntity result= contractDAO.findByCertifiedBank(certifyBankCriteria);
		LOGGER.info("[***] PISDR226Impl executeFindByCertifiedBank result - {} ", result);
		return result;
	}
	@Override
	public ContractEntity executeExistContractByIdAndProductId(String contractId, String productId) {
		LOGGER.info("[***] PISDR226Impl executeExistContractByIdAndProductId - contractId {} ",  contractId);
		LOGGER.info("[***] PISDR226Impl executeExistContractByIdAndProductId - productId {} ",  productId);

		BaseDAO baseDAO = DAOFactory.getDAOFactory(commonJdbcTemplate, jdbcUtils);
		ContractDAO contractDAO = new ContractDAOImpl(baseDAO);
		ContractEntity result = contractDAO.findContractByIdAndProductId(contractId,productId);

		LOGGER.info("[***] PISDR226Impl executeExistContractByIdAndProductId - {} ", result);
		return result;
	}

	@Override
	public boolean executeFindQuotationIfExistInContract(String quotationId) {
		LOGGER.info("[***] PISDR226Impl executeFindQuotationIfExistInContract - quotationId: {} ",  quotationId);
		BaseDAO baseDAO = DAOFactory.getDAOFactory(commonJdbcTemplate, jdbcUtils);
		ContractDAO contractDAO = new ContractDAOImpl(baseDAO);
		boolean result = contractDAO.findQuotationExistInContract(quotationId);
		LOGGER.info("[***] PISDR226Impl executeFindQuotationIfExistInContract - {} ", result);
		return result;
	}

	@Override
	public PaymentPeriodEntity executeFindPaymentPeriodByType(String paymentFrequencyType) {
		LOGGER.info("[***] PISDR226Impl executeFindPaymentPeriodByType - paymentFrequencyType: {} ",  paymentFrequencyType);
		BaseDAO baseDAO = DAOFactory.getDAOFactory(commonJdbcTemplate, jdbcUtils);
		PaymentPeriodDAO paymentPeriodDAO = new PaymentPeriodImpl(baseDAO);
		PaymentPeriodEntity result = paymentPeriodDAO.findPaymentPeriodByType(paymentFrequencyType);
		LOGGER.info("[***] PISDR226Impl executeFindPaymentPeriodByType - {} ", result);
		return result;
	}

	@Override
	public int executeInsertInsuranceContract(Map<String, Object> map) {
		LOGGER.info("[***] PISDR226Impl executeInsertInsuranceContract - START");
		BaseDAO baseDAO = DAOFactory.getDAOFactory(commonJdbcTemplate, jdbcUtils);
		ContractDAO contractDAO = new ContractDAOImpl(baseDAO);
		int result = contractDAO.insertInsuranceContract(map);
		LOGGER.info("[***] PISDR226Impl executeInsertInsuranceContract - result {} ", result);
		return result;
	}

	@Override
	public	ContractEntity executeFindContractById(String contractId){
		LOGGER.info("[***] PISDR226Impl executeFindContractById - START");
		BaseDAO baseDAO = DAOFactory.getDAOFactory(commonJdbcTemplate, jdbcUtils);
		ContractDAO contractDAO = new ContractDAOImpl(baseDAO);
		ContractEntity contract = contractDAO.findContractById(contractId);
		LOGGER.info("[***] PISDR226Impl executeFindContractById - result {} ", contract);
		return contract;
	}


	@Override
	public void executeSetCommonJdbcTemplate(Object commonJdbcTemplate) {
		this.commonJdbcTemplate = (CommonJdbcTemplate) commonJdbcTemplate;
	}


}
