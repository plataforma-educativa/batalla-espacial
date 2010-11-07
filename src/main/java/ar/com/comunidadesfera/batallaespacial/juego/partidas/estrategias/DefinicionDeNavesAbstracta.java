package ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias;

import ar.com.comunidadesfera.batallaespacial.Piloto;
import ar.com.comunidadesfera.batallaespacial.juego.Participante;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.NaveDeCombate;

/**
 * Implementación básica para las Estrategias de Definición de Naves.
 * Provee los métodos básicos de construcción y disposición de Naves. 
 * 
 * @author Mariano Tugnarelli
 *
 */
public abstract class DefinicionDeNavesAbstracta extends DefinicionDePiezasAbstracta 
                                                 implements DefinicionDeNaves {

    /**
     * @post construye una Nave de Combate para el Piloto del Participante.
     * @return Pieza construida.
     */
    protected NaveDeCombate construirNaveDeCombate(Participante participante, 
                                                   Piloto piloto) {

        /* construye la Nave y le asigna el Radar */
        NaveDeCombate nave = participante.getFabricaDePiezas().crearNaveDeCombate();

        /* reglamento para la Nave de Combate */
        nave.agregarObservador(this.getPartida().getReglamentacion().getReglamento(nave));
        
        nave.setRadar(this.getPartida().getControlDeRadar().crearRadar(nave));
        
        /* vincula el piloto con la Nave */
        nave.setPiloto(piloto);
        
        return nave;
    }
    
}
