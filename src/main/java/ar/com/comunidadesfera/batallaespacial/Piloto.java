package ar.com.comunidadesfera.batallaespacial;

import ar.com.comunidadesfera.batallaespacial.comandos.Comando;

/**
 * Un Piloto es un Soldado que tiene la capacidad para controlar una Nave 
 * mediante la CabinaDeControl asignada.
 *  
 * 
 * @author Mariano Tugnarelli
 *
 */
public interface Piloto extends Soldado {

    /**
     * @pre la Cabina de ControlAplicacion <code>cabina</code> no ha sido asignada a 
     *       otro Piloto.
     * @post asigna la <code>cabina</code> al Piloto para que a travéz de ella
     *       puede tener control sobre la Nave correspondiente.
     * 
     * @param cabina: Cabina de ControlAplicacion de la Nave asignada al Piloto.
     */
    void setCabinaDeControl(CabinaDeControl cabina);

    /**
     * @pre ninguna.
     * @post devuelve el Comando a ejecutar sobre la Nave
     *       que pilotea.
     * 
     * @return
     */
    Comando proximoComando(); 
}
