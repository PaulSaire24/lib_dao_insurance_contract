package com.bbva.pisd.lib.r226.impl;

import com.bbva.elara.domain.jdbc.CommonJdbcTemplate;
import com.bbva.pisd.dto.contract.search.CertifyBankCriteria;
import com.bbva.pisd.dto.contract.search.ReceiptSearchCriteria;
import com.bbva.pisd.dto.insurancedao.entities.ContractEntity;
import com.bbva.pisd.dto.insurancedao.entities.ReceiptEntity;
import com.bbva.pisd.lib.r226.dao.OracleContractDAO;
import com.bbva.pisd.lib.r226.interfaces.ContractDAO;
import com.bbva.pisd.lib.r226.pattern.factory.DAOFactory;
import com.bbva.pisd.lib.r226.pattern.factory.interfaces.BaseDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * The PISDR226Impl class...
 */
public class PISDR226Impl extends PISDR226Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(PISDR226Impl.class);

	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public boolean executeUpdateReceiptsPayment(List<ReceiptEntity> receipts) {
		return false;
	}

	@Override
	public List<ReceiptEntity> executeFindReceiptByChargeEntityExtern(ReceiptSearchCriteria receiptSearchCriteria) {
		LOGGER.info("[***] PISDR226Impl executeFindReceiptByChargeEntityExtern receiptSearchCriteria - {} ", receiptSearchCriteria);

		BaseDAO daoFactory = DAOFactory.getDAOFactory(commonJdbcTemplate);
		ContractDAO contractDAO = new OracleContractDAO(daoFactory);
		List<ReceiptEntity> listReceipts = contractDAO.findReceiptByChargeEntityExtern(receiptSearchCriteria);

		LOGGER.info("[***] PISDR226Impl executeFindReceiptByChargeEntityExtern listReceipts count {}", listReceipts!=null?listReceipts.size():0);

		return listReceipts;
	}


	/**
	 * executeSetCommonJdbcTemplate
	 * */
	@Override
	public void executeSetCommonJdbcTemplate(Object commonJdbcTemplate) {
		this.commonJdbcTemplate = (CommonJdbcTemplate) commonJdbcTemplate;
	}

	@Override
	public ContractEntity executeFindByCertifiedBank(CertifyBankCriteria certifyBankCriteria) {
		BaseDAO daoFactory = DAOFactory.getDAOFactory(commonJdbcTemplate);
		ContractDAO oracleContractDAO = new OracleContractDAO(daoFactory);
		return oracleContractDAO.findByCertifiedBank(certifyBankCriteria);
	}
}
