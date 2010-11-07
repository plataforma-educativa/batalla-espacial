package ar.com.comunidadesfera.batallaespacial.piezas.nave;

import ar.com.comunidadesfera.batallaespacial.Monitor;
import ar.com.comunidadesfera.batallaespacial.Sustancia;

/**
 * El Monitor de NaveDeCombate informa de su estado.
 * Un Monitor está siempre asociada a una única NaveDeCombate.
 * 
 */
public class MonitorNaveDeCombate implements Monitor {

    /**
     * Nave sobre que se monitorea.
     */
    private final NaveDeCombate nave;

    protected MonitorNaveDeCombate(NaveDeCombate naveDeCombate) {
        super();
        this.nave = naveDeCombate;
    }
    
    /**
     * @see Monitor#getNivelDeEscudos()
     */
    public byte getNivelDeEscudos() {
        return this.nave.getNivelDeEscudos();
    }

    /**
     * @see Monitor#getCantidadDeTorpedos()
     */
    public int getCantidadDeTorpedos() {
        return this.nave.getCantidadDeTorpedos();
    }

    /**
     * @see Monitor#getNivelDeCarga()
     */
    public byte getNivelDeCarga() {

        return this.nave.getBodega().getNivelDeCarga();
    }

    /**
     * @see Monitor#getNivelDeCarga()
     */
    public long getCarga(Sustancia sustancia) {
        
        return this.nave.getBodega().getCantidad(sustancia);
    }
    
}