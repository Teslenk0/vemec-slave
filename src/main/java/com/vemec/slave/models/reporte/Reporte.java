package com.vemec.slave.models.reporte;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Component
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double presionMaxima;
    private double presionMinima;
    private double volGas;
    private double frecGas;
    private double mezcla;
    private double humedadAire;
    private double tempEntrada;
    private double tempSalida;
    private double presionEntrada;
    private double presionSalida;

    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    private String cedula;

    private String unidadPresion;
    private String unidadTemp;
    private String unidadHumedad;
    private String unidadFrecuencia;
    private String unidadVolumen;

    public Reporte() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPresionMaxima() {
        return presionMaxima;
    }

    public void setPresionMaxima(double presionMaxima) {
        this.presionMaxima = presionMaxima;
    }

    public double getPresionMinima() {
        return presionMinima;
    }

    public void setPresionMinima(double presionMinima) {
        this.presionMinima = presionMinima;
    }

    public double getVolGas() {
        return volGas;
    }

    public void setVolGas(double volGas) {
        this.volGas = volGas;
    }

    public double getFrecGas() {
        return frecGas;
    }

    public void setFrecGas(double frecGas) {
        this.frecGas = frecGas;
    }

    public double getMezcla() {
        return mezcla;
    }

    public void setMezcla(double mezcla) {
        this.mezcla = mezcla;
    }

    public double getHumedadAire() {
        return humedadAire;
    }

    public void setHumedadAire(double humedadAire) {
        this.humedadAire = humedadAire;
    }

    public double getTempEntrada() {
        return tempEntrada;
    }

    public void setTempEntrada(double tempEntrada) {
        this.tempEntrada = tempEntrada;
    }

    public double getTempSalida() {
        return tempSalida;
    }

    public void setTempSalida(double tempSalida) {
        this.tempSalida = tempSalida;
    }

    public double getPresionEntrada() {
        return presionEntrada;
    }

    public void setPresionEntrada(double presionEntrada) {
        this.presionEntrada = presionEntrada;
    }

    public double getPresionSalida() {
        return presionSalida;
    }

    public void setPresionSalida(double presionSalida) {
        this.presionSalida = presionSalida;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getUnidadPresion() {
        return unidadPresion;
    }

    public void setUnidadPresion(String unidadPresion) {
        this.unidadPresion = unidadPresion;
    }

    public String getUnidadTemp() {
        return unidadTemp;
    }

    public void setUnidadTemp(String unidadTemp) {
        this.unidadTemp = unidadTemp;
    }

    public String getUnidadHumedad() {
        return unidadHumedad;
    }

    public void setUnidadHumedad(String unidadHumedad) {
        this.unidadHumedad = unidadHumedad;
    }

    public String getUnidadFrecuencia() {
        return unidadFrecuencia;
    }

    public void setUnidadFrecuencia(String unidadFrecuencia) {
        this.unidadFrecuencia = unidadFrecuencia;
    }

    public String getUnidadVolumen() {
        return unidadVolumen;
    }

    public void setUnidadVolumen(String unidadVolumen) {
        this.unidadVolumen = unidadVolumen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reporte reporte = (Reporte) o;
        return Double.compare(reporte.presionMaxima, presionMaxima) == 0 &&
                Double.compare(reporte.presionMinima, presionMinima) == 0 &&
                Double.compare(reporte.volGas, volGas) == 0 &&
                Double.compare(reporte.frecGas, frecGas) == 0 &&
                Double.compare(reporte.mezcla, mezcla) == 0 &&
                Double.compare(reporte.humedadAire, humedadAire) == 0 &&
                Double.compare(reporte.tempEntrada, tempEntrada) == 0 &&
                Double.compare(reporte.tempSalida, tempSalida) == 0 &&
                Double.compare(reporte.presionEntrada, presionEntrada) == 0 &&
                Double.compare(reporte.presionSalida, presionSalida) == 0 &&
                Objects.equals(id, reporte.id) &&
                Objects.equals(time, reporte.time) &&
                Objects.equals(cedula, reporte.cedula) &&
                Objects.equals(unidadPresion, reporte.unidadPresion) &&
                Objects.equals(unidadTemp, reporte.unidadTemp) &&
                Objects.equals(unidadHumedad, reporte.unidadHumedad) &&
                Objects.equals(unidadFrecuencia, reporte.unidadFrecuencia) &&
                Objects.equals(unidadVolumen, reporte.unidadVolumen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, presionMaxima, presionMinima, volGas, frecGas, mezcla, humedadAire, tempEntrada, tempSalida, presionEntrada, presionSalida, time, cedula, unidadPresion, unidadTemp, unidadHumedad, unidadFrecuencia, unidadVolumen);
    }
}
