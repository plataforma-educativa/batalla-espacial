package ar.com.comunidadesfera.batallaespacial;

import ar.com.comunidadesfera.batallaespacial.Civilizacion;

/**
 * Un Reporte es el resultado de un análisis realizador por el Radar.
 * La información que proporciona es confiable en un 90%. 
 *  
 * @author Mariano Tugnarelli
 *
 */
public interface Reporte {

    /**
     * A traves de los Espectros se puede identificar la existencia
     * de Asteriodes, Contenedores, Naves y Bases, o la ausencia
     * de todos ellos (Vacio).
     * Si el Espectro es DESCONOCIDO indica una posición fuera del Tablero.
     */
    public enum Espectro {

        VACIO,  
        DESCONOCIDO,        
        ASTEROIDE,
        CONTENEDOR,
        NAVE,
        BASE,
    }

    /**
     * @pre ninguna.
     * @post devuelve el Espectro detectado. 
     * 
     * @return Espectro detectado.
     */
    Espectro getEspectro();
    
    /**
     * @pre ninguna.
     * @post si se ha detectado la presencia de una Nave o Base
     *       devuelve la Civilización Ã©sta pertenece, null en caso 
     *       contrario.
     * 
     * @return Civilización identificada.
     */
    Civilizacion getCivilizacion();
    
    /**
     * @pre ninguna.
     * @post devuelve la cantidad de la Sustancia <code>sustancia</code> 
     *       detectada. 
     * 
     * @return cantidad de Sustancia.
     */
    long getCantidadDeSustancia(Sustancia sustancia);
}
