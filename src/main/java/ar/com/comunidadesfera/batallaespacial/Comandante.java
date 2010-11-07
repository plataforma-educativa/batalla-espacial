package ar.com.comunidadesfera.batallaespacial;

/**
 * Un Comandante es un Soldado con la capacidad para crear un 
 * escuadrón de Pilotos.
 * 
 * @author Mariano Tugnarelli
 *
 */
public interface Comandante extends Soldado {

    /**
     * @pre <code>integrantes</code> es mayor a 0.
     * @post devuelve el escuadrón conformado por <code>integrantes</code> 
     *       Pilotos.  
     *       
     * @return array de longitud <code>integrantes</code>, con los Pilotos
     *         que conforman el escuadrón.
     * @throws IllegalArgumentException si integrantes es menor o igual a 0.
     */
    Piloto[] construirEscuadron(int integrantes) 
        throws IllegalArgumentException;
}
