package com.vemec.slave.controllers;

import com.vemec.slave.services.ReporteService;
import com.vemec.slave.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RestController
@RequestMapping(path = "/api/v1/reporte")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @PostMapping
    public @ResponseBody
    ResponseEntity addNew(@RequestBody Map<String, Object> payload) {
        try {
            return new ResponseEntity<>(this.reporteService.addNew(payload), null, HttpStatus.CREATED);
        } catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }
}
