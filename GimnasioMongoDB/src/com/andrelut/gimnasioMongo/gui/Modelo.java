package com.andrelut.gimnasioMongo.gui;

import com.andrelut.gimnasioMongo.base.Clase;
import com.andrelut.gimnasioMongo.base.Cliente;
import com.andrelut.gimnasioMongo.base.Suscripcion;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Modelo {
    private final static String COLECCION_CLASES = "clases";
    private final static String COLECCION_CLIENTES = "clientes";
    private final static String COLECCION_SUSCRIPCIONES = "suscripciones";
    public MongoCollection<Document> coleccionClientes;
    private MongoClient cliente;
    private MongoCollection<Document> coleccionClases;
    private MongoCollection<Document> coleccionSuscripciones;

    public Modelo() {
        this.cliente = null;
    }


    public void conectar() {
        cliente = new MongoClient();
        String DATABASE = "gimnasioDB";
        MongoDatabase db = cliente.getDatabase(DATABASE);

        coleccionClases = db.getCollection(COLECCION_CLASES);
        coleccionClientes = db.getCollection(COLECCION_CLIENTES);
        coleccionSuscripciones = db.getCollection(COLECCION_SUSCRIPCIONES);

    }

    public void desconectar() {
        cliente.close();
        cliente = null;
    }


    public boolean estaConectado() {
        return cliente != null;
    }


    public void guardarObjeto(Object objeto) {
        if (objeto instanceof Cliente) {
            coleccionClientes.insertOne(objectToDocument(objeto));
        }
    }

    private Document objectToDocument(Object objeto) {
        Document document = new Document();

        if (objeto instanceof Cliente) {
            Cliente cliente = (Cliente) objeto;
            document.append("nombre", cliente.getNombre());
            document.append("fechaNacimiento", Date.from(cliente.getNacimiento().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            document.append("peso", cliente.getPeso());
            document.append("altura", cliente.getAltura());
        } else if (objeto instanceof Clase) {
            Clase clase = (Clase) objeto;
            document.append("nombre", clase.getNombre());
            document.append("instructor", clase.getInstructor());
            document.append("horario", clase.getHorario());
        } else if (objeto instanceof Suscripcion) {
            Suscripcion suscripcion = (Suscripcion) objeto;
            document.append("fechaSuscripcion", suscripcion.getFechaSuscripcion());
            document.append("fechaFinalizacion", suscripcion.getFechaFinalizacion());
            document.append("estado", suscripcion.getEstado());

        } else {
            return null;
        }
        return document;
    }

    public ArrayList<Cliente> getClientes() {
        ArrayList<Cliente> lista = new ArrayList<>();

        for (Document document : coleccionClientes.find()) {
            lista.add(documentToCliente(document));
        }
        return lista;
    }

    private Cliente documentToCliente(Document document) {
        Cliente cliente = new Cliente();

        cliente.setId(document.getObjectId("_id"));
        cliente.setNombre(document.getString("nombre"));
        Date nacimientoDate = document.getDate("fechaNacimiento");
        if (nacimientoDate != null) {
            cliente.setNacimiento(nacimientoDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        cliente.setPeso(document.getDouble("peso"));
        cliente.setAltura(document.getDouble("altura"));

        return cliente;
    }
}
