package ar.com.comunidadesfera.batallaespacial.interfaz.informes;

import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;

public class ControladorInformeBase extends ControladorInformePieza<BaseEspacial> {

    @FXML
    private Label puntos;
    
    @FXML
    private Label carga;
    
    @Override
    protected void completar() {
        
        this.panel.setText(this.pieza.getIdentificacion());

        this.puntos.setText(String.valueOf(this.pieza.getPuntos()));
        
        StringBuilder valorCarga = new StringBuilder();
        Map<Sustancia, Long> carga = this.pieza.getCarga();
        for (Sustancia sustancia : Sustancia.values()) {

            Long cantidad = carga.get(sustancia);
            
            valorCarga.append(sustancia)
                      .append(": ")
                      .append(cantidad != null ?  cantidad : 0)
                      .append("\n");
        }
        this.carga.setText(valorCarga.toString());
    }
}
