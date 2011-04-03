package ar.com.comunidadesfera.eficiencia.instrumentos;

import ar.com.comunidadesfera.eficiencia.Ejecucion;
import ar.com.comunidadesfera.eficiencia.InstrumentoDeMedicion;
import ar.com.comunidadesfera.eficiencia.registros.Discriminante;

/**
 * Instrumento de Medición que permite contabilizar recursos discretos a partir
 * de una interfaz incremental.
 * 
 * @author Mariano Tugnarelli
 *
 */
public interface Contador  extends InstrumentoDeMedicion {

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
     * @return Discriminante asociado al Contador. En caso de que sea el 
     *         Contador principal (no parcial) de la Ejecución, el Discriminante
     *         es el Módulo de la Ejecución.
     */
    Discriminante getDiscriminante();

}
