package ar.com.comunidadesfera.batallaespacial.juego;

import ar.com.comunidadesfera.batallaespacial.Reporte;

/**
 * Interfaz implementada por todos aquellos elementos del Juego 
 * que pueden ser detectados por un Radar.
 * Generan un Reporte que describe lo que el Radar puede conocer del
 * elemento.
 * 
 * @author Mariano Tugnarelli
 *
 */
public interface Detectable {

    /**
     * @post construye un Reporte del elemento que indica la información
     *       que puede detectar un Radar del elemento.
     * 
     * @return Reporte del elemento.
     */
    Reporte reportar();
}
