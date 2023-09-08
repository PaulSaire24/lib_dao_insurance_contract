package com.bbva.pisd.lib.r226.dao;

import com.bbva.pisd.dto.contract.entity.ContractEntity;
import com.bbva.pisd.lib.r226.interfaces.ContractDAO;
import com.bbva.pisd.lib.r226.pattern.factory.interfaces.BaseDAO;

import java.util.List;

public class OracleContractDAO implements ContractDAO {

    private BaseDAO baseDAO;

    public OracleContractDAO(BaseDAO baseDAO){
        this.baseDAO = baseDAO;
    }


    @Override
    public ContractEntity findByCertifiedBank(String nroCertifyBank) {
        return baseDAO.executeQuery(nroCertifyBank);
    }

    @Override
    public boolean updateReceiptsPayment(List<ReceiptEntity> receipts) {
        return false;
    }

    @Override
    public List<ReceiptEntity> findReceiptByChargeEntityExtern(PaymentSearchCriteriaDTO paymentSearchCriteriaDTO) {
        return null;
    }
}
