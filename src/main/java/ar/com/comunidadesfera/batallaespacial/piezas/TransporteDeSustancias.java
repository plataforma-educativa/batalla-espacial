package ar.com.comunidadesfera.batallaespacial.piezas;

import ar.com.comunidadesfera.batallaespacial.Civilizacion;
import ar.com.comunidadesfera.batallaespacial.Sustancia;

public interface TransporteDeSustancias {

    /**
     * @pre ninguna.
     * @post devuelve la cantidad de la Sustancia <code>sustancia</code>
     *       que tiene almacenada el TransporteDeSustancia.
     *       
     * @param sustancia
     * @return cantidad de Sustancia.
     */
    long getCantidad(Sustancia sustancia);
    
    /**
     * @pre ninguna.
     * @post devuelve la cantidad de Sustancia <code>sustancia</code>
     *       que es posible agregar. 
     * 
     * @param sustancia
     * @return
     */
    long getCapacidadDisponible(Sustancia sustancia);
    
    /**
     * @pre <code>cantidad</code> es menor o igual a 
     *       </code>getCapacidadDisponible(sustancia)</code>.
     * @post agrega al TransporteDeCarga <code>cantidad</code> de 
     *       <code>sustancia</code>.
     * 
     * @param sustancia : Sustancia a agregar.
     * @param cantidad : cantidad de Sustancia.
     * @throws IllegalArgumentException si <code>cantidad</code> es mayor a 
     *         </code>getCapacidadDisponible(sustancia)</code>.
     */
    void agregarSustancia(Sustancia sustancia, long cantidad)
        throws IllegalArgumentException;
    
    /**
     * @pre <code>cantidad</code> es mayor o igual a 
     *       </code>getCantidad(sustancia)</code>.
     * @post extrae del TransporteDeCarga <code>cantidad</code> de 
     *       <code>sustancia</code>.
     * 
     * @param sustancia : Sustancia a extraer.
     * @param cantidad : cantidad de Sustancia.
     * @throws IllegalArgumentException si <code>cantidad</code> es menor o 
     *         igual a  </code>getCantidad(sustancia)</code>.
     */
    void extraerSustancia(Sustancia sustancia, long cantidad)
        throws IllegalArgumentException;
    

    /**
     * @return Civilización a la que pertenece el Transporte o null en el caso
     *         de que no pertenezca a ninguna.
     *          
     */
    Civilizacion getCivilizacion();
    
    TransporteDeSustancias clone();
}

