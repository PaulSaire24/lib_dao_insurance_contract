package com.bbva.pisd.lib.r226.pattern.factory;

import com.bbva.elara.domain.jdbc.CommonJdbcTemplate;
import com.bbva.pisd.lib.r226.dao.BaseDAOV;

import java.util.Objects;

public abstract class DAOFactory {

    public abstract BaseDAO getBaseDao();

    public static DAOFactory getDAOFactory(CommonJdbcTemplate commonJdbcTemplate){
           if(Objects.isNull(commonJdbcTemplate)){

           }

    }

}
