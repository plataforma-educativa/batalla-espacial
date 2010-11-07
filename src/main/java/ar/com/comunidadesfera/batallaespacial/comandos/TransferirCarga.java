package ar.com.comunidadesfera.batallaespacial.comandos;

import ar.com.comunidadesfera.batallaespacial.Direccion;
import ar.com.comunidadesfera.batallaespacial.Sustancia;


/**
 * Comando que representa la transferencia de carga. 
 *  
 * @author Mariano Tugnarelli
 *
 */
public interface TransferirCarga extends Comando {


    /**
     * @pre ninguna.
     * @post devuelve la Sustancia transferida.
     * 
     * @return Sustancia transferida.
     */
    Sustancia getSustancia();
    
    /**
     * @pre ninguna.
     * @post devuelve la cantidad de carga transferida.
     * 
     * @return cantidad de carga transferida.
     */
    long getCarga();
    
    /**
     * @pre ninguna.
     * @post devuelve la Dirección desde la cual se transfiere la carga.
     *  
     * @return Dirección de origen de la transferencia.
     */
    Direccion getDireccionOrigen();
    
    /**
     * @pre ninguna.
     * @post devuelve la Dirección hacia la cual se transfiere la carga.
     *  
     * @return Dirección de destino de la transferencia.
     */
    Direccion getDireccionDestino();
    

}
