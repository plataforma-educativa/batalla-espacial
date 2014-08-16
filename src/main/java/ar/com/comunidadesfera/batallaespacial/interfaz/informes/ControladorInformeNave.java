package ar.com.comunidadesfera.batallaespacial.interfaz.informes;

import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.piezas.Arma;
import ar.com.comunidadesfera.batallaespacial.piezas.Arsenal;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.Nave;

public class ControladorInformeNave extends ControladorInformePieza<Nave> {

    @FXML
    private Label escudos;
    
    @FXML
    private Label carga;
    
    @FXML
    private Label arsenal;
    
    @Override
    protected void completar() {

        this.panel.setText(this.pieza.getIdentificacion());
        
        StringBuilder valorEscudo = new StringBuilder();
        valorEscudo.append(this.pieza.getNivelDeEscudos())
                   .append("%");
        
        this.escudos.setText(valorEscudo.toString());
        
        StringBuilder valorArsenal = new StringBuilder();
        Arsenal arsenal = this.pieza.getArsenal();
        for (Arma arma : arsenal.getArmamento()) {
        
            valorArsenal.append(arma);

            int municiones = arsenal.getMuniciones(arma);
            if (municiones < Integer.MAX_VALUE) {
                
                valorArsenal.append(": ")
                            .append(arsenal.getMuniciones(arma));
            }
            valorArsenal.append("\n");
        }
        
        this.arsenal.setText(valorArsenal.toString());
        
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
