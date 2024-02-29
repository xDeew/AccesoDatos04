package com.andrelut.gimnasioMongo.gui;

import com.andrelut.gimnasioMongo.base.Clase;
import com.andrelut.gimnasioMongo.base.Cliente;
import com.andrelut.gimnasioMongo.base.Suscripcion;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import javax.swing.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static com.mongodb.client.model.Aggregates.match;

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
        if (cliente != null) {
            cliente.close();
            cliente = null;
        }
    }


    public boolean estaConectado() {
        return cliente != null;
    }


    public void guardarObjeto(Object objeto) {
        if (objeto instanceof Cliente) {
            coleccionClientes.insertOne(objectToDocument(objeto));
        } else if (objeto instanceof Clase) {
            coleccionClases.insertOne(objectToDocument(objeto));
        } else if (objeto instanceof Suscripcion) {
            Suscripcion suscripcion = (Suscripcion) objeto;
            Document query = new Document("clienteId", suscripcion.getCliente().getId());
            Document existingSubscription = coleccionSuscripciones.find(query).first();
            if (existingSubscription != null) {
                JOptionPane.showMessageDialog(null, "El cliente ya tiene una suscripción activa");
            } else {
                coleccionSuscripciones.insertOne(objectToDocument(objeto));
                JOptionPane.showMessageDialog(null, "Suscripción guardada correctamente.");
            }
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
            document.append("fechaSuscripcion", Date.from(suscripcion.getFechaSuscripcion().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            document.append("fechaFinalizacion", Date.from(suscripcion.getFechaFinalizacion().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            document.append("estado", suscripcion.getEstado());

            if (suscripcion.getCliente() != null) {
                document.append("clienteId", suscripcion.getCliente().getId());
            }

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
        if (document == null) {
            return null;
        }

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

    public void modificarObjeto(Object objeto) {
        if (objeto instanceof Cliente) {
            Cliente cliente = (Cliente) objeto;
            coleccionClientes.replaceOne(new Document("_id", cliente.getId()), objectToDocument(cliente));
        } else if (objeto instanceof Clase) {
            Clase clase = (Clase) objeto;
            coleccionClases.replaceOne(new Document("_id", clase.getId()), objectToDocument(clase));
        } else if (objeto instanceof Suscripcion) {
            Suscripcion suscripcion = (Suscripcion) objeto;
            coleccionSuscripciones.replaceOne(new Document("_id", suscripcion.getId()), objectToDocument(suscripcion));
        }
    }

    public void eliminarObjeto(Object selectedValue) {
        if (selectedValue instanceof Cliente) {
            Cliente cliente = (Cliente) selectedValue;
            coleccionClientes.deleteOne(new Document("_id", cliente.getId()));
        } else if (selectedValue instanceof Clase) {
            Clase clase = (Clase) selectedValue;
            coleccionClases.deleteOne(new Document("_id", clase.getId()));
        } else if (selectedValue instanceof Suscripcion) {
            Suscripcion suscripcion = (Suscripcion) selectedValue;
            coleccionSuscripciones.deleteOne(new Document("_id", suscripcion.getId()));
        }
    }

    public ArrayList<Suscripcion> getSuscripciones() {
        ArrayList<Suscripcion> lista = new ArrayList<>();

        for (Document document : coleccionSuscripciones.find()) {
            lista.add(documentToSuscripcion(document));
        }
        return lista;

    }

    private Suscripcion documentToSuscripcion(Document document) {
        Suscripcion suscripcion = new Suscripcion();

        suscripcion.setId(document.getObjectId("_id"));
        suscripcion.setFechaSuscripcion(document.getDate("fechaSuscripcion").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        suscripcion.setFechaFinalizacion(document.getDate("fechaFinalizacion").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        suscripcion.setEstado(document.getString("estado"));

        ObjectId clienteId = document.getObjectId("clienteId");
        Cliente cliente = getClienteById(clienteId);
        suscripcion.setCliente(cliente);

        return suscripcion;
    }

    private Cliente getClienteById(ObjectId id) {
        Document document = coleccionClientes.find(new Document("_id", id)).first();
        return documentToCliente(document);
    }

    public ArrayList<Cliente> getClients(String comparador) {
        ArrayList<Cliente> lista = new ArrayList<>();
        Document query = new Document();
        query.append("nombre", new Document("$regex", "/*" + comparador + "/*"));

        for (Document document : coleccionClientes.find(query)) {
            lista.add(documentToCliente(document));
        }

        return lista;
    }

    public ArrayList<Suscripcion> getSubscriptions(String text) {
        ArrayList<Suscripcion> lista = new ArrayList<>();

        Bson matchCliente = match(Filters.regex("nombre", ".*" + text + ".*"));
        for (Document clienteDocument : coleccionClientes.aggregate(Arrays.asList(matchCliente))) {
            Cliente cliente = documentToCliente(clienteDocument);

            Bson matchSuscripcion = match(Filters.eq("clienteId", cliente.getId()));
            for (Document suscripcionDocument : coleccionSuscripciones.aggregate(Arrays.asList(matchSuscripcion))) {
                Suscripcion suscripcion = documentToSuscripcion(suscripcionDocument);
                suscripcion.setCliente(cliente);
                lista.add(suscripcion);
            }
        }

        return lista;
    }


    public ArrayList<Clase> getClases(String text) {
        return null;
    }
}
