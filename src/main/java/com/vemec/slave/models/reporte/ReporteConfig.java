package com.vemec.slave.models.reporte;

import org.hibernate.annotations.IndexColumn;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Component
@Table(indexes = { @Index(columnList = "cedula") })
public class ReporteConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cedula;

    @Temporal(TemporalType.TIMESTAMP)
    private Date cronometro;

    private Integer timer;

    public ReporteConfig() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Date getCronometro() {
        return cronometro;
    }

    public void setCronometro(Date cronometro) {
        this.cronometro = cronometro;
    }

    public Integer getTimer() {
        return timer;
    }

    public void setTimer(Integer timer) {
        this.timer = timer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReporteConfig that = (ReporteConfig) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(cedula, that.cedula) &&
                Objects.equals(cronometro, that.cronometro) &&
                Objects.equals(timer, that.timer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cedula, cronometro, timer);
    }
}

