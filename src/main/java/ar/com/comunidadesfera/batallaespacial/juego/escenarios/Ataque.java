package ar.com.comunidadesfera.batallaespacial.juego.escenarios;

import java.util.EnumSet;
import java.util.Set;

import ar.com.comunidadesfera.batallaespacial.Notificacion;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.piezas.Arma;

/**
 *  Escenario para el Ataque entre Piezas.
 */
public interface Ataque extends Escenario {

    public static final Set<Notificacion> NOTIFICACION = EnumSet.of(Notificacion.ATAQUE);

    /**
     * @return Armas utilizadas en el ataque.
     */
    Set<Arma> getArmas();

    /**
     * @return Piezas que realizan el Ataque.
     */
    Pieza[] getAtacantes();

    /**
     * @return Piezas atacadas.
     */
    Pieza[] getDestinatarios();
}