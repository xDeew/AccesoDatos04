package com.andrelut.gimnasioMongo.util;

import javax.swing.*;

public class Util {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }


    public static void mostrarMensajeError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);

    }
}
