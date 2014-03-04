package ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias;

import ar.com.comunidadesfera.batallaespacial.juego.Partida;

/**
 * Implementación básica de Estrategia.
 * 
 * @author Mariano Tugnarelli
 * 
 */
public abstract class EstrategiaAbstracta implements Estrategia {

    private Partida partida;

    public void setPartida(Partida partida) {

        this.partida = partida;

        this.configurar();
    }

    protected Partida getPartida() {

        return this.partida;
    }
    
    /**
     * @pre la Partida ha sido asignada.
     * @post realiza la configuración de la Estragia una vez que la 
     *       Partida ha sido asignada.
     */
    protected void configurar() {

        /* punto de extensión para las subclases */ 
    }
}
