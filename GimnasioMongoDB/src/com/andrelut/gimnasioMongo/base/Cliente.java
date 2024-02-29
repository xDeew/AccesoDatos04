package com.andrelut.gimnasioMongo.base;

import org.bson.types.ObjectId;

import java.time.LocalDate;

public class Cliente {
    private ObjectId id;
    private String nombre;
    private LocalDate nacimiento;
    private double peso;
    private double altura;

    public Cliente(String nombre, LocalDate nacimiento, double peso, double altura) {
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.peso = peso;
        this.altura = altura;
    }

    public Cliente() {

    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "   \n   " +
                "Nacimiento: " + nacimiento + "   \n   " +
                "Peso: " + peso + "kg\n   " +
                "Altura: " + altura + "m\n   ";
    }

}
