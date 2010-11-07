package ar.com.comunidadesfera.batallaespacial.test.pieza;

public interface ComprobarClon<E> {

    String getDescripcion();
    
    /**
     * @post comprueba una entidad con su clon utilizando los assert 
     *       necesarios.
     * @param elemento
     * @param clon
     */
    void ejecutar(E entidad, E clon);
}
