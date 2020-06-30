package com.vemec.slave.services;


import com.vemec.slave.controllers.WebSocketController;
import com.vemec.slave.models.reporte.Reporte;
import com.vemec.slave.models.reporte.ReporteRepository;
import com.vemec.slave.utils.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    public Reporte addNew(Map<String, Object> payload) throws Exception {
        try {
            Reporte r = new Reporte();
            r = Mappers.mapToReporte(payload, r);
            reporteRepository.save(r);

            // send data to local monitor
            WebSocketController.sendWebSocketUpdate(r);
            return r;
        } catch (Exception e) {
            throw e;
        }
    }
}
