package com.bbva.pisd.lib.r226.pattern.factory;

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

public class JdbcUtilsFactory implements BaseDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDAO.class);

    private JdbcUtils jdbcUtils;

    @Override
    public Object executeQuery(OperationDTO operation) {
        Object response = null;
        LOGGER.info("[BaseDAO] - start executeQuery() with Param OperationDTO.getTypeOperation 897 :: {}", operation.getTypeOperation());
        LOGGER.info("[BaseDAO] - start executeQuery() with Param OperationDTO :: {}", operation);
        try {
            if(operation.getTypeOperation().equals(OperationConstant.Operation.SELECT)){
                if(operation.isForListQuery()){
                    LOGGER.info("[BaseDAO] - start executeQuery.queryForMap.LIST with Param OperationDTO :: {}", operation);
                    response = this.jdbcUtils.queryForList(operation.getNameProp(), operation.getParams());
                }else if(operation.isContainsParameters()){
                    LOGGER.info("[BaseDAO] - start executeQuery.queryForMap.SELECT with Param OperationDTO :: {}", operation);
                    response = this.jdbcUtils.queryForMap(operation.getNameProp(), operation.getParams());
                }else{
                    LOGGER.info("[BaseDAO] - start executeQuery.queryForMap.SELECT not contain Param OperationDTO :: {}", operation);
                    response = this.jdbcUtils.queryForMap(operation.getNameProp());
                }
            }else if(operation.getTypeOperation().equals(OperationConstant.Operation.UPDATE)){
                LOGGER.info("[BaseDAO] - start executeQuery.queryForMap.UPDATE with Param OperationDTO :: {}", operation);
                response = this.jdbcUtils.update(operation.getNameProp(), operation.getParams());
            }else if(operation.getTypeOperation().equals(OperationConstant.Operation.BATCH)){
                LOGGER.info("[BaseDAO] - start executeQuery.queryForMap.BATCH with Param OperationDTO :: {}", operation);
                response = this.jdbcUtils.batchUpdate(operation.getNameProp(), operation.getBatchValues());
            }

        } catch(NoResultException ex) {
            LOGGER.info("[BaseDAO] - not found data, query Empty Result to {}", operation.getNameProp());
            throw new BusinessException(PISDSimulationDAOErrors.QUERY_EMPTY_RESULT.getAdviceCode(), false, ex.getMessage());
        } catch(DuplicateKeyException ex) {
            LOGGER.info("[BaseDAO] - DuplicateKeyException {}", operation.getNameProp());
            throw new BusinessException(PISDSimulationDAOErrors.ERROR_DUPLICATE_KEY.getAdviceCode(), true, ex.getMessage());
        } catch (TimeoutException ae){
            LOGGER.info("[BaseDAO] - TimeoutException {}", operation.getNameProp());
            throw new BusinessException(PISDSimulationDAOErrors.ERROR_TIME_OUT.getAdviceCode(), false, ae.getMessage());
        }catch (IncorrectResultSizeException ae){
            LOGGER.info("[BaseDAO] - TimeoutException {}", operation.getNameProp());
            throw new BusinessException(PISDSimulationDAOErrors.ERROR_INCORRECT_RESULT.getAdviceCode(), false, ae.getMessage());
        }
        LOGGER.info("[BaseDAO] - end executeQuery()");
        return response;
    }

    /**
     * @param jdbcUtils the this.jdbcUtils to set
     */
    public void setJdbcUtils(JdbcUtils jdbcUtils) {
        this.jdbcUtils = jdbcUtils;
    }
}
