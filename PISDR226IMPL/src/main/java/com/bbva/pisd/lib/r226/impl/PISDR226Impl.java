package com.bbva.pisd.lib.r226.impl;

import com.bbva.pisd.dto.contract.entity.ContractEntity;
import com.bbva.pisd.dto.contract.entity.ReceiptEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * The PISDR226Impl class...
 */
public class PISDR226Impl extends PISDR226Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(PISDR226Impl.class);

	/**
	 * The execute method...
	 */
	@Override
	public void execute() {
		// TODO - Implementation of business logic
	}

	@Override
	public void executeSetCommonJdbcTemplate(Object commonJdbcTemplate) {

	}

	@Override
	public ContractEntity executeFindByCertifiedBank(String nroCertificadoBanco) {
		return null;
	}

	@Override
	public boolean executeUpdateReceiptsPayment(ReceiptEntity receipt) {
		return false;
	}

	@Override
	public List<ReceiptEntity> executeFindReceiptByChargeEntityExtern() {
		return null;
	}
}
