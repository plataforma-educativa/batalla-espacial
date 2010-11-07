package ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias;

import java.util.LinkedList;
import java.util.List;

import ar.com.comunidadesfera.batallaespacial.Piloto;
import ar.com.comunidadesfera.batallaespacial.juego.Participante;
import ar.com.comunidadesfera.batallaespacial.juego.PilotoControlado;
import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.Nave;

/**
 * Implementación de Estrategia de Definicion de Naves de Combate posicionadas
 * en una Base Espacial del Participante.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class NavesDeCombatePosicionadasEnBase 
                extends DefinicionDeNavesAbstracta {

    public List<Nave> asignar(Participante participante, List<BaseEspacial> bases,
                              List<PilotoControlado> pilotos) {

        if (bases.isEmpty()) {
            
            throw new IllegalArgumentException("No se proveen de bases");
        }
        
        BaseEspacial base = bases.get(0);
        
        List<Nave> naves = new LinkedList<Nave>();

        for (Piloto piloto : pilotos) {
            
            Nave nave = this.construirNaveDeCombate(participante, piloto); 

            nave.setPista(base);
            
            naves.add(nave);
        }
        
        return naves;
    }

}
