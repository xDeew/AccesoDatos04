package com.andrelut.gimnasioMongo.gui;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;

public class Vista {

    public JTabbedPane tabbedPanedGimnasio;
    public JPanel JPanelClientes;
    public JPanel JPanelClases;
    public JPanel JPanelSuscripciones;
    public JPanel panel1;
    private JTextField txtNombre;
    private DatePicker fechaNacimiento;
    public JFrame frame;


    public Vista() {
        frame = new JFrame("Gimnasio");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(new Dimension(1000, 600));
        frame.setLocationRelativeTo(null);

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

//        setMenu();
//        setListModels();
//        setComboBox();

    }

}
