package com.bbva.pisd.lib.r226.dao;


import com.bbva.pisd.dto.contract.constants.PISDQueryName;
import com.bbva.pisd.dto.contract.entity.ContractEntity;
import com.bbva.pisd.dto.contract.entity.ReceiptEntity;
import com.bbva.pisd.dto.contract.entity.ReceiptSearchCriteriaDTO;
import com.bbva.pisd.lib.r226.interfaces.ContractDAO;
import com.bbva.pisd.lib.r226.mapper.map.MapperMap;
import com.bbva.pisd.lib.r226.pattern.factory.impl.CommonJdbcFactory;
import com.bbva.pisd.lib.r226.pattern.factory.interfaces.BaseDAO;
import com.bbva.pisd.lib.r226.transfor.list.ReceiptTransforList;
import com.bbva.pisd.lib.r226.transfor.map.ReceiptTransforMap;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class OracleContractDAO implements ContractDAO {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ContractDAO.class);

    private BaseDAO baseDAO;

    public OracleContractDAO(BaseDAO baseDAO){
        this.baseDAO = baseDAO;
    }


    @Override
    public ContractEntity findByCertifiedBank(String nroCertifyBank) {
        return null;
        //return baseDAO.executeQuery(nroCertifyBank);
    }

    @Override
    public boolean updateReceiptsPayment(List<ReceiptEntity> receipts) {
        return false;
    }

    @Override
    public List<ReceiptEntity> findReceiptByChargeEntityExtern(ReceiptSearchCriteriaDTO receiptSearchCriteriaDTO) {
        LOGGER.info("[***] OracleContractDAO executeFindReceiptByChargeEntityExtern - {} ",receiptSearchCriteriaDTO);
        if(this.baseDAO instanceof CommonJdbcFactory){
            List<ReceiptEntity> listReceipts = null;
            List<Map<String, Object>> result;
            Map<String, Object> parameters = ReceiptTransforMap.ReceiptSearchCriteriaTransforMap(receiptSearchCriteriaDTO);
            result = this.baseDAO.executeQueryList(PISDQueryName.SQL_SELECT_RECEIPTS_CHARGE_THIRD.getValue(), parameters);
            LOGGER.info("[***] OracleContractDAO executeFindReceiptByChargeEntityExtern Result - {}", result);
            listReceipts = ReceiptTransforList.mapListTransforListReceiptEntity(result);
            LOGGER.info("[***] OracleContractDAO executeFindReceiptByChargeEntityExtern ResultMapper - {}", listReceipts);
            return listReceipts;
        }
        return null;
    }

}
