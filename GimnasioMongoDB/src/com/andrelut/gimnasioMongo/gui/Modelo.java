package com.andrelut.gimnasioMongo.gui;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

public class Modelo {
    private final static String COLECCION_CLASES = "clases";
    private final static String COLECCION_CLIENTES = "clientes";
    private final static String COLECCION_SUSCRIPCIONES = "suscripciones";

    private MongoClient cliente;
    private MongoCollection coleccionClases;
    private MongoCollection coleccionClientes;
    private MongoCollection coleccionSuscripciones;


}
