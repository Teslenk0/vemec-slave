package com.vemec.slave.models.reporte;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ReporteRepository extends PagingAndSortingRepository<Reporte, Integer> {

   @Query("SELECT AVG(r.presionEntrada) FROM Reporte r WHERE r.cedula = ?1 AND r.time <= ?2")
   Double promPresionEntrada(Integer cedula, Date date);

   @Query("SELECT AVG(r.presionSalida) FROM Reporte r WHERE r.cedula = ?1 AND r.time <= ?2")
   Double promPresionSalida(Integer cedula, Date date);

   @Query("SELECT AVG(r.presionMaxima) FROM Reporte r WHERE r.cedula = ?1 AND r.time <= ?2")
   Double promPresionMaxima(Integer cedula, Date date);

   @Query("SELECT AVG(r.presionMinima) FROM Reporte r WHERE r.cedula = ?1 AND r.time <= ?2")
   Double promPresionMinima(Integer cedula, Date date);

   @Query("SELECT AVG(r.tempEntrada) FROM Reporte r WHERE r.cedula = ?1 AND r.time <= ?2")
   Double promTempEntrada(Integer cedula, Date date);

   @Query("SELECT AVG(r.tempSalida) FROM Reporte r WHERE r.cedula = ?1 AND r.time <= ?2")
   Double promTempSalida(Integer cedula, Date date);

   @Query("SELECT AVG(r.humedadAire) FROM Reporte r WHERE r.cedula = ?1 AND r.time <= ?2")
   Double promHumedadAire(Integer cedula, Date date);

   @Query("SELECT AVG(r.mezcla) FROM Reporte r WHERE r.cedula = ?1 AND r.time <= ?2")
   Double promMezcla(Integer cedula, Date date);

   @Query("SELECT AVG(r.frecGas) FROM Reporte r WHERE r.cedula = ?1 AND r.time <= ?2")
   Double promFrecGas(Integer cedula, Date date);

   @Query("SELECT AVG(r.volGas) FROM Reporte r WHERE r.cedula = ?1 AND r.time <= ?2")
   Double promVolGas(Integer cedula, Date date);
}