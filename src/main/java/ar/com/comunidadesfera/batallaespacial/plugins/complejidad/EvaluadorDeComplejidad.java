package ar.com.comunidadesfera.batallaespacial.plugins.complejidad;

import ar.com.comunidadesfera.batallaespacial.Reporte.Espectro;

/**
 * Un Evaluador de Complejidad permite determinar la complejidad de una Partida,
 * analizando los Espectros encontrados en el Tablero. 
 * 
 * @author Mariano Tugnarelli
 *
 */
public interface EvaluadorDeComplejidad {

    /**
     * @post analiza la complejidad de la Partida a partir de los Espectros
     *       detectados en el Radar sobre todo el Tablero del Juego.
     *       Devuelve en la Complejidad la información encontrada.
     * 
     * @param tablero
     * @return
     */
    Complejidad evaluar(Espectro[][] espectrosDelTablero);
}
