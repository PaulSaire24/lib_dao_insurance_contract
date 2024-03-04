package com.bbva.pisd.lib.r226.pattern.factory;

import com.bbva.elara.domain.jdbc.CommonJdbcTemplate;
import com.bbva.elara.utility.jdbc.JdbcUtils;
import com.bbva.pisd.lib.r226.pattern.factory.impl.CommonJdbcFactory;
import com.bbva.pisd.lib.r226.pattern.factory.impl.JdbcUtilsFactory;
import com.bbva.pisd.lib.r226.pattern.factory.interfaces.BaseDAO;

import java.util.Objects;

public abstract class DAOFactory {

    private JdbcUtils jdbcUtils;

    public static BaseDAO getDAOFactory(CommonJdbcTemplate commonJdbcTemplate, JdbcUtils jdbcUtils){
           if(Objects.isNull(commonJdbcTemplate)){
               return new JdbcUtilsFactory(jdbcUtils);
           }
           return new CommonJdbcFactory(commonJdbcTemplate);
    }

}
