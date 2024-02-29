package com.andrelut.gimnasioMongo.base;

import org.bson.types.ObjectId;

public class Clase {

    private ObjectId id;
    private String nombre;
    private String instructor;
    private String horario;

    public Clase(String nombre, String instructor, String horario) {
        this.nombre = nombre;
        this.instructor = instructor;
        this.horario = horario;
    }

    public Clase() {

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

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Nombre de la Clase: " + nombre + " \n " +
                "Instructor: " + instructor + " \n " +
                "Horario: " + horario + " \n ";
    }


}
