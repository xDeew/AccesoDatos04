package com.andrelut.gimnasioMongo.gui;


import com.andrelut.gimnasioMongo.base.Clase;
import com.andrelut.gimnasioMongo.base.Cliente;
import com.andrelut.gimnasioMongo.base.Suscripcion;
import com.andrelut.gimnasioMongo.util.Util;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Controlador implements ActionListener, KeyListener, ListSelectionListener {
    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        this.vista = vista;
        this.modelo = modelo;

        addActionListeners(this);
        addKeyListeners(this);
        addListSelectionListeners(this);

        try {
            modelo.conectar();
            vista.itemConectar.setText("Desconectar");
            setBotonesActivados(true);
            listarClientes();
            listarSuscripciones();
            listarClases();
        } catch (Exception ex) {
            Util.mostrarMensajeError("No se ha podido establecer conexión con el servidor.");
        }
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

        vista.itemConectar.addActionListener(listener);
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
        switch (e.getActionCommand()) {
            case "conexion":
                try {
                    if (modelo.getCliente() == null) {
                        modelo.conectar();
                        vista.itemConectar.setText("Desconectar");
                        setBotonesActivados(true);
                        listarClientes();
                        listarSuscripciones();
                        listarClases();
                    } else {
                        modelo.desconectar();
                        vista.itemConectar.setText("Conectar");
                        setBotonesActivados(false);
//                        vista.dlmProductos.clear();
//                        vista.dlmEmpleados.clear();
//                        vista.dlmDepartamentos.clear();
                        limpiarCamposCliente();
                        limpiarCamposSuscripcion();
                        limpiarCamposClase();
                    }
                } catch (Exception ex) {
                    Util.mostrarMensajeError("No se ha podido establecer conexión con el servidor.");
                }
                break;

            case "salir":
                modelo.desconectar();
                System.exit(0);
                break;

            case "addCliente":

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

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    private boolean comprobarCamposCliente() {

        return false;

    }

    private boolean comprobarCamposSuscripcion() {

        return false;

    }

    private boolean comprobarCamposClase() {

        return false;

    }

    private void limpiarCamposCliente() {

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
