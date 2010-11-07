package ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias;

import ar.com.comunidadesfera.batallaespacial.juego.Partida;

/**
 * 
 * @author Mariano Tugnarelli
 *
 */
public interface Estrategia {

    /**
     * @post inicializa la instancia a partir de la Partida indicada 
     */
    void setPartida(Partida<?> partida);

}
