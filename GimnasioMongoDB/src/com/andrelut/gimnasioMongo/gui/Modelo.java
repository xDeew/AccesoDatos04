package com.andrelut.gimnasioMongo.gui;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Modelo {
    private final static String COLECCION_CLASES = "clases";
    private final static String COLECCION_CLIENTES = "clientes";
    private final static String COLECCION_SUSCRIPCIONES = "suscripciones";

    private MongoClient cliente;
    private MongoCollection coleccionClases;
    private MongoCollection coleccionClientes;
    private MongoCollection coleccionSuscripciones;


    public void conectar() {
        cliente = new MongoClient();
        String DATABASE = "Gimnasio";
        MongoDatabase db = cliente.getDatabase(DATABASE);

        coleccionClases = db.getCollection(COLECCION_CLASES);
        coleccionClientes = db.getCollection(COLECCION_CLIENTES);
        coleccionSuscripciones = db.getCollection(COLECCION_SUSCRIPCIONES);

    }

    public void desconectar() {
        cliente.close();
        cliente = null;
    }

    public MongoClient getCliente() {
        return cliente;
    }

}
