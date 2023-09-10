package com.bbva.pisd.lib.r226.pattern.factory.interfaces;


import com.bbva.elara.library.AbstractLibrary;
import com.bbva.pisd.dto.contract.operation.OperationDTO;

import java.util.List;
import java.util.Map;

public interface BaseDAO {

    Object executeQuery(OperationDTO operationDTO);
    List<Map<String, Object>> executeQueryList(String query, Map<String, Object> parameters);

}
