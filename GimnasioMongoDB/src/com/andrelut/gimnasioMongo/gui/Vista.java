package com.andrelut.gimnasioMongo.gui;

import com.andrelut.gimnasioMongo.base.Cliente;
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
    public DefaultListModel<Cliente> dlmClientes;
    JMenuItem itemConexion;
    JMenuItem itemSalir;


    public Vista() {
        frame = new JFrame("Gimnasio");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(new Dimension(1000, 600));
        frame.setLocationRelativeTo(null);

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        setMenu();
        setListModels();
//        setComboBox();

    }

    private void setListModels() {
        dlmClientes = new DefaultListModel<>();
        listClientes.setModel(dlmClientes);
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
