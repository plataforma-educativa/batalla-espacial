package ar.com.comunidadesfera.batallaespacial;

import ar.com.comunidadesfera.batallaespacial.comandos.Atacar;
import ar.com.comunidadesfera.batallaespacial.comandos.Avanzar;
import ar.com.comunidadesfera.batallaespacial.comandos.Comando;
import ar.com.comunidadesfera.batallaespacial.comandos.TransferirCarga;

/**
 * El ControlAplicacion actúa como una fábrica de Comandos que se utilizan
 * para manipular una Nave. 
 * Todo ControlAplicacion se encuentra vinculado a una Nave sobre la cual
 * operan los Comandos creados.
 * 
 * @author Mariano Tugnarelli
 */
public interface Control {

    /**
     * @pre <code>dirección</code> es distinto de Direccion.ORIGEN.
     * @post construye un Comando para Avanzar en la Dirección indicada. 
     * 
     * @param direccion : Dirección de avance.
     * @return Comando creado.
     */
    Avanzar avanzar(Direccion direccion);
    
    /**
     * @pre <code>dirección</code> es distinto de Direccion.ORIGEN.
     * @post construye un Comando para Atacar en la Direción indicada.
     * 
     * @param direccion : Dirección de ataque.
     * @return Comando creado.
     */
    Atacar atacar(Direccion direccion);
    
    /**
     * @pre <code>origen</code> y <code>destino</code> son diferentes.
     * @post construye un Comando para TransferirCargaNaveDeCombate <code>carga</code>
     *       desde la Dirección <code>origen</code> a la Dirección
     *       <code>destino</code>. 
     * 
     * @param origen : Dirección de origen.
     * @param destino : Dirección de destino.
     * @param sustancia : Sustancia a transferir.
     * @param carga : cantidad de carga transferida.
     * @return Comando creado.
     */
    TransferirCarga transferirCarga(Direccion origen, Direccion destino,
                                    Sustancia sustancia, long carga);

    
    /**
     * @pre ninguna.
     * @post construye un Comando para no realizar ninguna acción.
     * 
     * @return Comando creado.
     */
    Comando esperar(); 
    
}
