package com.andrelut.gimnasioMongo.base;

import org.bson.types.ObjectId;

import java.time.LocalDate;

public class Suscripcion {
    private ObjectId id;

    LocalDate fechaSuscripcion;
    LocalDate fechaFinalizacion;

    private String estado;

    public Suscripcion(LocalDate fechaSuscripcion, LocalDate fechaFinalizacion, String estado) {
        this.fechaSuscripcion = fechaSuscripcion;
        this.fechaFinalizacion = fechaFinalizacion;
        this.estado = estado;
    }

    public Suscripcion() {

    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
    public LocalDate getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    public void setFechaSuscripcion(LocalDate fechaSuscripcion) {
        this.fechaSuscripcion = fechaSuscripcion;
    }

    public LocalDate getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Suscripcion{" +
                "fechaInicio=" + fechaSuscripcion +
                ", fechaFin=" + fechaFinalizacion +
                ", estado='" + estado + '\'' +
                '}';
    }
}
