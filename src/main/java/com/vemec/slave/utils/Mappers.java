package com.vemec.slave.utils;

import com.vemec.slave.models.reporte.Reporte;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class Mappers {

    public static Reporte mapToReporte(Map<String, Object> payload, Reporte r) throws Exception {

        if (payload.get("presionMaxima") != null) {
            double val = ((Number) payload.get("presionMaxima")).doubleValue();
            r.setPresionMaxima(val);
        }

        if (payload.get("presionMinima") != null) {
            double val = ((Number) payload.get("presionMinima")).doubleValue();
            r.setPresionMinima(val);
        }

        if (payload.get("volGas") != null) {
            double val = ((Number) payload.get("volGas")).doubleValue();
            r.setVolGas(val);

        }

        if (payload.get("frecGas") != null) {
            double val = ((Number) payload.get("frecGas")).doubleValue();
            r.setFrecGas(val);

        }

        if (payload.get("mezcla") != null) {
            double val = ((Number) payload.get("mezcla")).doubleValue();
            r.setMezcla(val);

        }

        if (payload.get("humedadAire") != null) {
            double val = ((Number) payload.get("humedadAire")).doubleValue();
            r.setHumedadAire(val);

        }

        if (payload.get("tempEntrada") != null) {
            double val = ((Number) payload.get("tempEntrada")).doubleValue();
            r.setTempEntrada(val);

        }

        if (payload.get("tempSalida") != null) {
            double val = ((Number) payload.get("tempSalida")).doubleValue();
            r.setTempSalida(val);

        }

        if (payload.get("presionEntrada") != null) {
            double val = ((Number) payload.get("presionEntrada")).doubleValue();
            r.setPresionEntrada(val);

        }

        if (payload.get("presionSalida") != null) {
            double val = ((Number) payload.get("presionSalida")).doubleValue();
            r.setPresionSalida(val);
        }


        if (payload.get("time") != null) {
            try {
                r.setTime(Utils.parseToSqldate((String) payload.get("time")));
            } catch (Exception e) {
                throw e;
            }
        }

        if (payload.get("cedula") != null) {
            r.setCedula((Integer) payload.get("cedula"));
        }

        if (payload.get("unidadPresion") != null) {
            r.setUnidadPresion((String) payload.get("unidadPresion"));
        }

        if (payload.get("unidadFrecuencia") != null) {
            r.setUnidadFrecuencia((String) payload.get("unidadFrecuencia"));
        }

        if (payload.get("unidadVolumen") != null) {
            r.setUnidadVolumen((String) payload.get("unidadVolumen"));
        }

        if (payload.get("unidadTemp") != null) {
            r.setUnidadTemp((String) payload.get("unidadTemp"));
        }

        if (payload.get("unidadHumedad") != null) {
            r.setUnidadHumedad((String) payload.get("unidadHumedad"));
        }

        if (payload.get("bateria") != null){
            r.setBateria((Boolean) payload.get("bateria"));
            r.setNivelBateria((Integer) payload.get("nivelBateria"));
        }

        if (payload.get("ppm") != null){
            r.setPpm((Integer) payload.get("ppm"));
        }

        return r;
    }
}
