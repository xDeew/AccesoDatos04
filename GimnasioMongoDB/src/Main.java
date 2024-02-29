import com.andrelut.gimnasioMongo.gui.Controlador;
import com.andrelut.gimnasioMongo.gui.Modelo;
import com.andrelut.gimnasioMongo.gui.Vista;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        new Controlador(new Modelo(), new Vista());
    }
}
