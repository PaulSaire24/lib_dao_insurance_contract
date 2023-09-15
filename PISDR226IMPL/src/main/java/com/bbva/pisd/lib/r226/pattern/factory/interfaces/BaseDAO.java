package com.bbva.pisd.lib.r226.pattern.factory.interfaces;


import com.bbva.pisd.dto.insurancedao.operation.Operation;

import java.util.List;
import java.util.Map;

public interface BaseDAO {

    Object executeQuery(Operation operation);
    List<Map<String, Object>> executeQueryListPagination(Map<String, Object> conditions,String query);

}
