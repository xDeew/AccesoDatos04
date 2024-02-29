package com.andrelut.gimnasioMongo.gui;

import com.andrelut.gimnasioMongo.base.Cliente;
import com.andrelut.gimnasioMongo.base.Suscripcion;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;

public class Vista {

    public JTabbedPane tabbedPanedGimnasio;
    public JPanel JPanelClientes;
    public JPanel JPanelClases;
    public JPanel JPanelSuscripciones;
    public JPanel panel1;
    public JButton btnNuevoCliente;
    public JButton btnModificarCliente;
    public JButton btnBorrarCliente;
    public JButton btnNuevaSuscripcion;
    public JButton btnModificarSuscripcion;
    public JButton btnBorrarSuscripcion;
    public JButton btnNuevaClase;
    public JButton btnModificarClase;
    public JButton btnBorrarClase;
    public JTextField txtNombre;
    public DatePicker fechaNacimiento;
    public JTextField txtAltura;
    public JTextField txtPeso;
    public DatePicker fechaInicio;
    public DatePicker fechaFin;
    public JComboBox comboEstadoSuscripcion;
    public JTextField txtNombreClase;
    public JTextField txtInstructor;
    public JFrame frame;
    public JTextField txtHorario;
    public JList listClases;
    public JComboBox comboClientesRegistrados;
    public JList listClientes;
    public JList listSuscripciones;
    public JTextField txtBuscarCliente;
    public JTextField txtBuscarSuscripcion;
    public JTextField txtBuscarClase;
    public JList<Cliente> listBusquedaCliente;
    public JList listBusquedaSuscripcionesCliente;
    public DefaultListModel<Suscripcion> dlmBusquedaSuscripcionesClientes;
    public DefaultListModel<Cliente> dlmBusquedaClientes;
    public DefaultListModel<Cliente> dlmClientes;
    public DefaultListModel<Suscripcion> dlmSuscripciones;
    JMenuItem itemConexion;
    JMenuItem itemSalir;


    public Vista() {
        frame = new JFrame("Gimnasio");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(new Dimension(1100, 600));
        frame.setLocationRelativeTo(null);

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        setMenu();
        setListModels();

        Color lightBlue = new Color(173, 216, 230);
        Color skyBlue = new Color(135, 206, 235);

        btnNuevoCliente.setBackground(lightBlue);
        btnModificarCliente.setBackground(lightBlue);
        btnBorrarCliente.setBackground(lightBlue);

        btnNuevaSuscripcion.setBackground(lightBlue);
        btnModificarSuscripcion.setBackground(lightBlue);
        btnBorrarSuscripcion.setBackground(lightBlue);

        btnNuevaClase.setBackground(lightBlue);
        btnModificarClase.setBackground(lightBlue);
        btnBorrarClase.setBackground(lightBlue);

        JPanelClientes.setBackground(skyBlue);
        JPanelClases.setBackground(skyBlue);
        JPanelSuscripciones.setBackground(skyBlue);
        setComboBox();

    }

    private void setComboBox() {
        comboEstadoSuscripcion.addItem("Activa");
        comboEstadoSuscripcion.addItem("No activa");

        comboClientesRegistrados.setSelectedIndex(-1);
        comboEstadoSuscripcion.setSelectedIndex(-1);
    }

    private void setListModels() {
        dlmClientes = new DefaultListModel<>();
        listClientes.setModel(dlmClientes);

        dlmSuscripciones = new DefaultListModel<>();
        listSuscripciones.setModel(dlmSuscripciones);

        dlmBusquedaClientes = new DefaultListModel<>();
        listBusquedaCliente.setModel(dlmBusquedaClientes);

        dlmBusquedaSuscripcionesClientes = new DefaultListModel<>();
        listBusquedaSuscripcionesCliente.setModel(dlmBusquedaSuscripcionesClientes);
    }

    private void setMenu() {
        JMenuBar mbBar = new JMenuBar();
        JMenu menu = new JMenu("Archivo");
        itemConexion = new JMenuItem("Conectar");
        itemConexion.setActionCommand("conectar");
        itemSalir = new JMenuItem("Salir");
        itemSalir.setActionCommand("salir");
        menu.add(itemConexion);
        menu.add(itemSalir);
        mbBar.add(menu);
        mbBar.add(Box.createHorizontalGlue());
        frame.setJMenuBar(mbBar);
    }


}
