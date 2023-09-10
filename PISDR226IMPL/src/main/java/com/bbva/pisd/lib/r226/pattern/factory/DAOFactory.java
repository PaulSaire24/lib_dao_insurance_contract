package com.bbva.pisd.lib.r226.pattern.factory;

import com.bbva.elara.domain.jdbc.CommonJdbcTemplate;
import com.bbva.pisd.lib.r226.pattern.factory.impl.CommonJdbcFactory;
import com.bbva.pisd.lib.r226.pattern.factory.impl.JdbcUtilsFactory;
import com.bbva.pisd.lib.r226.pattern.factory.interfaces.BaseDAO;

import java.util.Objects;

public abstract class DAOFactory {

    public static BaseDAO getDAOFactory(CommonJdbcTemplate commonJdbcTemplate){
           if(Objects.isNull(commonJdbcTemplate)){
               return new JdbcUtilsFactory();
           }
           return new CommonJdbcFactory(commonJdbcTemplate);
    }

}
