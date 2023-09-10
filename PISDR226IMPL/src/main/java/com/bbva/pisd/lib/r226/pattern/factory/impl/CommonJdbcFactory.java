package com.bbva.pisd.lib.r226.pattern.factory.impl;

import com.bbva.apx.exception.business.BusinessException;
import com.bbva.apx.exception.db.DuplicateKeyException;
import com.bbva.apx.exception.db.IncorrectResultSizeException;
import com.bbva.apx.exception.db.NoResultException;
import com.bbva.apx.exception.db.TimeoutException;
import com.bbva.elara.domain.jdbc.CommonJdbcTemplate;
import com.bbva.elara.library.AbstractLibrary;
import com.bbva.pisd.dto.contract.constants.PISDErrors;
import com.bbva.pisd.dto.contract.operation.OperationDTO;
import com.bbva.pisd.lib.r226.pattern.factory.interfaces.BaseDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class CommonJdbcFactory extends AbstractLibrary implements BaseDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcUtilsFactory.class);

    private CommonJdbcTemplate commonJdbcTemplate;

    public CommonJdbcFactory(CommonJdbcTemplate commonJdbcTemplate) {
        this.commonJdbcTemplate = commonJdbcTemplate;
    }


    @Override
    public Object executeQuery(OperationDTO operationDTO) {
        return null;
    }

    @Override
    public List<Map<String, Object>> executeQueryList(String query, Map<String, Object> parameters) {
        List<Map<String, Object>> response = null;

        LOGGER.info("[BaseDAO] - start executeQueryList() with Param parameters :: {}", parameters);
        try {
            response = commonJdbcTemplate.queryForList(query, parameters);
        } catch(NoResultException ex) {
            LOGGER.info("executeQueryList() - not found data, query Empty Result to {}", query);
            this.addAdvice(PISDErrors.QUERY_EMPTY_RESULT.getAdviceCode());
        }

        LOGGER.info("[BaseDAO] - end executeQueryList()");
        return response;
    }
}
