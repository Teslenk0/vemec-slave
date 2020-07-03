package com.vemec.slave.models.reporte;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteConfigRepository extends CrudRepository<ReporteConfig, Long> {

    ReporteConfig findByCedula(Integer cedula);
}
