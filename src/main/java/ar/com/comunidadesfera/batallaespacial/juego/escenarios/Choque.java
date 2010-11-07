package ar.com.comunidadesfera.batallaespacial.juego.escenarios;

import java.util.EnumSet;
import java.util.Set;

import ar.com.comunidadesfera.batallaespacial.Notificacion;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;

/**
 * Escenario para el Choque entre Piezas.
 * 
 *
 */
public interface Choque extends Escenario {

    public static final Set<Notificacion> NOTIFICACION = EnumSet.of(Notificacion.CHOQUE);
    
    /**
     * @return Piezas que intervinieron en el Choque.
     */
    Pieza[] getPiezas();

}