package com.bbva.pisd.lib.r226.pattern.factory.impl;

import com.bbva.apx.exception.business.BusinessException;
import com.bbva.apx.exception.db.DuplicateKeyException;
import com.bbva.apx.exception.db.IncorrectResultSizeException;
import com.bbva.apx.exception.db.NoResultException;
import com.bbva.apx.exception.db.TimeoutException;
import com.bbva.elara.utility.jdbc.JdbcUtils;
import com.bbva.pisd.dto.contract.constants.PISDErrors;
import com.bbva.pisd.dto.insurancedao.operation.Operation;
import com.bbva.pisd.dto.insurancedao.operation.OperationConstants;
import com.bbva.pisd.lib.r226.pattern.factory.interfaces.BaseDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class JdbcUtilsFactory implements BaseDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDAO.class);

    private JdbcUtils jdbcUtils;


    @Override
    public Object executeQuery(Operation operation) {

        Object response = null;
        LOGGER.info("[BaseDAO] - start executeQuery() with Param Operation.getTypeOperation 897 :: {}", operation.getTypeOperation());
        LOGGER.info("[BaseDAO] - start executeQuery() with Param Operation :: {}", operation);
        try {
            if(operation.getTypeOperation().equals(OperationConstants.Operation.SELECT)){
                if(operation.isForListQuery()) {
                    LOGGER.info("[BaseDAO] - start executeQuery.queryForMap.LIST with Param Operation :: {}", operation);
                    response = this.jdbcUtils.queryForList(operation.getNameProp(), operation.getParams());
                }else{
                    LOGGER.info("[BaseDAO] - start executeQuery.queryForMap.SELECT not contain Param Operation :: {}", operation);
                    response = this.jdbcUtils.queryForMap(operation.getNameProp());
                }
            }else if(operation.getTypeOperation().equals(OperationConstants.Operation.UPDATE)){
                LOGGER.info("[BaseDAO] - start executeQuery.queryForMap.UPDATE with Param OperationDTO :: {}", operation);
                response = this.jdbcUtils.update(operation.getNameProp(), operation.getParams());
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
        LOGGER.info("[BaseDAO] - end executeQuery()");
        return response;
    }

    @Override
    public List<Map<String, Object>> executeQueryListPagination(Map<String, Object> conditions, String query) {
        return null;
    }


    /**
     * @param jdbcUtils the this.jdbcUtils to set
     */
    public JdbcUtilsFactory(JdbcUtils jdbcUtils) {
        this.jdbcUtils = jdbcUtils;
    }
}
