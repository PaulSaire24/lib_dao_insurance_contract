package com.bbva.pisd.lib.r226.impl;

import com.bbva.elara.domain.jdbc.CommonJdbcTemplate;
import com.bbva.pisd.dto.contract.search.ReceiptSearchCriteria;
import com.bbva.pisd.dto.insurancedao.entities.ContractEntity;
import com.bbva.pisd.lib.r226.dao.OracleContractDAO;
import com.bbva.pisd.lib.r226.interfaces.ContractDAO;
import com.bbva.pisd.lib.r226.pattern.factory.DAOFactory;
import com.bbva.pisd.lib.r226.pattern.factory.interfaces.BaseDAO;
import com.bbva.pisd.lib.r226.util.JsonHelper;
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
	public List<ContractEntity> executeFindContractBySearchCriteria(ReceiptSearchCriteria searchCriteria) {
		LOGGER.info("[***] PISDR226Impl executeFindContractBySearchCriteria searchCriteria - {} ", JsonHelper.getInstance().toJsonString(searchCriteria));

		BaseDAO daoFactory = DAOFactory.getDAOFactory(commonJdbcTemplate);
		ContractDAO contractDAO = new OracleContractDAO(daoFactory);
		List<ContractEntity> listContract = contractDAO.findContractBySearchCriteria(searchCriteria);

		LOGGER.info("[***] PISDR226Impl executeFindReceiptByChargeEntityExtern listReceipts count {}", listContract!=null?listContract.size():0);

		return listContract;
	}
	/**
	 * executeSetCommonJdbcTemplate
	 * */
	@Override
	public void executeSetCommonJdbcTemplate(Object commonJdbcTemplate) {
		this.commonJdbcTemplate = (CommonJdbcTemplate) commonJdbcTemplate;
	}
}
