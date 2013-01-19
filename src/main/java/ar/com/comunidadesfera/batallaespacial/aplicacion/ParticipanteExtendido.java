package ar.com.comunidadesfera.batallaespacial.aplicacion;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import ar.com.comunidadesfera.batallaespacial.Civilizacion;
import ar.com.comunidadesfera.batallaespacial.juego.FabricaDePiezas;
import ar.com.comunidadesfera.batallaespacial.juego.Participante;

/**
 * Configuración de los Participantes que incorporan los parámetros de la
 * interfaz gráfica.
 * 
 * 
 */
public class ParticipanteExtendido extends Participante {

    private Paint pintura;
    
    public ParticipanteExtendido(Civilizacion civilizacion, int naves, 
                        FabricaDePiezas fabrica, String color) {
        super(civilizacion, naves, fabrica);
        this.setPintura(Color.web(color)); 
    }

    @Deprecated
    public java.awt.Color getColor() {
        return java.awt.Color.RED;
    }

    /**
     * 
     * @return pintura utilizada para identificar las Piezas que le pertenecen.
     */
    public Paint getPintura() {
        
        return this.pintura;
    }
    
    protected void setPintura(Paint pintura) {
        this.pintura = pintura;
    }

}
