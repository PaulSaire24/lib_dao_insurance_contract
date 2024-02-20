package com.bbva.pisd.lib.r226.util;

import com.bbva.pisd.dto.contract.constants.PISDQueryName;
import com.bbva.pisd.dto.insurancedao.constants.PISDConstant;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FunctionUtils {
    /**
     *  @allParametersNotNull(arguments,keys)
     *  return true -> all parameters save simulation not null
     *  return false -> parameters save simulation is null
     * */
    public static boolean parametersIsValid(Map<String, Object> arguments, String... keys) {
        return Arrays.stream(keys).allMatch(key -> Objects.nonNull(arguments.get(key)));
    }

    public static Map<String, Object>[] convertAsPrimitiveArray(List<Map<String, Object>> list){
        Map<String, Object> [] array = new HashMap[list.size()];
        for(int i = 0; i < list.size(); i++){
            array[i] = list.get(i);
        }
        return array;
    }

    public static String generateQueryCounter(String query){
        int positionFrom = query.indexOf(PISDConstant.Pagination.FROM);
        return PISDQueryName.SQL_COUNT_ROWS.getValue().concat(query.substring(positionFrom));
    }

    public static Integer mapConvertToInteger(String key,Map<String,Object> map){
        String value = Objects.toString(map.get(key), "0");
        return Integer.parseInt(value);
    }

    public static Double mapConvertToDouble(String key,Map<String,Object> map){
        String value = Objects.toString(map.get(key), "0");
        return Double.parseDouble(value);
    }
    public static String convertDatoToString(String key,Map<String,Object> map){
        Object objeto = (map.get(key));
        Date fecha = (Date) objeto;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatoFecha.format(fecha);
    }


}