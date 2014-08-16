package ar.com.comunidadesfera.batallaespacial.interfaz.informes;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import ar.com.comunidadesfera.batallaespacial.interfaz.Controlador;
import ar.com.comunidadesfera.batallaespacial.interfaz.Vista;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;


public abstract class ControladorInformePieza<T extends Pieza> implements Controlador, Pieza.Observador {

    private final Runnable requerirFoco = new Runnable() {
        
        @Override
        public void run() {

            panel.requestFocus();
        }
    };
    
    
    private final Runnable completarDatos = new Runnable() {
        
        @Override
        public void run() {
            
            completar();
        }
    };
    
    @FXML
    protected ResourceBundle resources;

    @FXML
    protected URL location;

    @FXML
    protected TitledPane panel;
    
    protected T pieza;
    
    @FXML
    void initialize() {
        
        this.completar();
    }
    
    public void setPieza(T pieza) {
        
        this.pieza = pieza;
        
        this.pieza.agregarObservador(this);
    }

    @Override
    public void cambio(Pieza pieza) {
        
        Platform.runLater(this.completarDatos);
    }

    @Override
    public void seleccionar(Pieza pieza, Vista origen) {

        this.panel.setExpanded(true);
        
        Platform.runLater(this.requerirFoco);
    }
    
    protected abstract void completar();
}
