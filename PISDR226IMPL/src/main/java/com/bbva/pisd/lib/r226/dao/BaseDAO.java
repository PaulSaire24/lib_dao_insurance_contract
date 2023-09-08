package com.bbva.pisd.lib.r226.dao;

import com.bbva.apx.exception.business.BusinessException;
import com.bbva.apx.exception.db.DuplicateKeyException;
import com.bbva.apx.exception.db.IncorrectResultSizeException;
import com.bbva.apx.exception.db.NoResultException;
import com.bbva.apx.exception.db.TimeoutException;
import com.bbva.elara.domain.jdbc.CommonJdbcTemplate;
import com.bbva.elara.library.AbstractLibrary;
import com.bbva.pisd.dto.contract.constants.PISDErrors;
import com.bbva.pisd.dto.contract.operation.OperationConstants;
import com.bbva.pisd.dto.contract.operation.OperationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class BaseDAO extends AbstractLibrary {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDAO.class);
    private CommonJdbcTemplate commonJdbcTemplate;

    public BaseDAO(CommonJdbcTemplate commonJdbcTemplate) {
        this.commonJdbcTemplate = commonJdbcTemplate;
    }

    public Object executeQuery(OperationDTO operation) {
        Object response = null;
        LOGGER.info("[BaseDAO] - start executeQuery() with Param OperationDTO.getTypeOperation 897 :: {}", operation.getTypeOperation());
        LOGGER.info("[BaseDAO] - start executeQuery() with Param OperationDTO :: {}", operation);
        try {
            if(operation.getTypeOperation().equals(OperationConstants.Operation.SELECT)){
                if(operation.isForListQuery()){
                    LOGGER.info("[BaseDAO] - start executeQuery.queryForMap.LIST with Param OperationDTO :: {}", operation);
                    response = this.commonJdbcTemplate.queryForList(operation.getNameProp(), operation.getParams());
                }else {
                    LOGGER.info("[BaseDAO] - start executeQuery.queryForMap.SELECT with Param OperationDTO :: {}", operation);
                    response = this.commonJdbcTemplate.queryForMap(operation.getNameProp(), operation.getParams());
                }
            }else if(operation.getTypeOperation().equals(OperationConstants.Operation.UPDATE)){
                LOGGER.info("[BaseDAO] - start executeQuery.queryForMap.UPDATE with Param OperationDTO :: {}", operation);
                response = this.commonJdbcTemplate.update(operation.getNameProp(), operation.getParams());
            }else if(operation.getTypeOperation().equals(OperationConstants.Operation.BATCH)){
                LOGGER.info("[BaseDAO] - start executeQuery.queryForMap.BATCH with Param OperationDTO :: {}", operation);
                response = this.commonJdbcTemplate.batchUpdate(operation.getNameProp(), operation.getBatchValues());
            }

        } catch(NoResultException ex) {
            LOGGER.info("[BaseDAO] - not found data, query Empty Result to {}", operation.getNameProp());
            throw new BusinessException(PISDErrors.QUERY_EMPTY_RESULT.getAdviceCode(), false, ex.getMessage());
        } catch(DuplicateKeyException ex) {
            LOGGER.info("[BaseDAO] - DuplicateKeyException {}", operation.getNameProp());
            throw new BusinessException(PISDErrors.ERROR_DUPLICATE_KEY.getAdviceCode(), true, ex.getMessage());
        } catch (TimeoutException ae){
            LOGGER.info("[BaseDAO] - TimeoutException {}", operation.getNameProp());
            throw new BusinessException(PISDErrors.ERROR_TIME_OUT.getAdviceCode(), false, ae.getMessage());
        }catch (IncorrectResultSizeException ae){
            LOGGER.info("[BaseDAO] - TimeoutException {}", operation.getNameProp());
            throw new BusinessException(PISDErrors.ERROR_INCORRECT_RESULT.getAdviceCode(), false, ae.getMessage());
        }
        LOGGER.info("[BaseDAO] - end executeQuery()");
        return response;
    }

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

    public List<Map<String, Object>> executeQueryListPagination(String query, Map<String, Object> parameters, int paginationKey, int pageSize) {
        List<Map<String, Object>> response = null;
        LOGGER.info("[PISDR201Impl] - executeQueryListPagination() :: Start - with Query : {}, (key, pageSize):{}", "", paginationKey+ ","+ pageSize);
        int firstRow = paginationKey * pageSize + 1;
        try {
            final String sql = this.parseQueryWithPagination(query, firstRow, pageSize);

            response = commonJdbcTemplate.queryForList(sql, parameters);
        } catch(NoResultException ex) {
            LOGGER.info("executeQueryListPagination() - not found data, query Empty Result to {}", query);
            this.addAdvice(PISDErrors.QUERY_EMPTY_RESULT.getAdviceCode());
        }
        LOGGER.info("executeQueryListPagination() :: End");
        return response;
    }

    private String parseQueryWithPagination(final String query, int firstRow, int pageSize) {
        LOGGER.info("Getting query");
        final String sql = insertPagination(query, firstRow, pageSize);

        LOGGER.info("Getted query" + " ,with SQL: " + sql);
        return sql;
    }

    private String insertPagination(String parseQuery, int firstRow, int pageSize) {
        StringBuilder builder = new StringBuilder();

        builder.append("SELECT * FROM ( SELECT a.*, ROWNUM rnum FROM ( ");
        builder.append(parseQuery);
        builder.append(" ) a WHERE ROWNUM <=");
        builder.append(firstRow + pageSize - 1);
        builder.append(") WHERE rnum >=");
        builder.append(firstRow);

        return builder.toString();
    }

    public void setCommonJdbcTemplate(CommonJdbcTemplate commonJdbcTemplate) {
        this.commonJdbcTemplate = commonJdbcTemplate;
    }
}
