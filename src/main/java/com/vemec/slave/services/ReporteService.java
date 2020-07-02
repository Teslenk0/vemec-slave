package com.vemec.slave.services;


import com.vemec.slave.controllers.WebSocketController;
import com.vemec.slave.models.reporte.Reporte;
import com.vemec.slave.models.reporte.ReporteRepository;
import com.vemec.slave.models.reporte.ReporteConfig;
import com.vemec.slave.models.reporte.ReporteConfigRepository;
import com.vemec.slave.utils.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vemec.slave.utils.Utils;
import java.util.*;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;
    @Autowired
    private ReporteConfigRepository reporteConfigRepository;

    public Reporte addNew(Map<String, Object> payload) throws Exception {
        try {
            Reporte r = new Reporte();
            r = Mappers.mapToReporte(payload, r);

            ReporteConfig rc = reporteConfigRepository.findByCedula(r.getCedula());
            Map<String, Object> data = Utils.evaluarUrgencia(r.getPpm(), r.getNivelBateria());

            if(rc == null){
                rc = new ReporteConfig();
                rc.setCedula(r.getCedula());
                rc.setCronometro(r.getTime());
                rc.setTimer((Integer) data.get("tiempo"));
                reporteConfigRepository.save(rc);
            }
            if(rc.getTimer() != ((Integer) data.get("tiempo"))){
                rc.setTimer((Integer) data.get("tiempo"));
                reporteConfigRepository.save(rc);
            }
            reporteRepository.save(r);
            if(data.get("mensaje") == "bateria" || data.get("mensaje") == "pulsaciones"){
                sendMaster(r, rc, (String)data.get("mensaje"));
            }
            else if(Utils.checkTimer(rc.getCronometro(), rc.getTimer(), r)) {
                sendMaster(r, rc, (String)data.get("mensaje"));
            }
            // send data to local monitor
            WebSocketController.sendWebSocketUpdate(r);
            return r;
        } catch (Exception e) {
            throw e;
        }
    }

    private void sendMaster(Reporte rep, ReporteConfig repConfig, String mensaje){

        new Thread(() -> {
            //Do whatever
            System.out.println("------------------");
            System.out.println(mensaje);
            System.out.println(repConfig.getTimer());
            System.out.println("------------------");
            repConfig.setCronometro(rep.getTime());
            reporteConfigRepository.save(repConfig);
            if(repConfig.getTimer() != 1){
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

                Map<String, Object> reporteNuevo = new HashMap<>();
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
                reporteNuevo.put("unidadVolumen",rep.getUnidadVolumen() );
                reporteNuevo.put("unidadFrecuencia", rep.getUnidadFrecuencia());
                reporteNuevo.put("unidadPresion", rep.getUnidadPresion());
                reporteNuevo.put("unidadHumedad", rep.getUnidadHumedad());
                reporteNuevo.put("unidadTemp", rep.getUnidadTemp());
                reporteNuevo.put("cedula", repConfig.getCedula());
                Map<String, Object> body = new HashMap<>();
                body.put("reporte", reporteNuevo);
                body.put("estado", mensaje);
                Utils.sendMaster(body.toString());
            }
            else{
                Map<String, Object> body = new HashMap<>();
                body.put("reporte", rep.toJson());
                body.put("estado", mensaje);
                Utils.sendMaster(body.toString());
            }

        }).start();
    }
}
