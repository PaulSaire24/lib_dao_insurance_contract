package com.bbva.pisd.lib.r226.util;

import com.bbva.pisd.dto.contract.constants.PISDConstant;
import com.bbva.pisd.dto.contract.constants.PISDQueryName;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class FunctionUtils {
    /**
     *  @allParametersNotNull(arguments,keys)
     *  return true -> all parameters save simulation not null
     *  return false -> parameters save simulation is null
     * */
    public static boolean parametersIsValid(Map<String, Object> arguments, String... keys) {
        return Arrays.stream(keys).allMatch(key -> Objects.nonNull(arguments.get(key)));
    }

    public static String generateQueryCounter(String query){
        int positionFrom = query.indexOf(PISDConstant.Pagination.FROM);
        return PISDQueryName.SQL_COUNT_ROWS.getValue().concat(query.substring(positionFrom));
    }
}