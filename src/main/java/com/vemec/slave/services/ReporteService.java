package com.vemec.slave.services;


import com.vemec.slave.constant.Alerta;
import com.vemec.slave.controllers.WebSocketController;
import com.vemec.slave.models.reporte.Reporte;
import com.vemec.slave.models.reporte.ReporteRepository;
import com.vemec.slave.models.reporte.ReporteConfig;
import com.vemec.slave.models.reporte.ReporteConfigRepository;
import com.vemec.slave.utils.Mappers;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vemec.slave.utils.Utils;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;
    @Autowired
    private ReporteConfigRepository reporteConfigRepository;

    @Transactional
    public Reporte addNew(Map<String, Object> payload) throws Exception {
        try {
            Reporte r = new Reporte();
            r = Mappers.mapToReporte(payload, r);

            ReporteConfig rc = reporteConfigRepository.findByCedula(r.getCedula());
            Map<String, Object> data = Utils.evaluarUrgencia(r.getPpm(), r.getNivelBateria());

            switch (data.get("mensaje").toString()) {
                case "pulsaciones":
                    r.setAlerta(Alerta.PULSACIONES);
                    break;
                case "bateria":
                    r.setAlerta(Alerta.BATERIA);
                    break;
                case "pulsaciones-bateria":
                    r.setAlerta(Alerta.PULSACIONES_BATERIA);
                    break;
                case "estable":
                    r.setAlerta(Alerta.ESTABLE);
                    break;
            }

            if (rc == null) {
                rc = new ReporteConfig();
                rc.setCedula(r.getCedula());
                rc.setCronometro(r.getTime());
                rc.setTimer((Integer) data.get("tiempo"));
                reporteConfigRepository.save(rc);
            }
            if (rc.getTimer() != ((Integer) data.get("tiempo"))) {
                rc.setTimer((Integer) data.get("tiempo"));
                reporteConfigRepository.save(rc);
            }
            reporteRepository.save(r);

            if (data.get("mensaje") == "bateria" || data.get("mensaje") == "pulsaciones") {
                sendMaster(r, rc, (String) data.get("mensaje"));
            } else if (Utils.checkTimer(rc.getCronometro(), rc.getTimer(), r)) {
                sendMaster(r, rc, (String) data.get("mensaje"));
            }
            // send data to local monitor
            WebSocketController.sendWebSocketUpdate(r);
            return r;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public void sendMaster(Reporte rep, ReporteConfig repConfig, String mensaje) {

        new Thread(() -> {
            //Do whatever
            repConfig.setCronometro(rep.getTime());
            reporteConfigRepository.save(repConfig);
            if (repConfig.getTimer() != 1) {
                Double promPE = reporteRepository.promPresionEntrada(repConfig.getCedula(), rep.getTime());
                Double promPS = reporteRepository.promPresionSalida(repConfig.getCedula(), rep.getTime());
                Double promPMax = reporteRepository.promPresionMaxima(repConfig.getCedula(), rep.getTime());
                Double promPMin = reporteRepository.promPresionMinima(repConfig.getCedula(), rep.getTime());
                Double promFG = reporteRepository.promFrecGas(repConfig.getCedula(), rep.getTime());
                Double promHA = reporteRepository.promHumedadAire(repConfig.getCedula(), rep.getTime());
                Double promVG = reporteRepository.promVolGas(repConfig.getCedula(), rep.getTime());
                Double promM = reporteRepository.promMezcla(repConfig.getCedula(), rep.getTime());
                Double promTE = reporteRepository.promTempEntrada(repConfig.getCedula(), rep.getTime());
                Double promTS = reporteRepository.promTempSalida(repConfig.getCedula(), rep.getTime());

                // create your json here
                JSONObject reporteNuevo = new JSONObject();
                try {
                    reporteNuevo.put("presionEntrada", promPE);
                    reporteNuevo.put("presionSalida", promPS);
                    reporteNuevo.put("presionMaxima", promPMax);
                    reporteNuevo.put("presionMinima", promPMin);
                    reporteNuevo.put("frecGas", promFG);
                    reporteNuevo.put("humedadAire", promHA);
                    reporteNuevo.put("volumenGas", promVG);
                    reporteNuevo.put("mezcla", promM);
                    reporteNuevo.put("tempEntrada", promTE);
                    reporteNuevo.put("tempSalida", promTS);
                    reporteNuevo.put("unidadVolumen", rep.getUnidadVolumen());
                    reporteNuevo.put("unidadFrecuencia", rep.getUnidadFrecuencia());
                    reporteNuevo.put("unidadPresion", rep.getUnidadPresion());
                    reporteNuevo.put("unidadHumedad", rep.getUnidadHumedad());
                    reporteNuevo.put("unidadTemp", rep.getUnidadTemp());
                    reporteNuevo.put("time", rep.getTime());
                    reporteNuevo.put("alerta", rep.getAlerta());


                    JSONObject body = new JSONObject();
                    body.put("reporte", reporteNuevo.toString());
                    body.put("cedula", repConfig.getCedula());
                    Utils.sendMaster(body.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                JSONObject body = new JSONObject();
                body.put("reporte", rep.toJson());
                body.put("cedula", repConfig.getCedula());
                Utils.sendMaster(body.toString());
            }

        }).start();
    }
}
