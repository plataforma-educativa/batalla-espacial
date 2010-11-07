package ar.com.comunidadesfera.batallaespacial;


/**
 * Un Soldado es un miembro de una Civilización que cumplirá un rol
 * específico en la misma.
 * 
 * @author Mariano Tugnarelli
 *
 */
public interface Soldado {

    /**
     * @pre ninguna.
     * @post devuelve la Civilización a la que pertenece el Soldado. 
     *  
     * @return Civilización del Soldado.
     */
    Civilizacion getCivilizacion();

    /**
     * @pre ninguna.
     * @post devuelve el nombre del Soldado.
     * 
     *  @return Nombre del Soldado.
     */
    String getNombre();

}