package com.vemec.slave.models.reporte;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ReporteRepository extends PagingAndSortingRepository<Reporte, Integer> {

   @Query("SELECT AVG(r.presionEntrada) FROM Reporte r WHERE r.cedula = ?1 AND r.time <= ?2")
   Double promPresionEntrada(String cedula, Date date);

   @Query("SELECT AVG(r.presionSalida) FROM Reporte r WHERE r.cedula = ?1 AND r.time <= ?2")
   Double promPresionSalida(String cedula, Date date);

   @Query("SELECT AVG(r.presionMaxima) FROM Reporte r WHERE r.cedula = ?1 AND r.time <= ?2")
   Double promPresionMaxima(String cedula, Date date);

   @Query("SELECT AVG(r.presionMinima) FROM Reporte r WHERE r.cedula = ?1 AND r.time <= ?2")
   Double promPresionMinima(String cedula, Date date);

   @Query("SELECT AVG(r.tempEntrada) FROM Reporte r WHERE r.cedula = ?1 AND r.time <= ?2")
   Double promTempEntrada(String cedula, Date date);

   @Query("SELECT AVG(r.tempSalida) FROM Reporte r WHERE r.cedula = ?1 AND r.time <= ?2")
   Double promTempSalida(String cedula, Date date);

   @Query("SELECT AVG(r.humedadAire) FROM Reporte r WHERE r.cedula = ?1 AND r.time <= ?2")
   Double promHumedadAire(String cedula, Date date);

   @Query("SELECT AVG(r.mezcla) FROM Reporte r WHERE r.cedula = ?1 AND r.time <= ?2")
   Double promMezcla(String cedula, Date date);

   @Query("SELECT AVG(r.frecGas) FROM Reporte r WHERE r.cedula = ?1 AND r.time <= ?2")
   Double promFrecGas(String cedula, Date date);

   @Query("SELECT AVG(r.volGas) FROM Reporte r WHERE r.cedula = ?1 AND r.time <= ?2")
   Double promVolGas(String cedula, Date date);
}