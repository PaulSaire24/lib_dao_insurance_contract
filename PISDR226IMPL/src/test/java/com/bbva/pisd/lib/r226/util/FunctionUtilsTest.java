package com.bbva.pisd.lib.r226.util;


import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.text.SimpleDateFormat;
import java.util.*;

public class FunctionUtilsTest {

    @Test
    public void executeParametersIsValidTest(){
        Map<String, Object> argumentos = new HashMap<>();
        argumentos.put("nombre", "Juan");
        argumentos.put("edad", 30);
        argumentos.put("ciudad", null);

        boolean valid = FunctionUtils.parametersIsValid(argumentos, "nombre", "edad");
        Assert.assertTrue(valid);
        boolean invalid = FunctionUtils.parametersIsValid(argumentos, "nombre", "edad", "ciudad");
        Assert.assertFalse(invalid);
    }

    @Test
    public void executeConvertAsPrimitiveArrayTest(){
        List<Map<String, Object>> lista = new ArrayList<>();

        Map<String, Object> mapa1 = new HashMap<>();
        mapa1.put("nombre", "Juan");
        mapa1.put("edad", 30);
        lista.add(mapa1);

        Map<String, Object> mapa2 = new HashMap<>();
        mapa2.put("nombre", "María");
        mapa2.put("edad", 25);
        lista.add(mapa2);

        Map<String, Object>[] arreglo = FunctionUtils.convertAsPrimitiveArray(lista);
        Assert.assertNotNull(arreglo);
        Assert.assertEquals(lista.size(), arreglo.length);
        for (int i = 0; i < lista.size(); i++) {
            Assert.assertEquals(lista.get(i), arreglo[i]);
        }
    }
    @Test
    public void testConvertDatoToString() {
        String key = "fecha";
        Date fechaActual = new Date();
        Map<String, Object> map = new HashMap<>();
        map.put(key, fechaActual);

        String resultado = FunctionUtils.convertDatoToString(key, map);

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = formatoFecha.format(fechaActual);
        Assert.assertEquals(fechaFormateada, resultado);
    }
}