package ar.com.comunidadesfera.batallaespacial.interfaz.informes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;

public class ControladorInformeBase extends ControladorInformePieza<BaseEspacial> {

    @FXML
    private Label civilizacion;
    
    @FXML
    private Label puntos;
    
    @FXML
    private Label carga;
    
    @FXML
    void initialize() {
        
        this.panel.setText(this.pieza.getIdentificacion());
        
        this.civilizacion.setText(this.pieza.getCivilizacion().getNombre());
        this.puntos.setText(String.valueOf(this.pieza.getPuntos()));
        this.carga.setText(String.valueOf(this.pieza.getBodega().getContenido()));
    }
}
