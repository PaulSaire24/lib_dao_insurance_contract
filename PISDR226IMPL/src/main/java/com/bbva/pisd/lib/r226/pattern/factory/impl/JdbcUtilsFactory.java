package com.bbva.pisd.lib.r226.pattern.factory.impl;

import com.bbva.apx.exception.business.BusinessException;
import com.bbva.apx.exception.db.DuplicateKeyException;
import com.bbva.apx.exception.db.IncorrectResultSizeException;
import com.bbva.apx.exception.db.NoResultException;
import com.bbva.apx.exception.db.TimeoutException;
import com.bbva.elara.domain.jdbc.CommonJdbcTemplate;
import com.bbva.elara.utility.jdbc.JdbcUtils;
import com.bbva.pisd.dto.contract.operation.OperationDTO;
import com.bbva.pisd.lib.r226.pattern.factory.interfaces.BaseDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class JdbcUtilsFactory implements BaseDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDAO.class);

    private JdbcUtils jdbcUtils;


    @Override
    public Object executeQuery(OperationDTO operationDTO) {
        return null;
    }

    @Override
    public List<Map<String, Object>> executeQueryList(String query, Map<String, Object> parameters) {
        return null;
    }

    /**
     * @param jdbcUtils the this.jdbcUtils to set
     */
    public void setJdbcUtils(JdbcUtils jdbcUtils) {
        this.jdbcUtils = jdbcUtils;
    }
}
