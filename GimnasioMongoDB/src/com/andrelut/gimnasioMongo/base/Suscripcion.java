package com.andrelut.gimnasioMongo.base;

import org.bson.types.ObjectId;

import java.time.LocalDate;

public class Suscripcion {
    LocalDate fechaSuscripcion;
    LocalDate fechaFinalizacion;
    private ObjectId id;
    private String estado;
    private Cliente cliente;


    public Suscripcion(Cliente cliente, LocalDate fechaSuscripcion, LocalDate fechaFinalizacion, String estado) {
        this.fechaSuscripcion = fechaSuscripcion;
        this.fechaFinalizacion = fechaFinalizacion;
        this.estado = estado;
        this.cliente = cliente;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        String clienteInfo;
        if (cliente != null) clienteInfo = "Cliente: " + cliente.getNombre() + " - " + cliente.getId() + "  ";
        else clienteInfo = "Cliente: null";
        return clienteInfo + "  \n  " +
                "  Fecha de Suscripción: " + fechaSuscripcion + "  \n  " +
                "  Fecha de Finalización: " + fechaFinalizacion + "  \n  " +
                "  Estado: " + estado + "  \n  ";
    }
}
