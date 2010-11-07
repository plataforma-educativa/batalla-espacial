package ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias;

import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.juego.Reglamento;

/**
 * Estrategia utilizada para determinar los Reglamentos a utilizar para cada
 * Pieza
 * 
 * @author Mariano Tugnarelli
 * 
 */
public interface Reglamentacion extends Estrategia {

    /**
     * @pre la Reglamentación se encuentra asociada a una Partida.
     * @post determina el Reglamento a utilizar para la Pieza dada.
     * @param pieza
     * @return Reglamento a utilizar para la Pieza.
     */
    Reglamento getReglamento(Pieza pieza);
}
