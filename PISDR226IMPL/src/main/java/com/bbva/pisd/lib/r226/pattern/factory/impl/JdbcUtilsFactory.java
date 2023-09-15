package com.bbva.pisd.lib.r226.pattern.factory.impl;

import com.bbva.elara.utility.jdbc.JdbcUtils;
import com.bbva.pisd.dto.insurancedao.operation.Operation;
import com.bbva.pisd.lib.r226.pattern.factory.interfaces.BaseDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class JdbcUtilsFactory implements BaseDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDAO.class);

    private JdbcUtils jdbcUtils;


    @Override
    public Object executeQuery(Operation operationDTO) {
        return null;
    }

    @Override
    public List<Map<String, Object>> executeQueryListPagination(Map<String, Object> conditions, String query) {
        return null;
    }


    /**
     * @param jdbcUtils the this.jdbcUtils to set
     */
    public void setJdbcUtils(JdbcUtils jdbcUtils) {
        this.jdbcUtils = jdbcUtils;
    }
}
