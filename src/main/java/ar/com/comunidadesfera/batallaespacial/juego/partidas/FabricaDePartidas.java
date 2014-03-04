package ar.com.comunidadesfera.batallaespacial.juego.partidas;

import ar.com.comunidadesfera.batallaespacial.juego.Configuracion;
import ar.com.comunidadesfera.batallaespacial.juego.Partida;

/**
 * Fabrica utilizada para construir una Partida a partir de la Configuración
 * dada.
 * 
 * @author Mariano Tugnarelli
 * 
 */
public interface FabricaDePartidas {

    /**
     * @post construye una Partida con la Configuración dada.
     * 
     */
    Partida construir(Configuracion configuracion);
}
