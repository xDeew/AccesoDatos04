package com.andrelut.gimnasioMongo.gui;


import com.andrelut.gimnasioMongo.base.Clase;
import com.andrelut.gimnasioMongo.base.Cliente;
import com.andrelut.gimnasioMongo.base.Suscripcion;
import com.andrelut.gimnasioMongo.util.Util;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Controlador implements ActionListener, KeyListener, ListSelectionListener {
    private Modelo modelo;
    private Vista vista;
    private boolean conectado;


    public Controlador(Modelo modelo, Vista vista) {
        this.vista = vista;
        this.modelo = modelo;
        this.conectado = false;


        addActionListeners(this);
        addKeyListeners(this);
        addListSelectionListeners(this);


    }

    private void addActionListeners(ActionListener listener) {
        vista.btnNuevoCliente.addActionListener(listener);
        vista.btnModificarCliente.addActionListener(listener);
        vista.btnModificarCliente.addActionListener(listener);
        vista.btnNuevaClase.addActionListener(listener);
        vista.btnModificarClase.addActionListener(listener);
        vista.btnBorrarClase.addActionListener(listener);
        vista.btnNuevaSuscripcion.addActionListener(listener);
        vista.btnModificarSuscripcion.addActionListener(listener);
        vista.btnBorrarSuscripcion.addActionListener(listener);

        vista.itemConexion.addActionListener(listener);
        vista.itemSalir.addActionListener(listener);
    }

    private void addListSelectionListeners(ListSelectionListener listener) {
        vista.listClientes.addListSelectionListener(listener);
        vista.listSuscripciones.addListSelectionListener(listener);
        vista.listClases.addListSelectionListener(listener);
    }

    private void addKeyListeners(KeyListener listener) {
        vista.txtBuscarCliente.addKeyListener(listener);
        vista.txtBuscarSuscripcion.addKeyListener(listener);
        vista.txtBuscarClase.addKeyListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (!comando.equals("conectar") && !comando.equals("salir")) {
            conectado = modelo.estaConectado();
            if (!conectado) {
                JOptionPane.showMessageDialog(vista.frame, "No hay conexión a la base de datos. Por favor, conecte antes de continuar.", "Error de conexión", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        switch (comando) {
            case "desconectar":
                modelo.desconectar();
                JOptionPane.showMessageDialog(vista.frame, "Desconectado de la base de datos.");
                vista.itemConexion.setText("Conectar");
                vista.itemConexion.setActionCommand("conectar");
                vista.comboClientesRegistrados.setEnabled(false);

                break;
            case "conectar":

                modelo.conectar();
                JOptionPane.showMessageDialog(vista.frame, "Conectado a la base de datos.");
                vista.itemConexion.setText("Desconectar");
                vista.itemConexion.setActionCommand("desconectar");
                vista.comboClientesRegistrados.setEnabled(true);
                actualizarComboClientesRegistrados();


                break;

            case "salir":
                modelo.desconectar();
                System.exit(0);
                break;

            case "addCliente":
                if (comprobarCamposCliente()) {
                    modelo.guardarObjeto(new Cliente(vista.txtNombre.getText(), vista.fechaNacimiento.getDate(), Double.parseDouble(vista.txtPeso.getText()), Double.parseDouble(vista.txtAltura.getText())));
                    JOptionPane.showMessageDialog(vista.frame, "Cliente guardado correctamente.");
                    limpiarCamposCliente();
                } else {
                    Util.mensajeError("Por favor, rellene todos los campos.");
                }
                listarClases();

                break;

            case "modificarCliente":

                break;

            case "eliminarCliente":

                break;

            case "addSuscripcion":

                break;

            case "modificarSuscripcion":

                break;

            case "eliminarSuscripcion":

                break;

            case "addClase":

                break;

            case "modificarClase":


                break;

            case "eliminarClase":

                break;
        }
    }

    private void actualizarComboClientesRegistrados() {
        vista.comboClientesRegistrados.removeAllItems();

        MongoCollection<Document> coleccionClientes = modelo.coleccionClientes;
        FindIterable<Document> documentos = coleccionClientes.find();

        for (Document documento : documentos) {
            Cliente cliente = new Cliente();
            cliente.setId(documento.getObjectId("_id"));
            cliente.setNombre(documento.getString("nombre"));
            Date nacimientoDate = documento.getDate("nacimiento");
            if (nacimientoDate != null) {
                cliente.setNacimiento(nacimientoDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            }
            cliente.setPeso(documento.getDouble("peso"));
            cliente.setAltura(documento.getDouble("altura"));

            vista.comboClientesRegistrados.addItem(cliente.getNombre() + " - " + cliente.getId());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {


    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    private boolean comprobarCamposCliente() {
        return !vista.txtNombre.getText().isEmpty() && !vista.txtPeso.getText().isEmpty() && !vista.txtAltura.getText().isEmpty() && vista.fechaNacimiento.getDate() != null;


    }

    private boolean comprobarCamposSuscripcion() {
        return !vista.fechaInicio.getDate().toString().isEmpty() && !vista.fechaFin.getDate().toString().isEmpty() && !vista.comboEstadoSuscripcion.getSelectedItem().toString().isEmpty() && !vista.comboClientesRegistrados.getSelectedItem().toString().isEmpty();

    }

    private boolean comprobarCamposClase() {

        return !vista.txtNombreClase.getText().isEmpty() && !vista.txtInstructor.getText().isEmpty() && !vista.txtHorario.getText().isEmpty();

    }

    private void limpiarCamposCliente() {
        vista.txtNombre.setText("");
        vista.txtPeso.setText("");
        vista.txtAltura.setText("");
        vista.fechaNacimiento.setDate(null);

    }

    private void limpiarCamposSuscripcion() {

    }

    private void limpiarCamposClase() {

    }


    private void listarClientes() {

    }

    private void listarSuscripciones() {

    }

    private void listarClases() {
        vista.dlmClientes.clear();
        for (Cliente cliente : modelo.getClientes()) {
            vista.dlmClientes.addElement(cliente);
        }

    }

    private void listarClientesBusqueda(ArrayList<Cliente> lista) {

    }

    private void listarSuscripcionesBusqueda(ArrayList<Suscripcion> lista) {

    }

    private void listarClasesBusqueda(ArrayList<Clase> lista) {

    }

    private void setBotonesActivados(boolean activados) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }
}
