package ar.com.comunidadesfera.batallaespacial.juego;

import ar.com.comunidadesfera.batallaespacial.Civilizacion;

/**
 * Configuración de un Participante del juego.
 *
 */
public class Participante {
    
    private Civilizacion civilizacion;
    
    private int cantidadNaves;
    
    private FabricaDePiezas fabricaDePiezas = null;

    public Participante(Civilizacion civilizacion, int naves, 
                        FabricaDePiezas fabrica) {
        
        this.setCivilizacion(civilizacion);
        this.setNaves(naves);
        this.setFabricaDePiezas(fabrica);
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

    public String toString() {
        
        return new StringBuffer()
                      .append("Participante [ civilizacion: ")
                      .append(this.getCivilizacion())
                      .append(", naves:")
                      .append(this.getCantidadNaves())
                      .append(" ]")
                      .toString();
    }

    public FabricaDePiezas getFabricaDePiezas() {
        return fabricaDePiezas;
    }

    protected void setFabricaDePiezas(FabricaDePiezas fabricaDePiezas) {
        this.fabricaDePiezas = fabricaDePiezas;
    }
}