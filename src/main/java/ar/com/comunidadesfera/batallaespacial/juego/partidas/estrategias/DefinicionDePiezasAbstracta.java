package ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias;

import ar.com.comunidadesfera.batallaespacial.juego.CasilleroInvalidoException;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.juego.Tablero;
import ar.com.comunidadesfera.batallaespacial.juego.Tablero.Iterador;

/**
 * Implementación básica para las Estrategias de Definición de Piezas.
 * Provee los métodos básicos de disposición de Piezas.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class DefinicionDePiezasAbstracta extends EstrategiaAbstracta {

    
    /**
     * @post localiza a la Pieza de manera aleatoria en el Tablero de
     *       la Partida.
     */
    protected void posicionarAleatoriamente(Pieza pieza) {

        /* busca posiciones aleatoriamente hasta encontrar una sin Piezas */
        Tablero.Iterador iterador = this.getPartida().getTablero().iterator();
        
        do {

            iterador.random();

        } while (iterador.get() != null);

        this.posicionar(pieza, iterador);
    }

    /**
     * @pre iterador se encuentra localizado en un Casillero disponible
     *      del Tablero de la Partida.
     * @post posiciona la Pieza en el Casillero referencia por el Iterador.
     * 
     * @param pieza
     * @param iterador
     */
    protected void posicionar(Pieza pieza, Iterador iterador) {
        
        try {

            this.getPartida().getTablero().colocarPieza(pieza, iterador);

        } catch (CasilleroInvalidoException cie) {
            
            throw new RuntimeException("No se ha podido posicionar Base", cie);
        }
    }
    
    /**
     * @post localiza a la Pieza en el centro del Tablero de la Partida.
     */
    protected void posicionarCentro(Pieza pieza) {
        
        Tablero tablero = this.getPartida().getTablero();
        
        Tablero.Iterador iterador = this.getPartida().getTablero().iterator();
        
        try {
            
            iterador.move(tablero.getFilas() / 2, tablero.getColumnas() / 2);
            
            this.getPartida().getTablero().colocarPieza(pieza, iterador);
            
        } catch (CasilleroInvalidoException cie) {
            
            throw new RuntimeException("No se ha posido centrar el iterador en el Tablero", cie);
        }
        
    }
}
