package com.bbva.pisd.lib.r226.pattern.factory;

import com.bbva.elara.domain.jdbc.CommonJdbcTemplate;
import com.bbva.pisd.lib.r226.pattern.factory.interfaces.BaseDAO;

import java.util.Objects;

public abstract class DAOFactory {

    public static BaseDAO getDAOFactory(CommonJdbcTemplate commonJdbcTemplate, String type){
           if(Objects.isNull(commonJdbcTemplate)){
               return new JdbcUtilsFactory();
           }
           return new CommonJdbcFactory(commonJdbcTemplate);
    }

}
