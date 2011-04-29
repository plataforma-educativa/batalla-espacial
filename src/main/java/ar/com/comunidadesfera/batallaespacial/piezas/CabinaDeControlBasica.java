package ar.com.comunidadesfera.batallaespacial.piezas;

import java.util.Queue;

import ar.com.comunidadesfera.batallaespacial.CabinaDeControl;
import ar.com.comunidadesfera.batallaespacial.Control;
import ar.com.comunidadesfera.batallaespacial.Monitor;
import ar.com.comunidadesfera.batallaespacial.Notificacion;
import ar.com.comunidadesfera.batallaespacial.Radar;

/**
 * 
 * Implementación de la Cabina de Control para tod Pieza Controlable.
 * 
 */
public class CabinaDeControlBasica implements CabinaDeControl {

    /**
     * Pieza a la cual pertenece la Cabina de Control.
     */
    private final PiezaControlable pieza;

    public CabinaDeControlBasica(PiezaControlable pieza) {

        this.pieza = pieza;
    }
    
    
    public Radar getRadar() {

        return this.pieza.getRadar();
    }

    /**
     * Devuelve el Monitor asociado a la Nave de Combate.
     */
    public Monitor getMonitor() {
        return this.pieza.getMonitor();
    }

    public Control getControl() {
        
        return this.pieza.getControl();
    }

    public Queue<Notificacion> getNotificaciones() {
        
        return this.pieza.getNotificaciones();
    }
}