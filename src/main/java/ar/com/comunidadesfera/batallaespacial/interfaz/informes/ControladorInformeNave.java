package ar.com.comunidadesfera.batallaespacial.interfaz.informes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.Nave;

public class ControladorInformeNave extends ControladorInformePieza<Nave> {

    @FXML
    private Label civilizacion;
    
    @FXML
    private Label puntos;
    
    @FXML
    private Label escudos;
    
    @FXML
    private Label carga;
    
    @FXML
    private Label arsenal;
    
    
    @FXML
    void initialize() {
        
        this.panel.setText(this.pieza.getIdentificacion());
        
        this.civilizacion.setText(this.pieza.getCivilizacion().getNombre());
        this.escudos.setText(String.valueOf(this.pieza.getNivelDeEscudos()));
        this.carga.setText(String.valueOf(this.pieza.getCarga()));
        this.puntos.setText(String.valueOf(this.pieza.getPuntos()));
        this.arsenal.setText(String.valueOf(this.pieza.getArsenal().getArmamento()));
    }
}
