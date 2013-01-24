package ar.com.comunidadesfera.batallaespacial.piezas;

import java.util.Set;

/**
 * Armamento disponible de una Pieza o conjunto de Piezas. 
 * 
 * @author Mariano Tugnarelli
 *
 */
public interface Arsenal {

    /**
     * 
     * @return tipo de Armas con el que cuenta el Arsenal. 
     */
    Set<Arma> getArmamento();
    
    /**
     * @param arma
     * @return cantidad de máxima de municiones de <code>arma</code> que pueden ser almacenados en el Arsenal.
     */
    int getCapacidad(Arma arma);
    
    /**
     * @param arma
     * @return cantidad de unidades de <code>arma</code> disponibles en el Arsenal.
     */
    int getMuniciones(Arma arma);
}
