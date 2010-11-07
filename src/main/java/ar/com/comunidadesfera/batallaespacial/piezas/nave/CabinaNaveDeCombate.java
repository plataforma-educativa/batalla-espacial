package ar.com.comunidadesfera.batallaespacial.piezas.nave;

import java.util.Queue;

import ar.com.comunidadesfera.batallaespacial.CabinaDeControl;
import ar.com.comunidadesfera.batallaespacial.Control;
import ar.com.comunidadesfera.batallaespacial.Monitor;
import ar.com.comunidadesfera.batallaespacial.Notificacion;
import ar.com.comunidadesfera.batallaespacial.Radar;

/**
 * 
 * Implementación de la Cabina de Control de la NaveDeCombate
 * 
 */
public class CabinaNaveDeCombate implements CabinaDeControl {

    /**
     * Nave a la cual pertenece la Cabina.
     */
    private final NaveDeCombate nave;

    protected CabinaNaveDeCombate(NaveDeCombate naveDeCombate) {
        super();
        this.nave = naveDeCombate;
    }
    
    public Radar getRadar() {

        return this.nave.getRadar();
    }

    /**
     * Devuelve el Monitor asociado a la Nave de Combate.
     */
    public Monitor getMonitor() {
        return this.nave.getMonitor();
    }

    public Control getControl() {
        
        return this.nave.getControl();
    }

    public Queue<Notificacion> getNotificaciones() {
        
        return this.nave.getNotificaciones();
    }
    
}