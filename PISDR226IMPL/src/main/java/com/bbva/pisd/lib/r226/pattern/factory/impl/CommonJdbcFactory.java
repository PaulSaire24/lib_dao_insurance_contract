package com.bbva.pisd.lib.r226.pattern.factory.impl;

import com.bbva.apx.exception.business.BusinessException;
import com.bbva.apx.exception.db.DuplicateKeyException;
import com.bbva.apx.exception.db.IncorrectResultSizeException;
import com.bbva.apx.exception.db.NoResultException;
import com.bbva.apx.exception.db.TimeoutException;
import com.bbva.elara.domain.jdbc.CommonJdbcTemplate;
import com.bbva.pisd.dto.contract.constants.PISDConstant;
import com.bbva.pisd.dto.contract.constants.PISDErrors;
import com.bbva.pisd.dto.insurancedao.operation.Operation;
import com.bbva.pisd.dto.insurancedao.operation.OperationConstants;
import com.bbva.pisd.lib.r226.pattern.factory.interfaces.BaseDAO;
import com.bbva.pisd.lib.r226.util.FunctionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommonJdbcFactory  implements BaseDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcUtilsFactory.class);

    private CommonJdbcTemplate commonJdbcTemplate;

    public CommonJdbcFactory(CommonJdbcTemplate commonJdbcTemplate) {
        this.commonJdbcTemplate = commonJdbcTemplate;
    }


    @Override
    public Object executeQuery(Operation operation) {
        Object response = null;
        LOGGER.info("[***] CommonJdbcFactory - start executeQuery() with Param OperationDTO :: {}", operation);
        try {
            if(operation.getTypeOperation().equals(OperationConstants.Operation.SELECT)){
                if(operation.isForListQuery()){
                    response = commonJdbcTemplate.queryForList(operation.getQuery(), operation.getParams());
                }else {
                    response = commonJdbcTemplate.queryForMap(operation.getQuery(), operation.getParams());
                }
            }else if(operation.getTypeOperation().equals(OperationConstants.Operation.UPDATE)){
                response = commonJdbcTemplate.update(operation.getQuery(), operation.getParams());
            }else if(operation.getTypeOperation().equals(OperationConstants.Operation.BATCH)){
                response = commonJdbcTemplate.batchUpdate(operation.getQuery(), operation.getBatchValues());
            }

        } catch(NoResultException ex) {
            LOGGER.info("[***] CommonJdbcFactory - not found data, query Empty Result to {}", operation.getNameProp());
            throw new BusinessException(PISDErrors.QUERY_EMPTY_RESULT.getAdviceCode(), false, ex.getMessage());
        } catch(DuplicateKeyException ex) {
            throw new BusinessException(PISDErrors.ERROR_DUPLICATE_KEY.getAdviceCode(), false, ex.getMessage());
        } catch (TimeoutException ae){
            throw new BusinessException(PISDErrors.ERROR_TIME_OUT.getAdviceCode(), false, ae.getMessage());
        }catch (IncorrectResultSizeException ae){
            throw new BusinessException(PISDErrors.ERROR_INCORRECT_RESULT.getAdviceCode(), false, ae.getMessage());
        }
        LOGGER.info("[***] CommonJdbcFactory - end executeQuery()");
        return response;
    }

    @Override
    public List<Map<String, Object>> executeQueryListPagination(Map<String, Object> conditions,String query) {
        List<Map<String, Object>> result = new ArrayList<>();
        Long countResult = this.countRowsOfQuery(conditions,query);
        if (countResult > PISDConstant.Pagination.PAGINATION) {
            long countPaginates = countResult / PISDConstant.Pagination.PAGINATION;
            long mod            = countResult % PISDConstant.Pagination.PAGINATION;
            countPaginates = countPaginates + (mod > 0 ? 1 : 0);
            for (int i = 0; i < countPaginates; i++) {
                result.addAll(this.executeQueryPagination(query, conditions, i, PISDConstant.Pagination.PAGINATION));
            }
        } else {
            Operation operation = Operation.Builder.an()
                    .withQuery(query)
                    .withTypeOperation(OperationConstants.Operation.SELECT).withIsForListQuery(true)
                    .withParams(conditions).build();
            result = (List<Map<String, Object>>) this.executeQuery(operation);
            LOGGER.info("[***] OracleContractDAO executeFindReceiptByChargeEntityExtern Result - {}", result);
        }
        return result;
    }

    private Long countRowsOfQuery(Map<String, Object> conditions, String queryInExecution) {
        LOGGER.info("[***] OracleContractDAO executeQueryFindReceiptsCount [ conditions - {} ] - [ queryInExecution - {}]", conditions, queryInExecution);
        Long countResult = 0l;
        Operation operationInput = Operation.Builder.an()
                .withQuery(FunctionUtils.generateQueryCounter(queryInExecution))
                .withTypeOperation(OperationConstants.Operation.SELECT).withIsForListQuery(false)
                .withParams(conditions).build();
        Map<String, Object> map = (Map<String, Object>) this.executeQuery(operationInput);
        countResult = Long.valueOf(map.get(PISDConstant.Pagination.COLUMN_COUNT).toString());
        LOGGER.info("[***] OracleContractDAO executeQueryFindReceiptsCount countResult - {}", countResult);
        return countResult;
    }

    private List<Map<String, Object>> executeQueryPagination(String query, Map<String, Object> parameters, int paginationKey, int pageSize) {
        List<Map<String, Object>> response = null;
        LOGGER.info("[PISDR201Impl] - executeQueryListPagination() :: Start - with Query : {}, (key, pageSize):{}", "", paginationKey+ ","+ pageSize);
        int firstRow = paginationKey * pageSize + 1;
        try {
            final String sql = this.parseQueryWithPagination(query, firstRow, pageSize);
            response = commonJdbcTemplate.queryForList(sql, parameters);
        } catch(NoResultException ex) {
            LOGGER.info("executeQueryListPagination() - not found data, query Empty Result to {}", query);
            throw new BusinessException(PISDErrors.QUERY_EMPTY_RESULT.getAdviceCode(), false, ex.getMessage());
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
}
