package com.vemec.slave.models.reporte;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vemec.slave.constant.Alerta;
import org.hibernate.annotations.IndexColumn;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

@Entity
@Component
@Table(indexes = { @Index(columnList = "cedula" ), @Index(columnList = "time") })
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

    private Integer cedula;
    private Alerta alerta;

    private String unidadPresion;
    private String unidadTemp;
    private String unidadHumedad;
    private String unidadFrecuencia;
    private String unidadVolumen;
    private Integer ppm;
    private Boolean bateria;
    private Integer nivelBateria;

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

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public Alerta getAlerta() {
        return alerta;
    }

    public void setAlerta(Alerta alerta) {
        this.alerta = alerta;
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

    public Integer getPpm() {
        return ppm;
    }

    public void setPpm(Integer ppm) {
        this.ppm = ppm;
    }

    public Boolean getBateria() {
        return bateria;
    }

    public void setBateria(Boolean bateria) {
        this.bateria = bateria;
    }

    public Integer getNivelBateria() {
        return nivelBateria;
    }

    public void setNivelBateria(Integer nivelBateria) {
        this.nivelBateria = nivelBateria;
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
                alerta == reporte.alerta &&
                Objects.equals(unidadPresion, reporte.unidadPresion) &&
                Objects.equals(unidadTemp, reporte.unidadTemp) &&
                Objects.equals(unidadHumedad, reporte.unidadHumedad) &&
                Objects.equals(unidadFrecuencia, reporte.unidadFrecuencia) &&
                Objects.equals(unidadVolumen, reporte.unidadVolumen) &&
                Objects.equals(ppm, reporte.ppm) &&
                Objects.equals(bateria, reporte.bateria) &&
                Objects.equals(nivelBateria, reporte.nivelBateria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, presionMaxima, presionMinima, volGas, frecGas, mezcla, humedadAire, tempEntrada, tempSalida, presionEntrada, presionSalida, time, cedula, alerta, unidadPresion, unidadTemp, unidadHumedad, unidadFrecuencia, unidadVolumen, ppm, bateria, nivelBateria);
    }

    @Override
    public String toString() {
        return "Reporte{" +
                "id=" + id +
                ", presionMaxima=" + presionMaxima +
                ", presionMinima=" + presionMinima +
                ", volGas=" + volGas +
                ", frecGas=" + frecGas +
                ", mezcla=" + mezcla +
                ", humedadAire=" + humedadAire +
                ", tempEntrada=" + tempEntrada +
                ", tempSalida=" + tempSalida +
                ", presionEntrada=" + presionEntrada +
                ", presionSalida=" + presionSalida +
                ", time=" + time +
                ", cedula=" + cedula +
                ", alerta=" + alerta +
                ", unidadPresion='" + unidadPresion + '\'' +
                ", unidadTemp='" + unidadTemp + '\'' +
                ", unidadHumedad='" + unidadHumedad + '\'' +
                ", unidadFrecuencia='" + unidadFrecuencia + '\'' +
                ", unidadVolumen='" + unidadVolumen + '\'' +
                ", ppm=" + ppm +
                ", bateria=" + bateria +
                ", nivelBateria=" + nivelBateria +
                '}';
    }

    public String toJson() {
        ObjectMapper Obj = new ObjectMapper();

        try {

            // get Oraganisation object as a json string
            String jsonStr = Obj.writeValueAsString(this);
            return jsonStr;
        }

        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
