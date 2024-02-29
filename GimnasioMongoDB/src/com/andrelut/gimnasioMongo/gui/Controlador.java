package com.andrelut.gimnasioMongo.gui;


import com.andrelut.gimnasioMongo.base.Clase;
import com.andrelut.gimnasioMongo.base.Cliente;
import com.andrelut.gimnasioMongo.base.Suscripcion;
import com.andrelut.gimnasioMongo.util.Util;

import javax.swing.*;
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
        vista.btnBorrarCliente.addActionListener(listener);
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
                listarClientes();
                listarClases();
                listarSuscripciones();
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
                listarClientes();

                break;

            case "modificarCliente":
                if (vista.listClientes.getSelectedValue() != null) {
                    if (comprobarCamposCliente()) {
                        Cliente cliente = (Cliente) vista.listClientes.getSelectedValue();
                        cliente.setNombre(vista.txtNombre.getText());
                        cliente.setNacimiento(vista.fechaNacimiento.getDate());
                        cliente.setPeso(Double.parseDouble(vista.txtPeso.getText()));
                        cliente.setAltura(Double.parseDouble(vista.txtAltura.getText()));
                        modelo.modificarObjeto(cliente);
                        JOptionPane.showMessageDialog(vista.frame, "Cliente modificado correctamente.");
                        limpiarCamposCliente();
                    } else {
                        Util.mensajeError("Error al modificar, revise los campos e introduzca nuevamente.");
                    }
                    listarClientes();
                } else {
                    Util.mensajeError("Por favor, seleccione un cliente de la lista.");
                }

                break;

            case "eliminarCliente":
                if (vista.listClientes.getSelectedValue() != null) {
                    modelo.eliminarObjeto(vista.listClientes.getSelectedValue());
                    JOptionPane.showMessageDialog(vista.frame, "Cliente eliminado correctamente.");
                    limpiarCamposCliente();
                    listarClientes();
                } else {
                    Util.mensajeError("Por favor, seleccione un cliente de la lista.");
                }

                break;

            case "addSuscripcion":
                if (comprobarCamposSuscripcion()) {
                    Cliente clienteSeleccionado = (Cliente) vista.comboClientesRegistrados.getSelectedItem();
                    Suscripcion nuevaSuscripcion = new Suscripcion(clienteSeleccionado, vista.fechaInicio.getDate(), vista.fechaFin.getDate(), vista.comboEstadoSuscripcion.getSelectedItem().toString());
                    modelo.guardarObjeto(nuevaSuscripcion);
                    JOptionPane.showMessageDialog(vista.frame, "Suscripción guardada correctamente.");
                    limpiarCamposSuscripcion();
                } else {
                    Util.mensajeError("Por favor, rellene todos los campos.");
                }
                listarSuscripciones();
                break;

            case "modificarSuscripcion":
                if (vista.listSuscripciones.getSelectedValue() != null) {
                    if (comprobarCamposSuscripcion()) {
                        Suscripcion suscripcion = (Suscripcion) vista.listSuscripciones.getSelectedValue();
                        suscripcion.setCliente((Cliente) vista.comboClientesRegistrados.getSelectedItem());
                        suscripcion.setFechaSuscripcion(vista.fechaInicio.getDate());
                        suscripcion.setFechaFinalizacion(vista.fechaFin.getDate());
                        suscripcion.setEstado(vista.comboEstadoSuscripcion.getSelectedItem().toString());
                        modelo.modificarObjeto(suscripcion);
                        JOptionPane.showMessageDialog(vista.frame, "Suscripción modificada correctamente.");
                        limpiarCamposSuscripcion();
                    } else {
                        Util.mensajeError("Error al modificar, revise los campos e introduzca nuevamente.");
                    }
                    listarSuscripciones();
                } else {
                    Util.mensajeError("Por favor, seleccione una suscripción de la lista.");
                }

                break;

            case "eliminarSuscripcion":
                if (vista.listSuscripciones.getSelectedValue() != null) {
                    modelo.eliminarObjeto(vista.listSuscripciones.getSelectedValue());
                    JOptionPane.showMessageDialog(vista.frame, "Suscripción eliminada correctamente.");
                    limpiarCamposSuscripcion();
                    listarSuscripciones();
                } else {
                    Util.mensajeError("Por favor, seleccione una suscripción de la lista.");
                }

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

        ArrayList<Cliente> clientes = modelo.getClientes();

        for (Cliente cliente : clientes) {
            vista.comboClientesRegistrados.addItem(cliente);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {


    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            Cliente cliente = (Cliente) vista.listClientes.getSelectedValue();
            if (cliente != null) {
                vista.txtNombre.setText(cliente.getNombre());
                vista.fechaNacimiento.setDate(cliente.getNacimiento());
                vista.txtPeso.setText(String.valueOf(cliente.getPeso()));
                vista.txtAltura.setText(String.valueOf(cliente.getAltura()));

            }
            Suscripcion suscripcion = (Suscripcion) vista.listSuscripciones.getSelectedValue();
            if (suscripcion != null && suscripcion.getCliente() != null) {
                Cliente clienteSeleccionado = suscripcion.getCliente();
                for (int i = 0; i < vista.comboClientesRegistrados.getItemCount(); i++) {
                    cliente = (Cliente) vista.comboClientesRegistrados.getItemAt(i);
                    if (cliente != null && cliente.getId().equals(clienteSeleccionado.getId())) {
                        vista.comboClientesRegistrados.setSelectedIndex(i);
                        break;
                    }
                }
                vista.fechaInicio.setDate(suscripcion.getFechaSuscripcion());
                vista.fechaFin.setDate(suscripcion.getFechaFinalizacion());
                vista.comboEstadoSuscripcion.setSelectedItem(suscripcion.getEstado());
            }

        }

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
        vista.fechaInicio.setDate(null);
        vista.fechaFin.setDate(null);
        vista.comboEstadoSuscripcion.setSelectedIndex(-1);
        vista.comboClientesRegistrados.setSelectedIndex(-1);

    }

    private void limpiarCamposClase() {

    }


    private void listarClientes() {
        vista.dlmClientes.clear();
        for (Cliente cliente : modelo.getClientes()) {
            vista.dlmClientes.addElement(cliente);
        }

    }

    private void listarSuscripciones() {
        vista.dlmSuscripciones.clear();
        for (Suscripcion suscripcion : modelo.getSuscripciones()) {
            vista.dlmSuscripciones.addElement(suscripcion);
        }

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
