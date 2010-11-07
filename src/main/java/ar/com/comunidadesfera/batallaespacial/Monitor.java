package ar.com.comunidadesfera.batallaespacial;

/**
 * Cada monitor se encuentra asociado a una Nave de la cual
 * reporta su condición.
 * 
 * @author Mariano Tugnarelli
 *
 */
public interface Monitor {

    /**
     * @pre ninguna.
     * @post devuelve un valor entre 0 y 100 que representa el
     *       nivel de energía que tienen los Escudos [%].
     *       El valor 100 indica máximo poder en los Escudos.
     *       El valor 0 indica Escudos fuera de funcionamiento.  
     * 
     * @return nivel de los escudos.
     */
    byte getNivelDeEscudos();
    
    /**
     * @pre ninguna.
     * @post devuelve la cantidad de Torpedos de Fotones que
     *       posee disponibles.
     * 
     * @return cantidad de Torpedos.
     */
    int getCantidadDeTorpedos();
    
    /**
     * @pre ninguna.
     * @post devuelve la cantidad de Sustancia <code>sustancia</code> [gr.] que 
     *       existe en la bodega. 
     * 
     * @param sustancia
     * @return gramos se Sustancia almacenados.
     */
    long getCarga(Sustancia sustancia);
    
    /**
     * @pre ninguna.
     * @post devuelve un valor entre 0 y 100 que representa
     *       el porcentaje de la bodega que es ocupado por
     *       la carga.
     *       El valor 0 indica que no existe carga, bodega vacia.
     *       El valor 100 indica que la bodega está completa.
     * 
     * @return porcentaje de la bodega ocupada por la carga.
     */
    byte getNivelDeCarga();

}
