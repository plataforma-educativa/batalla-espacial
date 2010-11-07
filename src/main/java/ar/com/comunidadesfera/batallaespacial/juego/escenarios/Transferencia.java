package ar.com.comunidadesfera.batallaespacial.juego.escenarios;

import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.piezas.TransporteDeSustancias;

/**
 * Escenario para las Transferencias entre Transportes de Sustancias. 
 *
 */
public interface Transferencia extends Escenario {

    /**
     * @return TransporteDeSustancias desde el cual se extrae Sustancia.
     */
    TransporteDeSustancias getOrigen();
    
    /**
     * @return TransporteDeSustancias en el que se agrega Sustancia.
     */
    TransporteDeSustancias getDestino();
    
    /**
     * @return cantida de Sustancia Transferida.
     */
    long getCantidad();
    
    /**
     * @return Sustancia Transferida.
     */
    Sustancia getSustancia();
}
