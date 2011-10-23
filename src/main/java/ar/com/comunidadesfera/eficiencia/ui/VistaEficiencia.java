package ar.com.comunidadesfera.eficiencia.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

public class VistaEficiencia {

    private ControladorEficiencia controlador;
    
    private JPanel panelBasico;
    
    private JTable tablaMediciones;
    
    public VistaEficiencia(ControladorEficiencia controlador) {
     
        this.controlador = controlador;
        this.inicializarPanel();
    }
    
    public JPanel getPanel() {
        
        return this.panelBasico;
    }
    
    private void inicializarPanel() {
        
        this.panelBasico = new JPanel(new BorderLayout());
        this.tablaMediciones = new JTable();
        this.panelBasico.add(this.tablaMediciones);
    }
}
