package ar.com.comunidadesfera.batallaespacial.piezas.base;

import java.util.List;

import ar.com.comunidadesfera.batallaespacial.juego.Tablero.Casillero;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.Nave;

/**
 * Pista de Aterrizaje de Naves
 * 
 */
public interface PistaDeAterrizaje {

    /**
     * Agrega la Nave <code>nave</code> a la Pista.
     */
    void agregarNave(Nave nave);
    
    /**
     * Retira la Nave de la pista.
     */
    void retirarNave(Nave nave);

    /**
     * Devuelve una lista (inmutable) de las Naves en la Pista.
     */
    List<Nave> getNaves();
    
    /**
     * Devuelve el Casillero que ocupa la Pista de Aterrizaje
     */
    Casillero getCasillero();
 
    PistaDeAterrizaje clone();
}
