package ar.com.comunidadesfera.batallaespacial.juego;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import ar.com.comunidadesfera.batallaespacial.Civilizacion;

/**
 * Configuración de un Participante del juego.
 *
 */
public class Participante {
    
    private Civilizacion civilizacion;
    
    private int cantidadNaves;
    
    private Paint pintura;

    private FabricaDePiezas fabricaDePiezas = null;

    public Participante(Civilizacion civilizacion, int naves, 
                        FabricaDePiezas fabrica,
                        String color) {
        
        this.setCivilizacion(civilizacion);
        this.setNaves(naves);
        this.setFabricaDePiezas(fabrica);
        this.setPintura(Color.web(color));
    }
    
    public Civilizacion getCivilizacion() {
        return civilizacion;
    }

    protected void setCivilizacion(Civilizacion civilizacion) {
        this.civilizacion = civilizacion;
    }

    public int getCantidadNaves() {
        return cantidadNaves;
    }

    protected void setNaves(int naves) {
        this.cantidadNaves = naves;
    }

    public FabricaDePiezas getFabricaDePiezas() {
        return fabricaDePiezas;
    }

    protected void setFabricaDePiezas(FabricaDePiezas fabricaDePiezas) {
        this.fabricaDePiezas = fabricaDePiezas;
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

    public String toString() {
        
        return new StringBuffer()
                      .append("Participante [ civilizacion: ")
                      .append(this.getCivilizacion())
                      .append(", naves:")
                      .append(this.getCantidadNaves())
                      .append(" ]")
                      .toString();
    }

}