package ar.com.comunidadesfera.eficiencia.instrumentos;

import ar.com.comunidadesfera.eficiencia.Ejecucion;

public interface Contador {

    /**
     * @post incrementa la cuenta llevada por el Contador en 1.
     */
    void incrementar();

    /**
     * @return valor actual de la cuenta llevada por el Contador.
     */
    long getValor();

    /**
     * @return Ejecución a la que pertenece el Contador.
     */
    Ejecucion getEjecucion();

    /**
     * @post incrementa la cuenta llevada por el Contador en 
     *       <code>incremento</code>. 
     * @param incremento
     */
    void incrementar(long incremento);

    /**
     * @post devuelve el Contador parcial asociado al discrimiante dado, si
     *       no existe lo crea. Los cambios sobre el Contador Parcial
     *       afectan al Contador.
     *        
     * @param discriminante identificador del contador parcial.
     * @return Contador parcial.
     */
    Contador getParcial(String discriminante);

    /**
     * @return identificador discriminante del Contador si se trata de uno
     *         parcial, null en caso contrario.
     */
    String getDiscriminante();

}
