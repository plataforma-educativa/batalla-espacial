package ar.com.comunidadesfera.eficiencia.ui;

import javax.swing.JFrame;

public class ControladorEficiencia {

    public static void main(String[] args) {
        
        // TODO borrar este punto de entrada
        
        JFrame frame = new JFrame();
        
        ControladorEficiencia controlador = new ControladorEficiencia();
        VistaEficiencia vista = new VistaEficiencia(controlador);
        frame.add(vista.getPanel());
        frame.setSize(400,400);
        frame.setVisible(true);
    }
}
