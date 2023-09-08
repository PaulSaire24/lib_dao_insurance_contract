package com.bbva.pisd.lib.r226.pattern.factory.interfaces;

import com.bbva.pisd.dto.contract.operation.OperationDTO;

public interface BaseDAO {

    Object executeQuery(OperationDTO operationDTO);

}
