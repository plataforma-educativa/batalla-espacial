package ar.com.comunidadesfera.batallaespacial.piezas;

import java.util.Queue;

import ar.com.comunidadesfera.batallaespacial.Control;
import ar.com.comunidadesfera.batallaespacial.Monitor;
import ar.com.comunidadesfera.batallaespacial.Notificacion;
import ar.com.comunidadesfera.batallaespacial.Radar;

/**
 * Interfaz implementada por todas las Piezas que pueden ser controladas por 
 * un Piloto.
 * 
 * @author Mariano Tugnarelli
 *
 */
public interface PiezaControlable {

    /**
     * @return Radar asociado a la Pieza.
     */
    Radar getRadar();
    
    /**
     * @return Monitor asociado a la Pieza.
     */
    Monitor getMonitor();
    
    /**
     * @return Control asociado a la Pieza.
     */
    Control getControl();
    
    /**
     * @return Notificaciones asociadas a la Pieza.
     */
    Queue<Notificacion> getNotificaciones();
}
