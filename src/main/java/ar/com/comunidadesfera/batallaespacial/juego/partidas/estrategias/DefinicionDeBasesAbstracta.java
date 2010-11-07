package ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias;

import ar.com.comunidadesfera.batallaespacial.juego.Participante;
import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;

/**
 * Implementación básica para las Estrategias de Definición de Bases.
 * Provee los métodos básicos de construcción y disposición de Bases. 
 * 
 * @author Mariano Tugnarelli
 *
 */
public abstract class DefinicionDeBasesAbstracta extends DefinicionDePiezasAbstracta 
                                                 implements DefinicionDeBases {

    /**
     * @post construye una Base Espacial para el Participante.
     * @param participante
     * @return Pieza construida.
     */
    protected BaseEspacial construirBaseEspacial(Participante participante) {

        /* crea una base para el participante */
        BaseEspacial base = participante.getFabricaDePiezas().crearBaseEspacial();
        
        base.setCivilizacion(participante.getCivilizacion());
        
        base.agregarObservador(this.getPartida().getReglamentacion()
                                                .getReglamento(base));
        return base;
    }

}
