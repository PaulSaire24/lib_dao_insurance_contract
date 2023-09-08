package com.bbva.pisd.lib.r226.pattern.factory;

import com.bbva.apx.exception.business.BusinessException;
import com.bbva.apx.exception.db.DuplicateKeyException;
import com.bbva.apx.exception.db.IncorrectResultSizeException;
import com.bbva.apx.exception.db.NoResultException;
import com.bbva.apx.exception.db.TimeoutException;
import com.bbva.elara.domain.jdbc.CommonJdbcTemplate;
import com.bbva.pisd.dto.contract.operation.OperationDTO;
import com.bbva.pisd.lib.r226.pattern.factory.interfaces.BaseDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonJdbcFactory implements BaseDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcUtilsFactory.class);

    private CommonJdbcTemplate commonJdbcTemplate;

    public CommonJdbcFactory(CommonJdbcTemplate commonJdbcTemplate) {
        this.commonJdbcTemplate = commonJdbcTemplate;
    }

    @Override
    public Object executeQuery(OperationDTO operation) {
        Object response = null;
        LOGGER.info("[BaseDAO] - start executeQuery() with Param OperationDTO :: {}", operation);
        try {
            switch (operation.getTypeOperation()) {
                case OperationConstant.Operation.SELECT:
                    if (operation.isForListQuery()) {
                        response = commonJdbcTemplate.queryForList(operation.getQuery(), operation.getParams());
                    } else {
                        response = commonJdbcTemplate.queryForMap(operation.getQuery(), operation.getParams());
                    }
                    break;
                case OperationConstant.Operation.UPDATE:
                    response = commonJdbcTemplate.update(operation.getQuery(), operation.getParams());
                    break;
                case OperationConstant.Operation.BATCH:
                    response = commonJdbcTemplate.batchUpdate(operation.getQuery(), operation.getBatchValues());
                    break;
            }

        } catch(NoResultException ex) {
            LOGGER.info("[BaseDAO] - not found data, query Empty Result to {}", operation.getNameProp());
            this.addAdvice(PISDErrors.QUERY_EMPTY_RESULT.getAdviceCode());
        } catch(DuplicateKeyException ex) {
            this.addAdvice(PISDErrors.ERROR_DUPLICATE_KEY.getAdviceCode());
            throw new BusinessException(PISDErrors.ERROR_DUPLICATE_KEY.getAdviceCode(), false, ex.getMessage());
        } catch (TimeoutException ae){
            this.addAdvice(PISDErrors.ERROR_TIME_OUT.getAdviceCode());
            throw new BusinessException(PISDErrors.ERROR_TIME_OUT.getAdviceCode(), false, ae.getMessage());
        }catch (IncorrectResultSizeException ae){
            this.addAdvice(PISDErrors.ERROR_INCORRECT_RESULT.getAdviceCode());
            throw new BusinessException(PISDErrors.ERROR_INCORRECT_RESULT.getAdviceCode(), false, ae.getMessage());
        }

        LOGGER.info("[BaseDAO] - end executeQuery()");
        return response;
    }
}
