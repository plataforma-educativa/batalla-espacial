package ar.com.comunidadesfera.batallaespacial.aplicacion;

import ar.com.comunidadesfera.batallaespacial.juego.FabricaDePiezas;
import ar.com.comunidadesfera.batallaespacial.juego.Tablero;

/**
 * La FabricaDeTableros define la interfaz para la creación
 * de Tableros a partir de una FabricaDePiezas especificada. 
 * @author mariano
 *
 */
public interface FabricaDeTableros {

    /**
     * @pre ha sido especificada la Fabrica de Piezas a utilizar. 
     * @post construye un Tablero.
     * 
     * @return Tablero  
     */
    Tablero crearTablero();
    
    /**
     * Asigna la Fabrica de Piezas especificada, que será utilizada
     * en la construcción del Tablero.
     * 
     * @param fabricaDePiezas
     */
    void setFabricaDePiezas(FabricaDePiezas fabricaDePiezas);
}
