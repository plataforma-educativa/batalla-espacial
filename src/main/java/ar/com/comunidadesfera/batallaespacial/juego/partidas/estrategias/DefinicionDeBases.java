package ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias;

import java.util.List;

import ar.com.comunidadesfera.batallaespacial.juego.Participante;
import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;

/**
 * Estrategia utilizada para la construcción de las Bases Espaciales de un
 * Participante y su disposición en el Tablero.
 * 
 * @author Mariano Tugnarelli
 * 
 */
public interface DefinicionDeBases extends Estrategia {

    /**
     * @pre la Estrategia se encuentra asociada a una Partida.
     * @post construye las Bases Espaciales para el Participante y las posiciona
     *       en el Tablero.
     */
    List<BaseEspacial> asignar(Participante participante);
}
